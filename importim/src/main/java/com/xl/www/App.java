package com.xl.www;

import com.xl.www.dao.IMDao;
import com.xl.www.dao.NewIMDao;
import com.xl.www.model.IM;
import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

/**
 * Hello world!
 *
 */
// @SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration(exclude = {
        NewIMDao.class
})
@MapperScan("com.xl.www.dao")
@ComponentScan
public class App  implements CommandLineRunner
{
    @Autowired
    IMDao imdao;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(imdao);
        List<IM> imlist = imdao.getMessageWithStuck();

        imlist.forEach((m) -> System.out.println(m.getLive_id() + " "));
    }
}
