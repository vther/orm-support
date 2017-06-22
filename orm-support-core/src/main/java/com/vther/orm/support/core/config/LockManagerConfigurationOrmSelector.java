package com.vther.orm.support.core.config;

import com.vther.orm.support.core.type.OrmType;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;


public class LockManagerConfigurationOrmSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableLockManager.class.getName(), false));
        if (attributes.isEmpty()) {
            return new String[]{"com.vther.orm.support.core.config.SpringJpaEclipseLinkConfig"};
        }
        String criteria = attributes.getString("ormType");
        switch (criteria) {
            case OrmType.HIBERNATE:
                return new String[]{"com.vther.orm.support.core.config.SpringHibernateConfig"};
            case OrmType.MYBATIS:
                return new String[]{"com.vther.orm.support.core.config.SpringHibernateConfig"};
            case OrmType.JPA_HIBERNATE:
                return new String[]{"com.vther.orm.support.core.config.SpringHibernateConfig"};
            case OrmType.JPA_ECLIPSELINK:
            default:
                return new String[]{"com.vther.orm.support.core.config.SpringJpaEclipseLinkConfig"};
        }
    }
}