package com.vther.orm.support.core.dao.impl.jpa.eclipselink;

import com.vther.orm.support.core.dao.CustomerDao;
import com.vther.orm.support.core.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
//@Profile({OrmType.JPA_ECLIPSELINK, OrmType.JPA_HIBERNATE})
public class JPACustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void createCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Transactional
    @Override
    public void updateCustomerName(Long customerId, String customerName) {
        Customer customer = entityManager.find(Customer.class, customerId);
        Assert.notNull(customer, "the customer not exists, customerId=" + customerId);
        customer.setName(customerName);
        entityManager.persist(customer);
    }

    @Override
    public Customer findByCustomerId(Long customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public List<Customer> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Customer> findByCustomerName(String customerName) {
        Assert.notNull(customerName, "the customerName can not be null");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        Path<String> name = root.get("name");
        query.where(builder.like(name, "%" + customerName + "%"));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Customer> findByPage(Integer pageNo, Integer pageSize) {
        Assert.notNull(pageNo, "pageNo can not be null");
        Assert.notNull(pageSize, "pageSize can not be null");
        Assert.isTrue(pageNo > 0, "pageNo must > 0");
        Assert.isTrue(pageSize > 0, "pageSize must > 0");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        return entityManager.createQuery(query)
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageNo * pageSize)
                .getResultList();
    }
}
