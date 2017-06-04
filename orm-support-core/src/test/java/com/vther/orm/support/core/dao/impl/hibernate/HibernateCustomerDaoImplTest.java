package com.vther.orm.support.core.dao.impl.hibernate;

import com.vther.orm.support.core.config.SpringJpaEclipseLinkConfig;
import com.vther.orm.support.core.dao.CustomerDao;
import com.vther.orm.support.core.type.OrmType;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringJpaEclipseLinkConfig.class})
@ActiveProfiles(OrmType.HIBERNATE)
public class HibernateCustomerDaoImplTest {
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

  /*  @Test
    public void createCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setAge(20);
        customer.setName("PANG TOU LI");
        customer.setRegisterTime(new Date());
        customerDao.createCustomer(customer);
    }*/

}