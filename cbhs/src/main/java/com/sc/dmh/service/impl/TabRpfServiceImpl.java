package com.sc.dmh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabRpf;
import com.sc.dmh.beans.TabRpfExample;

import com.sc.dmh.mapper.TabRpfMapper;
import com.sc.dmh.service.inter.TabRpfServiceI;



@Service
@Transactional
public class TabRpfServiceImpl implements TabRpfServiceI {
	
	@Autowired
	private TabRpfMapper tabRpfMapper;
	
	public void setTabRpfMapper(TabRpfMapper tabRpfMapper) {
		this.tabRpfMapper = tabRpfMapper;
	}

	@Override
	public int countByExample(TabRpfExample example) {
		
		return tabRpfMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TabRpfExample example) {
		
		return tabRpfMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer rpfId) {
		
		return tabRpfMapper.deleteByPrimaryKey(rpfId);
	}

	@Override
	public int insert(TabRpf record) {
		
		return tabRpfMapper.insert(record);
	}

	@Override
	public int insertSelective(TabRpf record) {
		
		return tabRpfMapper.insertSelective(record);
	}

	@Override
	public List<TabRpf> selectByExample(TabRpfExample example) {
		
		return tabRpfMapper.selectByExample(example);
	}

	@Override
	public TabRpf selectByPrimaryKey(Integer rpfId) {
		
		return tabRpfMapper.selectByPrimaryKey(rpfId);
	}

	@Override
	public int updateByExampleSelective(TabRpf record, TabRpfExample example) {
		
		return tabRpfMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(TabRpf record, TabRpfExample example) {
		
		return tabRpfMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(TabRpf record) {
		
		return tabRpfMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TabRpf record) {
		
		return tabRpfMapper.updateByPrimaryKey(record);
	}

	
}
