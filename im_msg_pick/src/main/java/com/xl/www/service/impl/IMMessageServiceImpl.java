package com.xl.www.service.impl;

import com.xl.www.dao.IMMessageDao;
import com.xl.www.domain.IMMessage;
import com.xl.www.service.IMMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务逻辑实现类
 *
 */
@Service
public class IMMessageServiceImpl implements IMMessageService {

    @Autowired
    private IMMessageDao immessageDao;

    @Override
    public List<String> findAllLikeMessage(String pattern){
        return immessageDao.findAllLikeMessage(pattern);
    }
}

