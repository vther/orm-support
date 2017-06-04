package com.vther.orm.support.jpa.dao;

import com.vther.orm.support.core.type.OrmType;
import com.vther.orm.support.jpa.entity.JPATestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {SpringConfig.class})
@ContextConfiguration(locations = {"classpath:spring-context-jpa.xml"})
@ActiveProfiles(OrmType.JPA_ECLIPSELINK)
public class JPATestEntityDaoTest {


    @Autowired
    private JPATestEntityDao jpaTestEntityDao;

    @Test
    public void create() throws Exception {
        JPATestEntity entity = new JPATestEntity();
        entity.setId(1000L);
        entity.setName("this is a test name");
        jpaTestEntityDao.create(entity);
    }

}