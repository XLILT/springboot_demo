package com.xl.www.dao.im;

import com.xl.www.model.Im;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImDao {
    List<Im> getMessageWithStuck(@Param("tableName") String tableName);
}
