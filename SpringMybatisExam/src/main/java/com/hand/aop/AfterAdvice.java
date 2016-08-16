package com.hand.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterAdvice implements AfterReturningAdvice{

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("After Insert Customer Data");
	}


}
