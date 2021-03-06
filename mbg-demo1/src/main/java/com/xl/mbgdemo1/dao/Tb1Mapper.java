package com.xl.mbgdemo1.dao;

import com.xl.mbgdemo1.model.Tb1;
import com.xl.mbgdemo1.model.Tb1Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb1Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    long countByExample(Tb1Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    int deleteByExample(Tb1Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    int deleteByPrimaryKey(Integer a);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    int insert(Tb1 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    int insertSelective(Tb1 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    List<Tb1> selectByExample(Tb1Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    int updateByExampleSelective(@Param("record") Tb1 record, @Param("example") Tb1Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb1
     *
     * @mbg.generated Fri Jan 10 16:21:52 CST 2020
     */
    int updateByExample(@Param("record") Tb1 record, @Param("example") Tb1Example example);
}