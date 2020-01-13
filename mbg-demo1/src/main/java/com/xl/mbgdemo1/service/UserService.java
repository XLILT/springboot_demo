package com.xl.mbgdemo1.service;

import com.xl.mbgdemo1.model.Tb1;
import com.xl.mbgdemo1.model.Tb1Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xl.mbgdemo1.dao.Tb1Mapper;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserService {
    private Tb1Mapper tb1Mapper;

    @Autowired
    public UserService(Tb1Mapper tb1Mapper) {
        this.tb1Mapper = tb1Mapper;
    }

    public Tb1 getTb1(int a) {
        log.info("{}", tb1Mapper);

        Tb1Example tb1Example = new Tb1Example();
        tb1Example.createCriteria().andAEqualTo(a);

        log.info("getOredCriteria {}", tb1Example.getOredCriteria().size());

        List<Tb1> tb1list = tb1Mapper.selectByExample(tb1Example);

        log.info("tb1list {}", tb1list.size());

        if(tb1list.size() != 1) {
            return null;
        }

        return tb1list.get(0);
    }
}
