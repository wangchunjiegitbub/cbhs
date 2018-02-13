package com.sc.dmh.mapper;

import com.sc.dmh.beans.TabCbfp;
import com.sc.dmh.beans.TabCbfpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabCbfpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int countByExample(TabCbfpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int deleteByExample(TabCbfpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer cbfpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int insert(TabCbfp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int insertSelective(TabCbfp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    List<TabCbfp> selectByExample(TabCbfpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    TabCbfp selectByPrimaryKey(Integer cbfpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TabCbfp record, @Param("example") TabCbfpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TabCbfp record, @Param("example") TabCbfpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TabCbfp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tab_cbfp
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TabCbfp record);
}