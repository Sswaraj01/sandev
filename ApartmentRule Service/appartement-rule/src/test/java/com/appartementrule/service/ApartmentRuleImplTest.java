package com.appartementrule.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.apartmentrule.bean.ApartmentRuleDtls;
import com.apartmentrule.dao.ApartmentRuleDao;
import com.apartmentrule.exception.ApartmentRuleCustomException;
import com.apartmentrule.model.RuleDetailsEntity;
import com.apartmentrule.services.ApartmentRuleImpl;
import com.appartementrule.AppartementRuleApplicationTests;
import com.appartementrule.util.TestHelper;


public class ApartmentRuleImplTest extends AppartementRuleApplicationTests {
	
	@Mock
	ApartmentRuleDao apartmentRuleDao;
	
	@InjectMocks
	private ApartmentRuleImpl apartmentRuleService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateRule() throws ParseException {
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		Mockito.when(apartmentRuleDao.saveRule(Mockito.any(RuleDetailsEntity.class))).thenReturn(rdEntity);
		ApartmentRuleDtls apartmentRuleDtls = apartmentRuleService.createRule(TestHelper.prepareApptRuleDetailsEntityObj());
		assertEquals("App1", apartmentRuleDtls.getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testCreateRuleException() throws ParseException {
		Mockito.when(apartmentRuleDao.saveRule(Mockito.any(RuleDetailsEntity.class))).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.createRule(TestHelper.prepareApptRuleDetailsEntityObj());
	}
	
	@Test
	public void testGetRuleById() throws ParseException {
		long ruleId = 1L;
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		Mockito.when(apartmentRuleDao.findById(ruleId)).thenReturn(rdEntity);
		ApartmentRuleDtls apartmentRuleDtls = apartmentRuleService.getRulesById(ruleId);
		assertEquals("App1", apartmentRuleDtls.getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetRuleByIdException() throws ParseException {
		long ruleId = 0;
		Mockito.when(apartmentRuleDao.findById(ruleId)).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getRulesById(ruleId);
	}
	
	@Test
	public void testGetRulesByApptId() throws ParseException {
		String apartmentId = "app1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleDao.findRuleDetails(apartmentId)).thenReturn(rdEntityList);
		List<ApartmentRuleDtls> apartmentRuleDtlsList = apartmentRuleService.getRulesByApptId(apartmentId);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetRuleByApptIdException() throws ParseException {
		String appid= null;
		Mockito.when(apartmentRuleDao.findRuleDetails(appid)).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getRulesByApptId(appid);
	}
	
	@Test
	public void testGetAllRules() throws ParseException {
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleDao.findAll()).thenReturn(rdEntityList);
		List<ApartmentRuleDtls> apartmentRuleDtlsList = apartmentRuleService.getAllRules();
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetAllRulesException() throws ParseException {
		Mockito.when(apartmentRuleDao.findAll()).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getAllRules();
	}
	
	@Test
	public void testGetRulesByUnitType() throws ParseException {
		String unitType="2BHK";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleDao.getAllRulesByUnitType(unitType)).thenReturn(rdEntityList);
		List<ApartmentRuleDtls> apartmentRuleDtlsList = apartmentRuleService.getRulesByUnitType(unitType);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetRulesByUnitTypeException() throws ParseException {
		String unitType="2BHK";
		Mockito.when(apartmentRuleDao.getAllRulesByUnitType(unitType)).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getRulesByUnitType(unitType);
	}
	
	
	@Test
	public void testGetRuleByApptIdAndUnitType() throws ParseException {
		String apartmentId = "app1",unitType="2BHK";
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		Mockito.when(apartmentRuleDao.findByApptIdAndUnitType(apartmentId, unitType)).thenReturn(rdEntity);
		ApartmentRuleDtls apartmentRuleDtls = apartmentRuleService.getRuleByApptIdAndUnitType(apartmentId, unitType);
		assertEquals("App1", apartmentRuleDtls.getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetRuleByApptIdAndUnitTypeException() throws ParseException {
		String  apartmentId = "app1",unitType="2BHK";
		Mockito.when(apartmentRuleDao.findByApptIdAndUnitType(apartmentId, unitType)).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getRuleByApptIdAndUnitType(apartmentId, unitType);
	}
	
	@Test
	public void testUpdateDeleteFlag() throws ParseException {
		long ruleId = 1L;
		Mockito.when(apartmentRuleDao.updateDeleteFlag(ruleId)).thenReturn(1);
		int apartmentRuleDtls = apartmentRuleService.updateDeleteFlag(ruleId);
		assertEquals(1, apartmentRuleDtls);
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testUpdateDeleteFlagException() throws ParseException {
		long ruleId = 1L;
		Mockito.when(apartmentRuleDao.updateDeleteFlag(ruleId)).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.updateDeleteFlag(ruleId);
	}
	
	@Test
	public void testGetAllCurrentRules() throws ParseException {
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleDao.findAllCurrentRule()).thenReturn(rdEntityList);
		List<ApartmentRuleDtls> apartmentRuleDtlsList = apartmentRuleService.getAllCurrentRules();
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetAllCurrentRulesException() throws ParseException {
		Mockito.when(apartmentRuleDao.findAllCurrentRule()).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getAllCurrentRules();
	}
	
	@Test
	public void testGetAllFutureRules() throws ParseException {
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleDao.findAllFutureRules()).thenReturn(rdEntityList);
		List<ApartmentRuleDtls> apartmentRuleDtlsList = apartmentRuleService.getAllFutureRules();
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetAllFutureRulesException() throws ParseException {
		Mockito.when(apartmentRuleDao.findAllFutureRules()).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getAllFutureRules();
	}
	
	@Test
	public void testGetAllCurrentRulesByApptId() throws ParseException {
		String  apartmentId = "app1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleDao.findAllCurrentRuleByApptId(apartmentId)).thenReturn(rdEntityList);
		List<ApartmentRuleDtls> apartmentRuleDtlsList = apartmentRuleService.getAllCurrentRulesByApptId(apartmentId);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetAllCurrentRulesByApptIdException() throws ParseException {
		String  apartmentId = "app1";
		Mockito.when(apartmentRuleDao.findAllCurrentRuleByApptId(apartmentId)).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getAllCurrentRulesByApptId(apartmentId);
	}
	
	@Test
	public void testGetAllFutureRulesByApptId() throws ParseException {
		String  apartmentId = "app1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleDao.findAllFutureRulesByApptId(apartmentId)).thenReturn(rdEntityList);
		List<ApartmentRuleDtls> apartmentRuleDtlsList = apartmentRuleService.getAllFutureRulesByApptId(apartmentId);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test(expected=ApartmentRuleCustomException.class)
	public void testGetAllFutureRulesByApptIdException() throws ParseException {
		String  apartmentId = "app1";
		Mockito.when(apartmentRuleDao.findAllFutureRulesByApptId(apartmentId)).thenThrow(new ApartmentRuleCustomException("Error occurred"));
		apartmentRuleService.getAllFutureRulesByApptId(apartmentId);
	}
	
}