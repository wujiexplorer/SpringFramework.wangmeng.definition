package com.lx.benefits.web.controller.admin;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.enterpriseadmin.credit.EmployeeCreditExcelDto;
import com.lx.benefits.bean.dto.enterpriseadmin.order.OrderListExcelDto;
import com.lx.benefits.bean.dto.enterpriseadmin.order.SaleExcelDto;
import com.lx.benefits.bean.entity.creditoperateinfo.CreditOperateInfo;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfo;
import com.lx.benefits.bean.entity.sale.AdvanceSaleItem;
import com.lx.benefits.bean.param.order.ItemOrderListQueryParam;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.vo.order.ItemOrderListVO;
import com.lx.benefits.mapper.employeecreditinfo.EmployeeCreditInfoMapper;
import com.lx.benefits.service.creditoperateinfo.CreditOperateInfoService;
import com.lx.benefits.service.employeecreditinfo.EmployeeCreditInfoService;
import com.lx.benefits.service.order.OrderService;
import com.lx.benefits.service.sale.AdvanceSaleItemService;
import com.lx.benefits.web.controller.base.BaseAdminController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * @author unknow on 2018-12-11 00:27.
 */
@Api(tags = "文件下载")
@RestController("DownloadController")
@RequestMapping("/download")
public class DownloadController extends BaseAdminController {

    private final static Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @Autowired
    private EmployeeCreditInfoService employeeCreditInfoService;

    @Autowired
    private CreditOperateInfoService creditOperateInfoService;

    @Autowired
    private EmployeeCreditInfoMapper employeeCreditInfoMapper;

    @Resource
    private AdvanceSaleItemService advanceSaleItemService;

    @Resource
    private OrderService orderService;


