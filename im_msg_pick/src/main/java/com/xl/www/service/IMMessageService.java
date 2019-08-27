package com.xl.www.service;

import com.xl.www.domain.IMMessage;

import java.util.List;

/**
 * 城市业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface IMMessageService {

    /**
     *
     * @return
     */
    List<String> findAllLikeMessage(String pattern);
}
