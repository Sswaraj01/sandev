package com.appartementrule.configuration;

import org.junit.Test;

import com.apartmentrule.configuration.SwaggerConfig;

public class SwaggerConfigTest {
	
	private SwaggerConfig swaggerConfig =new SwaggerConfig();
	
	@Test
	public void testApi(){
		swaggerConfig.api();
	}
}
