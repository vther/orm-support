package com.vther.orm.support.core.config;

import com.vther.orm.support.core.type.OrmType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(LockManagerConfigurationOrmSelector.class)
@interface EnableLockManager {
    String ormType() default OrmType.JPA;
}