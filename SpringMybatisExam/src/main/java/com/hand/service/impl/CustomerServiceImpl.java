package com.hand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hand.dao.CustomerDao;
import com.hand.dao.PaymentDao;
import com.hand.dao.RentalDao;
import com.hand.domain.Customer;
import com.hand.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerDao customerDao;
	@Resource
	private PaymentDao paymentDao;
	@Resource
	private RentalDao rentalDao;

	@Transactional
	public boolean addCustomer(Customer customer) {
		try {
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
	
	/**
	 * 通过id删除顾客已经顾客产生的消费记录
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteCustomerById(Short id) {
		try {
			paymentDao.deletePaymentByCustomerId(id);
			rentalDao.deleteRentalByCustomerId(id);
			if (customerDao.deleteCustomerById(id)>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
		
		return false;
	}

}
