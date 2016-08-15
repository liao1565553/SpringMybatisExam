package com.hand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hand.dao.AddressDao;
import com.hand.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Resource
	private AddressDao addressDao;

	public boolean isExistAid(Short id) {
		if (addressDao.findAddressById(id) != null) {
			return true;
		}
		return false;
	}
}
