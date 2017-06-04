package com.vther.orm.support.core.dao.impl.hibernate;

import com.vther.orm.support.core.dao.CustomerDao;
import com.vther.orm.support.core.entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Transactional //hibernate 查询也是需要事务的
@Repository
//@Profile(OrmType.HIBERNATE)
public class HibernateCustomerDaoImpl implements CustomerDao {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createCustomer(Customer customer) {
        getSession().persist(customer);
    }

    @Override
    public void updateCustomerName(Long customerId, String customerName) {
        Customer customer = (Customer) getSession().get(Customer.class, customerId);
        Assert.notNull(customer, "the customer not exists, customerId=" + customerId);
        customer.setName(customerName);
        getSession().save(customer);
    }

    @Override
    public Customer findByCustomerId(Long customerId) {
    /*    DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.eq("customerId",customerId));
        return (Customer) criteria.getExecutableCriteria(getSession()).uniqueResult();*/
        return (Customer) getSession()
                .createQuery("select c from Customer c where c.customerId = :customerId")
                .setParameter("customerId", customerId)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> findAll() {
        Criteria criteria = getSession().createCriteria(Customer.class);
        return (List<Customer>) criteria.list();
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> findByCustomerName(String customerName) {
        Assert.notNull(customerName, "the customerName can not be null");
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.like("name", "%" + customerName + "%"));
        return (List<Customer>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> findByPage(Integer pageNo, Integer pageSize) {
        Assert.notNull(pageNo, "pageNo can not be null");
        Assert.notNull(pageSize, "pageSize can not be null");
        Assert.isTrue(pageNo > 0, "pageNo must > 0");
        Assert.isTrue(pageSize > 0, "pageSize must > 0");
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageNo * pageSize);
        return (List<Customer>) criteria.list();
    }

/*
 * 	public void saveEmployee(Employee employee) {
 persist(employee);
 }

 @SuppressWarnings("unchecked")
 public List<Employee> findAllEmployees() {
 Criteria criteria = getSession().createCriteria(Employee.class);
 return (List<Employee>) criteria.list();
 }

 public void deleteEmployeeBySsn(String ssn) {
 Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
 query.setString("ssn", ssn);
 query.executeUpdate();
 }


 public Employee findBySsn(String ssn){
 Criteria criteria = getSession().createCriteria(Employee.class);
 criteria.add(Restrictions.eq("ssn",ssn));
 return (Employee) criteria.uniqueResult();
 }

 public void updateEmployee(Employee employee){
 getSession().update(employee);
 }
 */
}
