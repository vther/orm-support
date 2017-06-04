package com.vther.orm.support.core.config;

import com.vther.orm.support.core.type.Cons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Import(com.vther.orm.support.core.dao.impl.jpa.eclipselink.JPACustomerDaoImpl.class)
//@ImportResource("classpath:spring-context-jpa.xml")
//@Profile(OrmType.JPA_ECLIPSELINK)
//@PropertySource(value = {"classpath:orm.properties"})
//@EnableTransactionManagement
//@ComponentScan({"com.vther.orm.support.core"})
//@ActiveProfiles({OrmType.JPA_ECLIPSELINK})
public class SpringJpaEclipseLinkConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SpringJpaEclipseLinkConfig.class);

    @Value("${" + Cons.JAP_ECLIPSELINK.ENTITY_PACKAGES + "}")
    private String entityPackages;

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
        final EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);

        LOG.info("load properties,entityPackages={}", entityPackages);
        List<String> list = new ArrayList<>();
        list.add("com.vther.orm.support.core.entity");
        if (StringUtils.hasText(entityPackages)) {
            list.addAll(Arrays.asList(entityPackages.split(Cons.SEPARATOR)));
        }
        String[] array = new String[list.size()];
        list.toArray(array);
        factory.setPackagesToScan(array);
        factory.getJpaPropertyMap().put("eclipselink.weaving", "false");
        factory.getJpaPropertyMap().put("eclipselink.logging.level.sql", "FINE");
        factory.getJpaPropertyMap().put("eclipselink.logging.parameters", "true");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        //factory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        //factory.setPersistenceUnitName("TEST_Persistence");
        return factory;
    }

}