package com.wangmeng.test.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangmeng.test.common.base.BaseService;
import com.wangmeng.test.common.conf.V2Config;
import com.wangmeng.test.common.file.FileUploadUtils;
import com.wangmeng.test.common.support.Convert;
import com.wangmeng.test.mapper.auto.TsysDatasMapper;
import com.wangmeng.test.model.auto.TsysDatas;
import com.wangmeng.test.model.auto.TsysDatasExample;
import com.wangmeng.test.model.custom.Tablepar;
import com.wangmeng.test.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SysDatasService implements BaseService<TsysDatas, TsysDatasExample> {
	
	
	@Autowired
	private TsysDatasMapper tsysDatasMapper;
	
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<TsysDatas> list(Tablepar tablepar, String searchTxt){
	        TsysDatasExample testExample=new TsysDatasExample();
	        testExample.setOrderByClause("id+0 desc");
	        if(searchTxt!=null&&!"".equals(searchTxt)){
	        	testExample.createCriteria().andIdLike("%"+searchTxt+"%");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<TsysDatas> list= tsysDatasMapper.selectByExample(testExample);
	        PageInfo<TsysDatas> pageInfo = new PageInfo<TsysDatas>(list);
	        return  pageInfo;
	 }

	
	@Override
	public int deleteByPrimaryKey(String ids) {
		List<String> lista= Convert.toListStrArray(ids);
		TsysDatasExample example=new TsysDatasExample();
		example.createCriteria().andIdIn(lista);
		return tsysDatasMapper.deleteByExample(example);
	}
	
	/**
	 * 文件上传文件存储到文件表
	 * @param record
	 * @param fileURL
	 * @return 主键
	 * @throws IOException 
	 */
	public String insertSelective(MultipartFile file) throws IOException {
		//文件上传获取文件名字
        String files = FileUploadUtils.upload(file);
        //补充完整url地址 
        String filesURL= V2Config.getProfile()+files;
        
		TsysDatas record=new TsysDatas();
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		record.setFilePath(filesURL);
		if(tsysDatasMapper.insertSelective(record)>0)
		{
			return record.getId();
		}
		return null;
	}
	
	@Override
	public int insertSelective(TsysDatas record) {
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		return tsysDatasMapper.insertSelective(record);
	}

	@Override
	public TsysDatas selectByPrimaryKey(String id) {
		
		return tsysDatasMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(TsysDatas record) {
		return tsysDatasMapper.updateByPrimaryKeySelective(record);
	}
	
	public int updateByPrimaryKey(TsysDatas record) {
		return tsysDatasMapper.updateByPrimaryKey(record);
	}

	
	@Override
	public int updateByExampleSelective(TsysDatas record, TsysDatasExample example) {
		
		return tsysDatasMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(TsysDatas record, TsysDatasExample example) {
		
		return tsysDatasMapper.updateByExample(record, example);
	}

	@Override
	public List<TsysDatas> selectByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.deleteByExample(example);
	}
	

	
}
