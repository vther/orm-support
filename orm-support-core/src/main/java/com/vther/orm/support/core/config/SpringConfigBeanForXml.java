package com.vther.orm.support.core.config;

import com.vther.orm.support.core.type.OrmType;
import com.vther.orm.support.core.utils.SpringContextUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@EnableTransactionManagement
@EnableLockManager(ormType = OrmType.HIBERNATE)
@Import({SpringContextUtil.class}) //不同地方导入一个Bean只会注入一次
//@EnableLockManager()
public class SpringConfigBeanForXml {

    // @Import和这种方式会重复注入
//    @Bean
//    public SpringContextUtil springContextUtil() {
//        return new SpringContextUtil();
//    }
}