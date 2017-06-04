package com.vther.orm.support.core.dao;

import com.vther.orm.support.core.entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {SpringJpaEclipseLinkConfig.class})
@ContextConfiguration(locations = {"classpath:spring-context-hibernate.xml"})
//@ActiveProfiles(OrmType.JPA_ECLIPSELINK)
public class CustomerDaoTest {

    private Instant start;

    @Autowired
    private CustomerDao customerDao;

    @Before
    public void before() throws Exception {
        start = Instant.now();
    }

    @After
    public void after() throws Exception {
        System.out.println("================== CustomerDaoTest end, time costs " +
                Duration.between(start, Instant.now()).getSeconds() + "s ================== ");
    }

    @Test
    public void createCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1000L);
        customer.setAge(20);
        customer.setName("PANG TOU LI");
        customer.setRegisterTime(new Date());
        customerDao.createCustomer(customer);
    }

    @Test
    public void updateCustomerName() throws Exception {
        customerDao.updateCustomerName(1000L, "PANG BEN PANG");
    }

    @Test
    public void findByCustomerId() throws Exception {
        System.out.println(customerDao.findByCustomerId(1000L));
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(customerDao.findAll());
    }

    @Test
    public void findByCustomerName() throws Exception {
        System.out.println(customerDao.findByCustomerName("PANG"));
    }

    @Test
    public void findByPage() throws Exception {
        System.out.println(customerDao.findByPage(1, 10));
    }

}

