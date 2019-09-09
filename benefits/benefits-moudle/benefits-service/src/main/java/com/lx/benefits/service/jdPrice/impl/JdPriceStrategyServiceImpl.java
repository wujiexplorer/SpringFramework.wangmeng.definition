package com.lx.benefits.service.jdPrice.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyDto;
import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyLineDto;
import com.lx.benefits.bean.dto.jdPrice.JdPriceStrategyReq;
import com.lx.benefits.bean.entity.jdPrice.JdPriceStrategy;
import com.lx.benefits.bean.entity.jdPrice.JdPriceStrategyLine;
import com.lx.benefits.bean.util.BeansUtils;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.mapper.jdPrice.JdPriceStrategyLineMapper;
import com.lx.benefits.mapper.jdPrice.JdPriceStrategyMapper;
import com.lx.benefits.service.jdPrice.JdPriceStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: fan
 * Date: 2019/03/04
 * Time: 23:35
 */
@Service
public class JdPriceStrategyServiceImpl implements JdPriceStrategyService {

    @Autowired
    JdPriceStrategyMapper strategyMapper;

    @Autowired
    JdPriceStrategyLineMapper strategyLineMapper;

    @Override
    public JSONObject delete(Long id) {
        if (strategyMapper.getStrategyById(id) == null) {
            return Response.fail("调价策略不存在");
        }
        if (strategyMapper.delete(id) > 0) {
            return Response.succ();
        }
        return Response.fail("删除失败");
    }

    @Override
    public JSONObject insert(JdPriceStrategyDto record, String userName) {
        record.setId(null);
        JdPriceStrategy strategy = BeansUtils.copyProperties(record, JdPriceStrategy.class);
        try {
            strategy.setCreateUser(userName);
            strategy.setUpdateUser(userName);
            strategy.setCreateTime(new Date());
            strategy.setUpdateTime(new Date());
            int row = 0;
            if (strategyMapper.insert(strategy) > 0) {
                for (JdPriceStrategyLineDto lineDto : record.getStrategy()) {
                    JdPriceStrategyLine line = BeansUtils.copyProperties(lineDto, JdPriceStrategyLine.class);
                    line.setStrategyId(strategy.getId());
                    line.setUpdateTime(new Date());
                    line.setCreateTime(new Date());
                    row = row +strategyLineMapper.insert(line);
                }
                if (row == record.getStrategy().size()) {
                    return Response.succ();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        strategyMapper.delete(strategy.getId());
        strategyLineMapper.deletes(strategy.getId());
        return Response.fail("操作失败");
    }

    @Override
    public JSONObject getStrategyById(Long id) {
        JdPriceStrategy strategy = strategyMapper.getStrategyById(id);
        JdPriceStrategyDto dto = BeansUtils.copyProperties(strategy, JdPriceStrategyDto.class);
        if (dto == null) {
            Response.succ();
        }
        List<JdPriceStrategyLine> lines = strategyLineMapper.getStrategyLine(strategy.getId());
        List<JdPriceStrategyLineDto> lineDtos = BeansUtils.copyArrayProperties(lines, JdPriceStrategyLineDto.class);
        dto.setStrategy(lineDtos);
        return Response.succ(dto);
    }

    @Override
    public JSONObject getStrategyList(JdPriceStrategyReq record) {
        JSONObject jsonObject = new JSONObject();
        record.setPage(record.getPage() > 0 ? (record.getPage() - 1) * record.getPageSize() : 0);
        List<JdPriceStrategy> list = strategyMapper.getStrategyList(record);
        List<JdPriceStrategyDto> dtoList = BeansUtils.copyArrayProperties(list, JdPriceStrategyDto.class);
        for (JdPriceStrategyDto dto : dtoList) {
            List<JdPriceStrategyLine> lines = strategyLineMapper.getStrategyLine(dto.getId());
            List<JdPriceStrategyLineDto> lineDtos = BeansUtils.copyArrayProperties(lines, JdPriceStrategyLineDto.class);
            dto.setStrategy(lineDtos);
        }
        Integer row = strategyMapper.getStrategyListCount(record);
        jsonObject.put("list", dtoList);
        jsonObject.put("count", row);
        return jsonObject;
    }

    @Override
    public JSONObject update(JdPriceStrategyDto record, String userName) {
        if (strategyMapper.getStrategyById(record.getId()) == null) {
            return Response.fail("调价策略不存在");
        }

         record.setUpdateTime(new Date());
         JdPriceStrategy strategy = BeansUtils.copyProperties(record, JdPriceStrategy.class);
        strategy.setUpdateUser(userName);
        if (strategyMapper.update(strategy) < 1) {
            return Response.fail("操作失 败");
        }
        strategyLineMapper.deletes(record.getId());
        for (JdPriceStrategyLineDto lineDto : record.getStrategy()) {
            JdPriceStrategyLine line = BeansUtils.copyProperties(lineDto, JdPriceStrategyLine.class);
            line.setStrategyId(record.getId());
            line.setUpdateTime(new Date());
            strategyLineMapper.insert(line);
        }
        return Response.succ();
    }
}
