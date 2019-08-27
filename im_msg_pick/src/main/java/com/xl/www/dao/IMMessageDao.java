package com.xl.www.dao;

import org.apache.ibatis.annotations.Param;
// import com.xl.www.domain.IMMessage;

import java.util.List;

/**
 * DAO 接口类
 *
 */
public interface IMMessageDao {

    List<String> findAllLikeMessage(@Param("pattern")String pattern);
}

