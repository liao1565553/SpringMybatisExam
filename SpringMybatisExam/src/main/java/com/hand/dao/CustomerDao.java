package com.hand.dao;

import com.hand.domain.Customer;

public interface CustomerDao {
	public void insertCustomer(Customer customer);
	public void foreignKeyNoChecks();
}
