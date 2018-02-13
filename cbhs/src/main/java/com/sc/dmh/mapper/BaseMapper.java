package com.sc.dmh.mapper;
import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T1,T2,ID extends Serializable> {
  
  
  int countByExample(T2 example);

  
  int deleteByExample(T2 example);

  int deleteByPrimaryKey(ID id);

  int insert(T1 record);

  int insertSelective(T1 record);

  List<T1> selectByExample(T2 example);

  T1 selectByPrimaryKey(ID id);

  int updateByExampleSelective(@Param("record") T1 record, @Param("example") T2 example);

  int updateByExample(@Param("record") T1 record, @Param("example") T2 example);

  int updateByPrimaryKeySelective(T1 record);

  int updateByPrimaryKey(T1 record);
  
  
}