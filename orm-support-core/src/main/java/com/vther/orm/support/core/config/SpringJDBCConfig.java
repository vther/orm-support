package com.vther.orm.support.core.config;

import com.vther.orm.support.core.type.OrmType;
import org.springframework.context.annotation.*;

@Configuration
@ImportResource("classpath:spring-context-jdbc.xml")
@Profile(OrmType.JDBC)
//@EnableTransactionManagement
@ComponentScan({"com.vther.orm.support.core"})
public class SpringJDBCConfig {

}