    @ApiOperation(value = "下载excel模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1表示下载参与活动文件", required = false)
    })
    @RequestMapping(value = "/campaignExcel", method = RequestMethod.GET)
    public JSONObject campaignExcel(HttpServletResponse response,Long campaignId) {

        ServletOutputStream out = null;
        if (null == campaignId || campaignId < 1) {
            return Response.fail("活动id不能为空");
        }
        List<EmployeeCreditExcelDto> dtos = new ArrayList<>();
        List<CreditOperateInfo> list = creditOperateInfoService.selectDownExcel(campaignId);
        for (CreditOperateInfo creditOperateInfo : list) {
            EmployeeCreditExcelDto employeeCreditExcelDto = new EmployeeCreditExcelDto();
            employeeCreditExcelDto.setCreditType(creditOperateInfo.getCreditType().toString());
            employeeCreditExcelDto.setCredit(creditOperateInfo.getCredit().toString());
            employeeCreditExcelDto.setEmployeeName(creditOperateInfo.getEmployeeName());
            employeeCreditExcelDto.setEmployeeNo(creditOperateInfo.getEmployeeNo());
            // 查询剩余积分
            Map<String,Object> map = new HashMap<>();
            map.put("campaignId",campaignId);
            map.put("employeeId",creditOperateInfo.getEmployeeId());
            Double creditSurplus = employeeCreditInfoMapper.selectSumByExample(map);
            employeeCreditExcelDto.setSurplus(creditSurplus.toString());
            dtos.add(employeeCreditExcelDto);
        }
        String dateStr = DateUtil.dateToString(new Date(),DateUtil.DATE_FORMAT_NOW);
        String fileName = "" + dateStr + ".xlsx";

        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet1 = new Sheet(1, 0, EmployeeCreditExcelDto.class);
            writer.write(dtos, sheet1);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            logger.info("活动参与文件下载异常：{}", e);
            return Response.fail("下载文件异常");
        }
        return null;
    }

    @ApiOperation(value = "下载代下单清单")
    @RequestMapping(value = "/advanceSaleExcel", method = RequestMethod.GET)
    public JSONObject advanceSaleExcel(HttpServletResponse response,Long campaignId) {

        ServletOutputStream out = null;
        if (null == campaignId || campaignId < 1) {
            return Response.fail("活动id不能为空");
        }
        List<SaleExcelDto> dtos = new ArrayList<>();
        List<AdvanceSaleItem> list = advanceSaleItemService.selectDownExcel(campaignId);
        for (AdvanceSaleItem advanceSaleItem : list) {
            SaleExcelDto saleExcelDto = new SaleExcelDto();
            saleExcelDto.setCampaignId(advanceSaleItem.getCampaignId().toString());
            saleExcelDto.setEmployeeName(advanceSaleItem.getEmployeeName());
            saleExcelDto.setEmployeeNo(advanceSaleItem.getEmployeeNo());
            saleExcelDto.setSku(advanceSaleItem.getSku().toString());
            saleExcelDto.setCount(advanceSaleItem.getCount().toString());
            Integer flag = advanceSaleItem.getFlag();
            if (flag.equals(0)) {
                saleExcelDto.setOrderMsg("未执行下单");
            } else {
                if (StringUtils.isEmpty(advanceSaleItem.getOrderMsg())) {
                    if (flag.equals(1)) {
                        saleExcelDto.setOrderMsg("执行下单失败");
                    } else if (flag.equals(2)) {
                        saleExcelDto.setOrderMsg("执行下单成功");
                    }
                } else {
                    saleExcelDto.setOrderMsg(advanceSaleItem.getOrderMsg());
                }
            }

            if (advanceSaleItem.getGoodsPrice() != null) {
                saleExcelDto.setGoodsPrice(advanceSaleItem.getGoodsPrice().toString());
            }
            dtos.add(saleExcelDto);
        }
        String dateStr = DateUtil.dateToString(new Date(),DateUtil.DATE_FORMAT_NOW);
        String fileName = "" + dateStr + ".xlsx";

        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet1 = new Sheet(1, 0, SaleExcelDto.class);
            writer.write(dtos, sheet1);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            logger.info("代下单清单文件下载异常：{}", e);
            return Response.fail("下载文件异常");
        }
        return null;
    }



    @ApiOperation(value = "下载预售报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1表示下载参与活动文件", required = false)
    })
    @RequestMapping(value = "/downloadSaleList", method = RequestMethod.GET)
    public JSONObject downloadSaleList(HttpServletResponse response,Long campaignId) {

        ServletOutputStream out = null;
        if (null == campaignId || campaignId < 1) {
            return Response.fail("活动id不能为空");
        }
        List<EmployeeCreditExcelDto> dtos = new ArrayList<>();
        List<CreditOperateInfo> list = creditOperateInfoService.selectDownExcel(campaignId);
        for (CreditOperateInfo creditOperateInfo : list) {
            EmployeeCreditExcelDto employeeCreditExcelDto = new EmployeeCreditExcelDto();
            employeeCreditExcelDto.setCredit(creditOperateInfo.getCredit().toString());
            employeeCreditExcelDto.setCreditType(creditOperateInfo.getCreditType().toString());
            employeeCreditExcelDto.setEmployeeNo(creditOperateInfo.getEmployeeNo());
            employeeCreditExcelDto.setEmployeeName(creditOperateInfo.getEmployeeName());
            EmployeeCreditInfo employeeCreditInfo = employeeCreditInfoService.getEmployeeCreditInfo(Long.parseLong(creditOperateInfo.getEmployeeId().toString()),campaignId);
            if (null != employeeCreditInfo.getCredit()) {
                employeeCreditExcelDto.setSurplus(employeeCreditInfo.getCredit().toString());
            }
            dtos.add(employeeCreditExcelDto);
        }
        String dateStr = DateUtil.dateToString(new Date(),DateUtil.DATE_FORMAT_NOW);
        String fileName = "" + dateStr + ".xlsx";

        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet1 = new Sheet(1, 0, EmployeeCreditExcelDto.class);
            writer.write(dtos, sheet1);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.reset();

            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            logger.info("活动文件下载异常：{}", e);
            return Response.fail("下载文件异常");
        }
        return null;
    }

    @GetMapping(value = "/downOrderlist")
    public JSONObject downOrderlist(HttpServletResponse response,@RequestBody ItemOrderListQueryParam req) {

        ServletOutputStream out = null;
        List<OrderListExcelDto> dtos = new ArrayList<>();

        List<ItemOrderListVO> listVOS = orderService.listItemOrderList(req);
        for (ItemOrderListVO itemOrderListVO :listVOS) {
            OrderListExcelDto orderListExcelDto = new OrderListExcelDto();
            dtos.add(orderListExcelDto);
        }
       // itemOrderListVO.setStatusDesc(OrderEnum.STATUS.getDescByCode(itemOrderListVO.getStatus()));
       // itemOrderListVO.setPayChannelDesc(PayChannelEnum.getDescByCode(itemOrderListVO.getPayChannel()));
       // itemOrderListVO.setReverseStatusDesc(OrderEnum.REVERSE_STATUS.getDescByCode(itemOrderListVO.getReverseStatus()));
        String dateStr = DateUtil.dateToString(new Date(),DateUtil.DATE_FORMAT_NOW);
        String fileName = "" + dateStr + ".xlsx";

        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet1 = new Sheet(1, 0, OrderListExcelDto.class);
            writer.write(dtos, sheet1);
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            logger.info("导出订单下载异常：{}", e);
            return Response.fail("导出订单异常");
        }
        return null;


    }
}