package com.lx.benefits.service.enterprwelcome.impl;


import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.base.dto.WelcomeModuleInfoDto;
import com.lx.benefits.bean.entity.enterpruserinfo.EnterprUserInfo;
import com.lx.benefits.bean.enums.YNEnum;
import com.lx.benefits.bean.dto.admin.customized.BannerInfoDto;
import com.lx.benefits.bean.dto.admin.customized.EnterprNoticeDto;
import com.lx.benefits.bean.dto.admin.customized.ModuleOptionInfoDto;
import com.lx.benefits.bean.entity.employeecreditinfo.EmployeeCreditInfoExample;
import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfo;
import com.lx.benefits.bean.entity.enterprbannerinfo.EnterprBannerInfoExample;
import com.lx.benefits.bean.entity.enterprmenuinfo.EnterprMenuInfo;
import com.lx.benefits.bean.entity.enterprmenuinfo.EnterprMenuInfoExample;
import com.lx.benefits.bean.entity.enterprmenuinfo.EnterprMenuInfoExample.Criteria;
import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfo;
import com.lx.benefits.bean.entity.enterprmoduleinfo.EnterprModuleInfoExample;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfo;
import com.lx.benefits.bean.entity.enterprnoticeinfo.EnterprNoticeInfoExample;
import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfo;
import com.lx.benefits.bean.entity.enterprwelcomeinfo.EnterprWelcomeInfoExample;
import com.lx.benefits.bean.util.DateUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.dao.employeecreditinfo.EmployeeCreditInfoDao;
import com.lx.benefits.dao.enterprbannerinfo.EnterprBannerInfoDao;
import com.lx.benefits.dao.enterprmoduleinfo.EnterprModuleInfoDao;
import com.lx.benefits.dao.enterprnoticeinfo.EnterprNoticeInfoDao;
import com.lx.benefits.dao.enterprwelcomeinfo.EnterprWelcomeInfoDao;
import com.lx.benefits.mapper.enterprmenuinfo.EnterprMenuInfoMapper;
import com.lx.benefits.mapper.enterpruserinfo.EnterprUserInfoMapper;
import com.lx.benefits.service.enterprwelcome.EnterprWelcomeService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author by yingcai on 2018/12/18.
 */
@Service
public class EnterprWelcomeServiceImpl implements EnterprWelcomeService {

    @Autowired
    private EnterprBannerInfoDao enterprBannerInfoDao;

    @Autowired
    private EnterprWelcomeInfoDao enterprWelcomeInfoDao;

    @Autowired
    private EnterprModuleInfoDao enterprModuleInfoDao;

    @Autowired
    private EnterprNoticeInfoDao enterprNoticeInfoDao;

    @Autowired
    private EmployeeCreditInfoDao employeeCreditInfoDao;

    @Autowired
    private EnterprUserInfoMapper enterprUserInfoMapper;
    
    @Autowired
    private EnterprMenuInfoMapper enterprMenuInfoMapper;
    
