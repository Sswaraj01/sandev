package com.appartementrule.exception;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import com.apartmentrule.exception.ApartmentRuleCustomException;
import com.apartmentrule.exception.ExceptionHandlerAdvice;

public class ExceptionHandlerAdviceTest {
	
	
	private ExceptionHandlerAdvice expAdvice=new ExceptionHandlerAdvice();
	
	@Test
	public void testHandleNotFoundException(){
		ApartmentRuleCustomException cusExp=new ApartmentRuleCustomException("Error Occured");
		expAdvice.handleNotFoundException(cusExp);
		
	}

}
