package com.xl.www.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Runner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // System.out.println(imStuckDao);
        // System.out.println(Thread.currentThread().getId());

        log.info("{}", Thread.currentThread().getId());
        Thread.sleep(1000);
        log.info("{}", Thread.currentThread().getId());
    }
}
