package com.hand.dao;

import com.hand.domain.Customer;

public interface CustomerDao {
	public void insertCustomer(Customer customer);
	public void foreignKeyNoChecks();
	public Customer getLastCustomer();
	public Customer getCustomerByID(Short id);
	public int deleteCustomerById(Short id);
}
