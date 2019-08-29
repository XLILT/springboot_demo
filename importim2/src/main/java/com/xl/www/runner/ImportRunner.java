package com.xl.www.runner;

import com.xl.www.dao.im.ImDao;
import com.xl.www.dao.imstuck.ImStuckDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ImportRunner implements CommandLineRunner {
    @Autowired
    ImDao imDao;

    @Autowired
    ImStuckDao imStuckDao;

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0; i < 16; i++) {
            imStuckDao.insertMessages(imDao.getMessageWithStuck("t_live_messages_" + i));
        }

        // System.out.println(imStuckDao);
    }
}
