package com.xl.mbgdemo1.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xl.mbgdemo1.dao")
public class MyBatisHelper {
}
