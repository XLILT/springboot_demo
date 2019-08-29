package com.xl.www.dao;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.xl.www.dao.im"}, sqlSessionFactoryRef = "sqlSessionFactoryIm")
public class MybatisDbAConfigIm {
    // 将这个对象放入Spring容器中
    @Bean(name = "dataSourceIm")
    @ConfigurationProperties(prefix = "spring.datasource.im")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryIm")
    public SqlSessionFactory sqlSessionFactoryIm(@Qualifier("dataSourceIm") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/im/*.xml"));
        return bean.getObject();
    }

    @Bean("sqlSessionTemplateIm")
    public SqlSessionTemplate sqlSessionTemplateIm(
            @Qualifier("sqlSessionFactoryIm") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
