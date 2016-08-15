package com.hand.test;

import java.util.Date;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.domain.Customer;
import com.hand.service.AddressService;
import com.hand.service.CustomerService;

// @ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class TestCustomer {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AddressService addressService = (AddressService) ctx.getBean("addressServiceImpl");
		CustomerService customerService=(CustomerService) ctx.getBean("customerServiceImpl");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入frist_name：");
		String fristName = scan.nextLine();
		System.out.println("请输入last_name：");
		String lastName = scan.nextLine();
		System.out.println("请输入email：");
		String email = scan.nextLine();

		System.out.println("请输入address_id：");
		String addressId = scan.nextLine();

		while (!isShort(addressId)) {
			System.out.println("您输入的address_id不是数字类型请重新输入(退出请输入exit)：");
			addressId = scan.nextLine();
			if ("exit".equals(addressId)) {
				break;
			}
		}
		if (isShort(addressId)) {
			while (!addressService.isExistAid(Short.parseShort(addressId))) {
				System.out.println("address_id不存在请重新输入(退出请输入exit)：");
				addressId = scan.nextLine();
				if ("exit".equals(addressId)) {
					break;
				}
			}
		}
		Date createDate = new Date(System.currentTimeMillis());
		Customer customer=new Customer();
		
		customer.setStoreId((byte) 1);
		customer.setAddressId((short) 1);
		customer.setFirstName(fristName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setCreateDate(createDate);
		
		//插入数据
		if (customerService.addCustomer(customer)) {
			System.out.println("插入成功！");
		}else {
			System.out.println("插入失败");
			System.out.println("外键限制");
		}
		
	}

	public static boolean isShort(String value) {
		try {
			Short.parseShort(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
