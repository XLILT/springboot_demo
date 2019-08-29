package com.xl.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Hello world!
 *
 */

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
// @MapperScan({"com.xl.www.dao.im", "com.xl.www.dao.imstuck"})
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
