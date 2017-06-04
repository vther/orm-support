package com.vther.orm.support.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@Import(com.vther.orm.support.core.dao.impl.hibernate.HibernateCustomerDaoImpl.class)
//@ImportResource("classpath:spring-context-hibernate.xml")
//@Profile(OrmType.HIBERNATE)
//@PropertySource(value = {"classpath:orm.properties"})
//@EnableTransactionManagement
//@ComponentScan({"com.vther.orm.support.core"})
public class SpringHibernateConfig {

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.vther.orm.support.core.entity");
        sessionFactory.getHibernateProperties().setProperty("hibernate.show_sql", "true");
        sessionFactory.getHibernateProperties().setProperty("hibernate.format_sql", "true");
        sessionFactory.getHibernateProperties().setProperty("hibernate.use_sql_comments", "true");
        sessionFactory.getHibernateProperties().setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        //sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

 /*    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
                setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
               setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }*/
}