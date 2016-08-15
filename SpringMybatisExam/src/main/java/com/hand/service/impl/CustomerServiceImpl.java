package com.hand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hand.dao.CustomerDao;
import com.hand.domain.Customer;
import com.hand.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerDao customerDao;

	public boolean addCustomer(Customer customer) {
		try {
			customerDao.foreignKeyNoChecks();
			customerDao.insertCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Customer getLastCustomer() {
		return customerDao.getLastCustomer();
	}

}
