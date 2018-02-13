package com.sc.dmh.mapper;

import com.sc.dmh.beans.TabCz;
import com.sc.dmh.beans.TabCzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabCzMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int countByExample(TabCzExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int deleteByExample(TabCzExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer czId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int insert(TabCz record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int insertSelective(TabCz record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    List<TabCz> selectByExample(TabCzExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    TabCz selectByPrimaryKey(Integer czId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TabCz record, @Param("example") TabCzExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TabCz record, @Param("example") TabCzExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TabCz record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cz
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TabCz record);
}