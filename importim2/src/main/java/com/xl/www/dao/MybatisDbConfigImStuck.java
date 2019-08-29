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
@MapperScan(basePackages = {"com.xl.www.dao.imstuck"}, sqlSessionFactoryRef = "sqlSessionFactoryImStuck")
public class MybatisDbConfigImStuck {
    // 将这个对象放入Spring容器中
    @Bean(name = "dataSourceImStuck")
    @ConfigurationProperties(prefix = "spring.datasource.im-stuck")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryImStuck")
    public SqlSessionFactory sqlSessionFactoryImStuck(@Qualifier("dataSourceImStuck") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/imstuck/*.xml"));
        return bean.getObject();
    }

    @Bean("sqlSessionTemplateImStuck")
    public SqlSessionTemplate sqlSessionTemplateImStuck(
            @Qualifier("sqlSessionFactoryImStuck") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
