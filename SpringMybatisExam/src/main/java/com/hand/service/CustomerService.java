package com.hand.service;

import com.hand.domain.Customer;

public interface CustomerService {
	public boolean addCustomer(Customer customer);
	public Customer getLastCustomer();
	public boolean deleteCustomerById(Short id);
}
