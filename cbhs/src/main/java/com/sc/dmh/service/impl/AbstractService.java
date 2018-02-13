package com.sc.dmh.service.impl;
import java.io.Serializable;
import java.util.List;

import com.sc.dmh.mapper.BaseMapper;
import com.sc.dmh.service.inter.BaseService;


public abstract  class AbstractService<T1,T2, ID extends Serializable> implements BaseService<T1,T2, ID> {
	
	  private BaseMapper<T1,T2, ID> baseMapper;
	  
	  public void setBaseMapper(BaseMapper<T1, T2, ID> baseMapper) {
	    this.baseMapper = baseMapper;
	  }
	
	@Override
	public int countByExample(T2 example) {
		
		return baseMapper.countByExample(example);
	}
	@Override
	public int deleteByExample(T2 example) {
		
		return baseMapper.deleteByExample(example);
	}
	@Override
	public int deleteByPrimaryKey(ID id) {
		
		return baseMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(T1 record) {
		
		return baseMapper.insert(record);
	}
	@Override
	public int insertSelective(T1 record) {
		
		return baseMapper.insertSelective(record);
	}
	@Override
	public List<T1> selectByExample(T2 example) {
		
		return baseMapper.selectByExample(example);
	}
	@Override
	public T1 selectByPrimaryKey(ID id) {
		
		return baseMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByExampleSelective(T1 record, T2 example) {
		
		return baseMapper.updateByExampleSelective(record, example);
	}
	@Override
	public int updateByExample(T1 record, T2 example) {
		
		return baseMapper.updateByExample(record, example);
	}
	@Override
	public int updateByPrimaryKeySelective(T1 record) {
		
		return baseMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKey(T1 record) {
		
		return baseMapper.updateByPrimaryKey(record);
	}
	
  
}