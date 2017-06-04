package com.vther.orm.support.core.config;

import com.vther.orm.support.core.type.OrmType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
@ImportResource("classpath:spring-context-mybatis.xml")
@Profile(OrmType.MYBATIS)
//@PropertySource(value = {"classpath:orm.properties"})
//@EnableTransactionManagement
@ComponentScan({"com.vther.orm.support.core"})
@MapperScan("com.vther.orm.support.core.dao")
public class SpringMybatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        //sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

        Resource mapperResource = new ClassPathResource("mapper/CustomerDao.xml");
        sqlSessionFactory.setMapperLocations(new Resource[] {mapperResource});
        //sqlSessionFactory.setTypeHandlersPackage("org.horiga.study.mybatis.typehandler");
        return sqlSessionFactory.getObject();
    }
}

//    @Bean
//    MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("com.vther.orm.support.core.dao");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        return mapperScannerConfigurer;
//    }
//}