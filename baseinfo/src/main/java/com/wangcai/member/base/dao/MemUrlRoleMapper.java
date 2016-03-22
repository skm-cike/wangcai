package com.wangcai.member.base.dao;

import com.wangcai.member.base.vo.MemUrlRoleExample;
import com.wangcai.member.base.vo.MemUrlRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemUrlRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    int countByExample(MemUrlRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    int deleteByExample(MemUrlRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    int deleteByPrimaryKey(MemUrlRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    int insert(MemUrlRoleKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    int insertSelective(MemUrlRoleKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    List<MemUrlRoleKey> selectByExample(MemUrlRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    int updateByExampleSelective(@Param("record") MemUrlRoleKey record, @Param("example") MemUrlRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEM_BASE_URLROLE
     *
     * @mbggenerated Wed Apr 01 17:03:47 CST 2015
     */
    int updateByExample(@Param("record") MemUrlRoleKey record, @Param("example") MemUrlRoleExample example);
}