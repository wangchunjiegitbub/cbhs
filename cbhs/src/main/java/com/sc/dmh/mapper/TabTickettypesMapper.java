package com.sc.dmh.mapper;

import com.sc.dmh.beans.TabTickettypes;
import com.sc.dmh.beans.TabTickettypesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabTickettypesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int countByExample(TabTickettypesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int deleteByExample(TabTickettypesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer tickettypesId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int insert(TabTickettypes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int insertSelective(TabTickettypes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    List<TabTickettypes> selectByExample(TabTickettypesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    TabTickettypes selectByPrimaryKey(Integer tickettypesId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TabTickettypes record, @Param("example") TabTickettypesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TabTickettypes record, @Param("example") TabTickettypesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TabTickettypes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_tickettypes
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TabTickettypes record);
}