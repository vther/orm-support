package com.vther.orm.support.jpa.dao.impl;

import com.vther.orm.support.jpa.dao.JPATestEntityDao;
import com.vther.orm.support.jpa.entity.JPATestEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JPATestEntityDaoImpl implements JPATestEntityDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void create(JPATestEntity entity) {
        entityManager.persist(entity);
    }
}
