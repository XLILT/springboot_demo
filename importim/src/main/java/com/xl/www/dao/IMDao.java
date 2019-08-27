package com.xl.www.dao;

import com.xl.www.model.IM;

import java.util.List;

public interface IMDao {
    List<IM> getMessageWithStuck();
}
