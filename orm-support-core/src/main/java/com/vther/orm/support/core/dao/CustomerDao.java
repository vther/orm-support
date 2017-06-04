package com.vther.orm.support.core.dao;


import com.vther.orm.support.core.entity.Customer;

import java.util.List;

public interface CustomerDao {
    void createCustomer(Customer customer);

    void updateCustomerName(Long customerId, String customerName);

    Customer findByCustomerId(Long customerId);

    List findAll();

    List<Customer> findByCustomerName(String customerName);

    List<Customer> findByPage(Integer pageNo, Integer pageSize);
}
