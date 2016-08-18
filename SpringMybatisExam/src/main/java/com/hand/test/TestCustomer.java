package com.hand.test;

import java.util.Date;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.domain.Customer;
import com.hand.domain.Film;
import com.hand.service.AddressService;
import com.hand.service.CustomerService;
import com.hand.service.FilmService;

public class TestCustomer {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		customerOpertor(ctx);
		filmAopOpertor(ctx);
		deleteCustomerById(ctx);

	}

	private static void deleteCustomerById(ApplicationContext ctx) {
		CustomerService service = (CustomerService) ctx.getBean("customerServiceImpl");
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入一个您要删除的customerId值：");
		String customerId = scan.nextLine();
		while (!isShort(customerId)) {
			System.out.println("您输入的address_id不是数字类型请重新输入(退出请输入exit)：");
			customerId = scan.nextLine();
			if ("exit".equals(customerId)) {
				break;
			}
		}
		if (isShort(customerId)) {
			while (!service.deleteCustomerById(Short.parseShort(customerId))) {
				System.out.println("customerId不存在请重新输入(退出请输入exit)：");
				customerId = scan.nextLine();
				if ("exit".equals(customerId)) {
					break;
				}
			}
			System.out.println("删除成功！");
		}
	}

	private static void filmAopOpertor(ApplicationContext ctx) {
		FilmService filmService = (FilmService) ctx.getBean("filmAop");
		Film film = new Film();
		film.setDescription("aaa");
		film.setLanguageId((byte) 2);
		System.out.println("----------AOP模块---------");
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入title值：");
		film.setTitle(scan.nextLine());
		if (filmService.addFilm(film)) {
			System.out.println("插入成功！");
		} else {
			System.out.println("插入失败!");
		}

	}

	private static void customerOpertor(ApplicationContext ctx) {
		AddressService addressService = (AddressService) ctx.getBean("addressServiceImpl");
		CustomerService customerService = (CustomerService) ctx.getBean("customerServiceImpl");

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
		Customer customer = new Customer();

		customer.setStoreId((byte) 1);
		customer.setAddressId(Short.parseShort(addressId));
		customer.setFirstName(fristName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setCreateDate(createDate);

		// 插入数据
		if (customerService.addCustomer(customer)) {
			System.out.println("插入成功！");
		} else {
			System.out.println("插入失败");
			System.out.println("外键限制");
		}

		System.out.println("刚刚插入的数据是");
		Customer customer2 = customerService.getLastCustomer();

		System.out.println("first_name: " + customer2.getFirstName());
		System.out.println("last_name: " + customer2.getLastName());
		System.out.println("email: " + customer2.getEmail());
		System.out.println("address: " + addressService.getAddressById(customer2.getAddressId()));
		System.out.println("create_date: " + customer2.getCreateDate());
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
