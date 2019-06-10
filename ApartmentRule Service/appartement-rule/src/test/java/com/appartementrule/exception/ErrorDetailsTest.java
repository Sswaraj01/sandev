package com.appartementrule.exception;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.apartmentrule.exception.ErrorDetails;

public class ErrorDetailsTest {
	
	@InjectMocks
	ErrorDetails errorDtls;
	
	@Test
	public void testErrorDetails(){
		ErrorDetails errorDtls=new ErrorDetails("01", "Error Occured");
		ErrorDetails errorDtls1= new ErrorDetails();
		errorDtls.getErrorCode();
		errorDtls.getErrorMsg();
		errorDtls1.setErrorCode("01");
		errorDtls1.setErrorMsg("Error Occured");
	}
	

}
