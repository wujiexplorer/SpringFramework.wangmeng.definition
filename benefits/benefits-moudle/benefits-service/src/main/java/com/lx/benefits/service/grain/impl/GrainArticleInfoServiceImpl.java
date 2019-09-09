package com.lx.benefits.service.grain.impl;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.grain.GrainArticleInfo;
import com.lx.benefits.bean.util.StringUtil;
import com.lx.benefits.dao.grain.GrainArticleInfoDao;
import com.lx.benefits.service.grain.GrainArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User:wangmeng
 * Date:2019/5/20
 * Time:13:42
 * Verision:2.x
 * Description:TODO
 **/
@Service
public class GrainArticleInfoServiceImpl implements GrainArticleInfoService {

    @Autowired
    private GrainArticleInfoDao grainArticleInfoDao;

    @Override
    public int delete(Long id) {
        if(id == null){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainArticleInfoDao.delete(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章信息删除出错！",e);
        }
    }

    @Override
    public GrainArticleInfo insert(GrainArticleInfo grainArticleInfo) {
        validate(grainArticleInfo);
        try{
            return grainArticleInfoDao.insert(grainArticleInfo);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章信息新增出错！",e);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(GrainArticleInfo grainArticleInfo) {
        try{
            return grainArticleInfoDao.updateByPrimaryKeySelective(grainArticleInfo);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章信息修改出错！",e);
        }
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoList(Map<String,Object> params) {
        try{
            return grainArticleInfoDao.findGrainArticleInfoList(params);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章查询信息列表出错",e);
        }
    }

    @Override
    public GrainArticleInfo selectByPrimaryKey(Long id) {
        if(null == id){
            throw new BusinessException("id不能为空！");
        }
        try{
            return grainArticleInfoDao.selectByPrimaryKey(id);
        }catch (Exception e){
            throw new RuntimeException("有关颗粒文章根据ID查询出错！",e);
        }
    }

    @Override
    public Integer countGrainArticleInfoList(Map<String,Object> map) {
        if(map.isEmpty()){
            throw new BusinessException("map参数不能为空！");
        }
        try{
            return grainArticleInfoDao.countGrainArticleInfoList(map);
        }catch (Exception e){
            throw new RuntimeException("统计颗粒文章信息列表行数出错！",e);
        }
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListByStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.findGrainArticleInfoListByStatus(params);
        }catch (Exception e){
            throw  new RuntimeException("根据状态查询颗粒文章信息列表出错！",e);
        }
    }

    @Override
    public Integer countGrainArticleInfoListByStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.countGrainArticleInfoListByStatus(params);
        }catch (Exception e){
            throw new RuntimeException("统计颗粒文章信息列表行数出错！",e);
        }
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListByVerifyStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.findGrainArticleInfoListByVerifyStatus(params);
        }catch (Exception e){
            throw new RuntimeException("根据审核状态查询颗粒文章信息列表出错！",e);
        }
    }

    @Override
    public Integer countGrainArticleInfoListByVerifyStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.countGrainArticleInfoListByVerifyStatus(params);
        }catch (Exception e){
            throw new RuntimeException("根据审核状态统计颗粒文章信息列表行数出错！",e);
        }
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListByOpenAwardStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.findGrainArticleInfoListByOpenAwardStatus(params);
        }catch (Exception e){
            throw new RuntimeException("查询开启奖励的颗粒文章列表出错！",e);
        }
    }

    @Override
    public List<GrainArticleInfo> findGrainArticleInfoListBySuspendOpenAwardStatus(Map<String, Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.findGrainArticleInfoListBySuspendOpenAwardStatus(params);
        }catch (Exception e){
            throw new RuntimeException("查询开启暂停奖励状态的颗粒文章列表！",e);
        }
    }

    @Override
    public Integer countGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.countGrainArticleInfoListByOpenAwardStatus(params);
        }catch (Exception e){
            throw new RuntimeException("统计颗粒文章信息列表行数出错！",e);
        }
    }

    @Override
    public Integer countGrainArticleInfoListBySuspendOpenAwardStatus(Map<String,Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.countGrainArticleInfoListBySuspendOpenAwardStatus(params);
        }catch(Exception e){
            throw new RuntimeException("统计开启暂停奖励颗粒文章信息列表行数！",e);
        }
    }

    @Override
    public Integer calculateGrainArticleInfoListByOpenAwardStatus(Map<String,Object> params) {
        if(params.isEmpty()){
            throw new BusinessException("params参数不能为空！");
        }
        try{
            return grainArticleInfoDao.calculateGrainArticleInfoListByOpenAwardStatus(params);
        }catch (Exception e){
            throw new RuntimeException("计算所有发布的颗粒文章累计阅读量！",e);
        }
    }

    private void validate(GrainArticleInfo grainArticleInfo){
        if(grainArticleInfo == null){
            throw new BusinessException("颗粒文章信息不能为空！");
        }else if(StringUtil.isEmpty(grainArticleInfo.getArticleContent())){
            throw new BusinessException("文章内容不能为空！");
        }else if(grainArticleInfo.getArticleReadTime() == null){
            throw new BusinessException("文章颗粒奖励阅读时间阈值不能为空！");
        }else if(StringUtil.isEmpty(grainArticleInfo.getArticleTitle())){
            throw new BusinessException("文章标题不能为空！");
        }else if(grainArticleInfo.getCumulationAwardValue() == null){
            throw new BusinessException("文章累计奖励值不能为空！");
        }else if(grainArticleInfo.getIsCustom() == null){
            throw new BusinessException("是否自主创作标志不能为空！");
        }else if(grainArticleInfo.getIsHot() == null){
            throw new BusinessException("是否热门文章标志不能为空！");
        }else if(StringUtil.isEmpty(grainArticleInfo.getPublishPerson())){
            throw new BusinessException("文章发布者不能为空！");
        }else if(grainArticleInfo.getPublishPersonId() == null){
            throw new BusinessException("文章发布者ID不能为空！");
        }else if(grainArticleInfo.getSingleGrainAwardValue() == null){
            throw new BusinessException("文章单次颗粒奖励值不能为空！");
        }else if(grainArticleInfo.getStatus() == null){
            throw new BusinessException("文章设置状态不能为空！");
        }else if(grainArticleInfo.getPublishTime() == null){
            throw new BusinessException("文章发布时间不能为空！");
        }
    }
}
