package com.sc.dmh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sc.dmh.beans.CbhsUser;
import com.sc.dmh.beans.CbhsUserExample;
import com.sc.dmh.mapper.CbhsUserMapper;

import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.to.CustomerContextHolder;


@Service
@Transactional
public class UserServiceImpl implements UserServiceI {
	
	@Autowired
	private CbhsUserMapper cbhsUserMapper;
	
	public void setCbhsUserMapper(CbhsUserMapper cbhsUserMapper) {
		this.cbhsUserMapper = cbhsUserMapper;
	}

	@Override
	public int countByExample(CbhsUserExample example) {
		
		return cbhsUserMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(CbhsUserExample example) {
		
		return cbhsUserMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		
		return cbhsUserMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int insert(CbhsUser record) {
		
		return cbhsUserMapper.insert(record);
	}

	@Override
	public int insertSelective(CbhsUser record) {
		
		return cbhsUserMapper.insertSelective(record);
	}

	@Override
	public List<CbhsUser> selectByExample(CbhsUserExample example) {
		//修改数据库
//		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_MSSQL);
		return cbhsUserMapper.selectByExample(example);
	}

	@Override
	public CbhsUser selectByPrimaryKey(Integer userId) {
		
		return cbhsUserMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByExampleSelective(CbhsUser record, CbhsUserExample example) {
		
		return cbhsUserMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(CbhsUser record, CbhsUserExample example) {
		
		return cbhsUserMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(CbhsUser record) {
		
		return cbhsUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CbhsUser record) {
		
		return cbhsUserMapper.updateByPrimaryKey(record);
	}

}
