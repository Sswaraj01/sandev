package com.appartementrule.model;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.apartmentrule.model.RuleDetailsEntity;

public class RuleEntityTest {
	
	@InjectMocks
	RuleDetailsEntity rdEntity;
	
	@Test
	public void testRuleEntity(){
		rdEntity=new RuleDetailsEntity();
		rdEntity.setDeleted("No");
		rdEntity.getDeleted();
	}
}
