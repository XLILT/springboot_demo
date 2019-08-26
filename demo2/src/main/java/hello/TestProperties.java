/*************************************************************************
* COPYRIGHT NOTICE
*  Copyright (c) 2019, MXL
*  All rights reserved.
*
*  @version : 1.0
*  @author : mxl
*  @E-mail : xiaolongicx@gmail.com
*  @date : 2019-08-26 15:52
*
*  Revision Notes :
*/

package hello;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Component
// 单独存放文件并指定encoding解决utf8编码问题
@PropertySource(value="classpath:value.properties", encoding="UTF-8")
@ConfigurationProperties(prefix="demo.value")
public class TestProperties {

    /**
     * 书名
     */
    // @Value("${demo.value.name}")
    private String name;

    /**
     * 作者
     */
    // @Value("${demo.value.writer}")
    private String writer;

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String w) {
        writer = w;
    }
}