    Pattern campaignPattern=Pattern.compile("festival.*?(?:\\?|&)campaignId=(\\d+)");
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setWelcome(WelcomeModuleInfoDto req) {
        Long customId = req.getCustomId();

        Long enterpriseId = req.getEnterpriseId();
        String logoPath = req.getLogoPath();
        String pageBackground = req.getPageBackground();
        String navBackground = req.getNavBackground();
        String contactPhone = req.getContactPhone();
        String contactEmail = req.getContactEmail();
        Integer wxacode = req.getWxacode();

        EnterprWelcomeInfo enterprWelcomeInfo = new EnterprWelcomeInfo();
        if(StringUtils.isNotEmpty(logoPath)) {
            enterprWelcomeInfo.setLogoPath(logoPath);
        }
        if(StringUtils.isNotEmpty(pageBackground)) {
            enterprWelcomeInfo.setPageBackground(pageBackground);
        }
        if(StringUtils.isNotEmpty(navBackground)) {
            enterprWelcomeInfo.setNavBackground(navBackground);
        }
        if(StringUtils.isNotEmpty(contactPhone)) {
            enterprWelcomeInfo.setContactPhone(contactPhone);
        }
        if(StringUtils.isNotEmpty(contactEmail)) {
            enterprWelcomeInfo.setContactEmail(contactEmail);
        }
        if(null != wxacode) {
        	enterprWelcomeInfo.setWxacode(wxacode);
        }
        
        enterprWelcomeInfo.setEnterprId(enterpriseId);
        Boolean isShowSearchBar = req.getIsShowSearchBar();
        if(null != isShowSearchBar && isShowSearchBar) {
            enterprWelcomeInfo.setIsShowSearchBar(YNEnum.Y.val());
        }else {
            enterprWelcomeInfo.setIsShowSearchBar(YNEnum.N.val());
        }
        enterprWelcomeInfo.setType(req.getType());
        if(null == customId || customId == 0) {
            customId = enterprWelcomeInfoDao.insert(enterprWelcomeInfo);
        }else {
            EnterprWelcomeInfoExample enterprWelcomeInfoExample = new EnterprWelcomeInfoExample();
            enterprWelcomeInfoExample.createCriteria().andCustomIdEqualTo(customId);
            Integer update = enterprWelcomeInfoDao.update(enterprWelcomeInfo, enterprWelcomeInfoExample);
            if (null == update || update < 1) {
                throw new RuntimeException("设置欢迎页异常!");
            }
        }
        
        if (null == customId || customId < 1) {
            return false;
        }

        List<BannerInfoDto> bannerInfoDtoList = req.getBannerInfoDtoList();
        for(BannerInfoDto bannerInfoDto : bannerInfoDtoList) {
            EnterprBannerInfo enterprBannerInfo = new EnterprBannerInfo();
            String bannerPath = bannerInfoDto.getBannerPath();
            String linkUrl = bannerInfoDto.getLinkUrl();
            String bannerTitle = bannerInfoDto.getBannerTitle();
            Integer bannerSort = bannerInfoDto.getBannerSort();
            Integer startTime = DateUtil.date2IntegerUnixTime(bannerInfoDto.getStartTime());
            startTime = null == startTime ? 0 : startTime;
            Integer endTime = DateUtil.date2IntegerUnixTime(bannerInfoDto.getEndTime());
            endTime = null == endTime ? 0 : endTime;
            Boolean isShow = bannerInfoDto.getIsShow();
            Boolean isDeleted = bannerInfoDto.getIsDeleted();
            
            enterprBannerInfo.setBannerPath(bannerPath);
            enterprBannerInfo.setLinkUrl(linkUrl);
            enterprBannerInfo.setBannerTitle(bannerTitle);
            enterprBannerInfo.setBannerSort(bannerSort);
            enterprBannerInfo.setStartTime(startTime);
            enterprBannerInfo.setEndTime(endTime);

            enterprBannerInfo.setCustomId(customId);
            if(null != isShow && isShow) {
                enterprBannerInfo.setIsShow(YNEnum.Y.val());
            }else {
                enterprBannerInfo.setIsShow(YNEnum.N.val());
            }
            if (null != isDeleted && isDeleted) {
                enterprBannerInfo.setIsDeleted(YNEnum.Y.val());
            } else {
                enterprBannerInfo.setIsDeleted(YNEnum.N.val());
            }
            Long bannerId = bannerInfoDto.getBannerId();
            enterprBannerInfo.setType(req.getType());
            enterprBannerInfo.setBannerTitleEn(bannerInfoDto.getBannerTitleEn());
            if(null == bannerId || bannerId < 1) {
                Long insert= enterprBannerInfoDao.insert(enterprBannerInfo);
                if (null == insert || insert <1) {
                    throw new RuntimeException("设置欢迎页异常!");
                }
            }else {
                enterprBannerInfo.setBannerId(bannerId);
                Integer update = enterprBannerInfoDao.updateByPrimaryKeySelective(enterprBannerInfo);
                if (null == update || update < 1) {
                    throw new RuntimeException("设置欢迎页异常!");
                }
            }
        }

        List<ModuleOptionInfoDto> moduleOptionInfoDtoList = req.getModuleOptionInfoDtoList();
        for(ModuleOptionInfoDto moduleOptionInfoDto : moduleOptionInfoDtoList) {
            String moduleName = moduleOptionInfoDto.getModuleName();
            String moduleLink = moduleOptionInfoDto.getModuleLink();
            String modulePic = moduleOptionInfoDto.getModulePic();
            String background = moduleOptionInfoDto.getBackground();
            String selectedBackground = moduleOptionInfoDto.getSelectedBackground();
            Integer moduleSort = moduleOptionInfoDto.getModuleSort();
            Integer startTime = DateUtil.date2IntegerUnixTime(moduleOptionInfoDto.getStartTime());
            Integer endTime = DateUtil.date2IntegerUnixTime(moduleOptionInfoDto.getEndTime());
            Boolean isShow = moduleOptionInfoDto.getIsShow();
            Boolean isDeleted = moduleOptionInfoDto.getIsDeleted();

            EnterprModuleInfo enterprModuleInfo = new EnterprModuleInfo();
            enterprModuleInfo.setModuleName(moduleName);
            enterprModuleInfo.setModuleLink(moduleLink);
            enterprModuleInfo.setModulePic(modulePic);
            enterprModuleInfo.setBackground(background);
            enterprModuleInfo.setSelectedBackground(selectedBackground);
            enterprModuleInfo.setModuleSort(moduleSort);
            enterprModuleInfo.setStartTime(startTime);
            enterprModuleInfo.setEndTime(endTime);
            enterprModuleInfo.setCustomId(customId);
            if(null != isShow && isShow) {
                enterprModuleInfo.setIsShow(YNEnum.Y.val());
            }else {
                enterprModuleInfo.setIsShow(YNEnum.N.val());
            }
            if (null != isDeleted && isDeleted) {
                enterprModuleInfo.setIsDeleted(YNEnum.Y.val());
            } else {
                enterprModuleInfo.setIsDeleted(YNEnum.N.val());
            }
            Long moduleId = moduleOptionInfoDto.getModuleId();
            enterprModuleInfo.setType(req.getType());
            enterprModuleInfo.setModuleNameEn(moduleOptionInfoDto.getModuleNameEn());
            if(null == moduleId || moduleId < 1) {
                Long insert = enterprModuleInfoDao.insert(enterprModuleInfo);
                if (null == insert || insert < 1) {
                    throw new RuntimeException("设置欢迎页异常!");
                }

            }else {
                enterprModuleInfo.setModuleId(moduleId);
                Integer update = enterprModuleInfoDao.updateByPrimaryKeySelective(enterprModuleInfo);
                if (null == update || update < 1) {
                    throw new RuntimeException("设置欢迎页异常!");
                }
            }
        }
        if(req.getType()==2) {
        	List<EnterprMenuInfo> enterprMenuInfoList = req.getEnterprMenuInfoList();
            for (EnterprMenuInfo enterprMenuInfo : enterprMenuInfoList) {
            	String menuName =  enterprMenuInfo.getMenuName();
            	Byte status = enterprMenuInfo.getStatus();
            	
            	EnterprMenuInfo enterprMenu = new EnterprMenuInfo();
            	enterprMenu.setCustomId(customId);
            	enterprMenu.setMenuName(menuName);
            	enterprMenu.setStatus(status);
            	
            	EnterprMenuInfoExample example = new EnterprMenuInfoExample();
                Criteria andCustomIdEqualTo = example.createCriteria().andCustomIdEqualTo(customId);
                Integer menuId = enterprMenuInfo.getMenuId();
                if(menuId == null || menuId < 1) {
                	int insert = enterprMenuInfoMapper.insert(enterprMenu);
                	if (insert < 1) {
                        throw new RuntimeException("设置菜单显示异常!");
                    }
                }else {
                	andCustomIdEqualTo.andMenuIdEqualTo(menuId);
                	int update = enterprMenuInfoMapper.updateByExampleSelective(enterprMenu, example);
                	if (update < 1) {
                        throw new RuntimeException("设置菜单显示异常!");
                    }
                }
    		}
        }
        return true;
    }

    @Override
    public WelcomeModuleInfoDto welcomeDetailByCustomId(Long enterpriseId,Integer type, boolean isFront) {
        WelcomeModuleInfoDto dto = new WelcomeModuleInfoDto();
        // 查询企业信息
        EnterprUserInfo enterprUserInfo = enterprUserInfoMapper.selectByPrimaryKey(enterpriseId);
        if (null != enterprUserInfo) {
            dto.setEnterprName(enterprUserInfo.getEnterprName());
        }
        EnterprWelcomeInfoExample enterprWelcomeInfoExample = new EnterprWelcomeInfoExample();
        enterprWelcomeInfoExample.createCriteria().andEnterprIdEqualTo(enterpriseId).andTypeEqualTo(type);
        List<EnterprWelcomeInfo> enterprWelcomeInfoList = enterprWelcomeInfoDao.find(enterprWelcomeInfoExample);
        if(null != enterprWelcomeInfoList && enterprWelcomeInfoList.size() > 0) {
            EnterprWelcomeInfo enterprWelcomeInfo = enterprWelcomeInfoList.get(0);

            EnterprBannerInfoExample enterprBannerInfoExample = new EnterprBannerInfoExample();
            enterprBannerInfoExample.createCriteria().andCustomIdEqualTo(enterprWelcomeInfo.getCustomId()).andTypeEqualTo(type);
            enterprBannerInfoExample.setOrderByClause(" bannerSort ASC ");
            List<EnterprBannerInfo> enterprBannerInfoList = enterprBannerInfoDao.find(enterprBannerInfoExample);
            List<BannerInfoDto> bannerInfoDtoList = new ArrayList<>();
            Long currentTime = DateUtil.getNowTimestamp10();
            for(EnterprBannerInfo enterprBannerInfo : enterprBannerInfoList) {
                BannerInfoDto bDto = new BannerInfoDto();
                Long bannerId = enterprBannerInfo.getBannerId();
                String bannerPath = enterprBannerInfo.getBannerPath();
                String linkUrl = enterprBannerInfo.getLinkUrl();
                String bannerTitle = enterprBannerInfo.getBannerTitle();
                Integer bannerSort = enterprBannerInfo.getBannerSort();
                Integer startTime = enterprBannerInfo.getStartTime();
                startTime = null == startTime || startTime < 1 ? null : startTime;
                Integer endTime = enterprBannerInfo.getEndTime();
                endTime = null == endTime || endTime < 1 ? null : endTime;
                Integer isShow = enterprBannerInfo.getIsShow();
                Integer isDeleted = enterprBannerInfo.getIsDeleted();

                bDto.setType(enterprBannerInfo.getType());
                bDto.setBannerId(bannerId);
                bDto.setBannerPath(bannerPath);
                bDto.setLinkUrl(linkUrl);
                bDto.setBannerTitle(bannerTitle);
                bDto.setBannerTitleEn(enterprBannerInfo.getBannerTitleEn());
                bDto.setBannerSort(bannerSort);
                bDto.setStartTime(DateUtil.unixTime2Date(startTime));
                bDto.setEndTime(DateUtil.unixTime2Date(endTime));
                bDto.setIsShow(null != isShow && isShow == 1);
                bDto.setIsDeleted(null != isDeleted && isDeleted == 1);
                if (isFront) {
                    if (bDto.getIsShow() && !bDto.getIsDeleted()) {
                        //判断时间范围
                        boolean isInRange = true;
                        if (null != startTime && currentTime < startTime) {
                            isInRange = false;
                        }
                        if (null != endTime && currentTime > endTime) {
                            isInRange = false;
                        }
                        if (isInRange) {
                            bannerInfoDtoList.add(bDto);    
                        }
                    }
                } else {
                    if (!bDto.getIsDeleted()) {
                        bannerInfoDtoList.add(bDto);    
                    }
                }
            }

//            Map<Long,EmployeeCreditInfo> creditInfoMap = new HashMap<>();
//            if (isFront) {
//                Long employeeId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
//                EmployeeCreditInfoExample employeeCreditInfoExample = new EmployeeCreditInfoExample();
//                employeeCreditInfoExample.createCriteria().andEmployeeIdEqualTo(employeeId);
//                List<EmployeeCreditInfo> creditInfoList = employeeCreditInfoDao.select(employeeCreditInfoExample);
//                for (EmployeeCreditInfo creditInfo : creditInfoList) {
//                    Long campaignId = creditInfo.getCampaignId();
//                    creditInfoMap.put(campaignId,creditInfo);
//                }
//            }

            EnterprModuleInfoExample enterprModuleInfoExample = new EnterprModuleInfoExample();
            enterprModuleInfoExample.createCriteria().andCustomIdEqualTo(enterprWelcomeInfo.getCustomId()).andTypeEqualTo(type);
            enterprModuleInfoExample.setOrderByClause(" moduleSort ASC ");
            List<EnterprModuleInfo> enterprModuleInfoList = enterprModuleInfoDao.find(enterprModuleInfoExample);

            List<ModuleOptionInfoDto> moduleOptionInfoDtoList = new ArrayList<>();
            for(EnterprModuleInfo enterprModuleInfo : enterprModuleInfoList) {
                ModuleOptionInfoDto mDto = new ModuleOptionInfoDto();
                Long moduleId = enterprModuleInfo.getModuleId();
                String moduleName = enterprModuleInfo.getModuleName();
                String moduleLink = enterprModuleInfo.getModuleLink();
                String modulePic = enterprModuleInfo.getModulePic();
                String background = enterprModuleInfo.getBackground();
                String selectedBackground = enterprModuleInfo.getSelectedBackground();
                Integer moduleSort = enterprModuleInfo.getModuleSort();
                Integer startTime = enterprModuleInfo.getStartTime();
                startTime = null == startTime || startTime < 1 ? null : startTime;
                Integer endTime = enterprModuleInfo.getEndTime();
                endTime = null == endTime || endTime < 1 ? null : endTime;
                Integer isShow = enterprModuleInfo.getIsShow();
                Integer isDeleted = enterprModuleInfo.getIsDeleted();

                mDto.setType(enterprModuleInfo.getType());
                mDto.setModuleId(moduleId);
                mDto.setModuleName(moduleName);
                mDto.setModuleNameEn(enterprModuleInfo.getModuleNameEn());
                mDto.setModuleLink(moduleLink);
                mDto.setModulePic(modulePic);
                mDto.setBackground(background);
                mDto.setSelectedBackground(selectedBackground);
                mDto.setModuleSort(moduleSort);
                mDto.setStartTime(DateUtil.unixTime2Date(startTime));
                mDto.setEndTime(DateUtil.unixTime2Date(endTime));
                mDto.setIsShow(null != isShow && isShow == 1);
                mDto.setIsDeleted(null != isDeleted && isDeleted == 1);
                if (isFront) {
					if (mDto.getIsShow()) {
						Matcher matcher = campaignPattern.matcher(moduleLink);
						if (matcher.find()) {
							Long campaignId = Long.parseLong(matcher.group(1));
							EmployeeCreditInfoExample example = new EmployeeCreditInfoExample();
							example.createCriteria().andCampaignIdEqualTo(campaignId)
									.andEmployeeIdEqualTo(SessionContextHolder.getSessionEmployeeInfo().getEmployeeId());
							int count = employeeCreditInfoDao.countByExample(example);
							if (count == 0) {
								mDto.setIsShow(false);
							}
						}
					}
                    if (mDto.getIsShow() && !mDto.getIsDeleted()) {
                        //判断时间范围
                        boolean isInRange = true;
                        if (null != endTime && currentTime > endTime) {
                            isInRange = false;
                        }
                        if (null != startTime && currentTime < startTime) {
                            isInRange = false;
                        }
                        if (isInRange) {
                            moduleOptionInfoDtoList.add(mDto);
                        }
                    }
                } else {
                    if (!mDto.getIsDeleted()) {
                        moduleOptionInfoDtoList.add(mDto);
                    }
                }
            }
            EnterprNoticeInfoExample enterprNoticeInfoExample = new EnterprNoticeInfoExample();
            enterprNoticeInfoExample.createCriteria().andEnterprIdEqualTo(enterpriseId);
            List<EnterprNoticeInfo> list = enterprNoticeInfoDao.find(enterprNoticeInfoExample);
            if (list !=null && list.size() > 0) {
                EnterprNoticeDto enterprNoticeDto = new EnterprNoticeDto();
                BeanUtils.copyProperties(list.get(0),enterprNoticeDto);

                String loginAttach = list.get(0).getLoginAttach();
                if (!StringUtils.isEmpty(loginAttach)) {
                    List<String> loginAttachList = JSONObject.parseArray(loginAttach,String.class);
                    enterprNoticeDto.setLoginAttach(loginAttachList);
                }

                String userAttach = list.get(0).getUserAttach();
                if (!StringUtils.isEmpty(userAttach)) {
                    List<String> userAttachList = JSONObject.parseArray(userAttach,String.class);
                    enterprNoticeDto.setUserAttach(userAttachList);
                }
                dto.setEnterprNoticeDto(enterprNoticeDto);
            } else {
                dto.setEnterprNoticeDto(null);
            }
            
            EnterprMenuInfoExample example = new EnterprMenuInfoExample();
            example.createCriteria().andCustomIdEqualTo(enterprWelcomeInfo.getCustomId());
            List<EnterprMenuInfo> enterprMenuInfoList = enterprMenuInfoMapper.selectByExample(example);
            dto.setEnterprMenuInfoList(enterprMenuInfoList);
            
            dto.setBannerInfoDtoList(bannerInfoDtoList);
            dto.setModuleOptionInfoDtoList(moduleOptionInfoDtoList);
            dto.setContactEmail(enterprWelcomeInfo.getContactEmail());
            dto.setContactPhone(enterprWelcomeInfo.getContactPhone());
            dto.setEnterpriseId(enterprWelcomeInfo.getEnterprId());
            dto.setCustomId(enterprWelcomeInfo.getCustomId());
            dto.setLogoPath(enterprWelcomeInfo.getLogoPath());
            dto.setNavBackground(enterprWelcomeInfo.getNavBackground());
            dto.setPageBackground(enterprWelcomeInfo.getPageBackground());
            dto.setType(enterprWelcomeInfo.getType());
            Integer isShowSearchBar = enterprWelcomeInfo.getIsShowSearchBar();
            dto.setIsShowSearchBar(Integer.valueOf(1).equals(isShowSearchBar));
            dto.setWxacode(enterprWelcomeInfo.getWxacode());
            return dto;
        }
        return null;
    }
    
}
