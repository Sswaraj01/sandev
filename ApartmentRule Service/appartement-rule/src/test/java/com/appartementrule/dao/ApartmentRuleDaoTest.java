package com.appartementrule.dao;

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
import com.apartmentrule.repository.ApartmentRuleRepository;
import com.appartementrule.util.TestHelper;

public class ApartmentRuleDaoTest {

	@Mock
	ApartmentRuleRepository apartmentRuleRepo;
	
	@InjectMocks
	ApartmentRuleDao apartmentRuleDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindRuleDetails() throws ParseException{
		String apartmentId = "app1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleRepo.findRuleDetails(apartmentId)).thenReturn(rdEntityList);
		List<RuleDetailsEntity> apartmentRuleDtlsList = apartmentRuleDao.findRuleDetails(apartmentId);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test
	public void testUpdateDeleteFlag() throws ParseException{
		long ruleId = 1L;
		Mockito.when(apartmentRuleRepo.updateDeleteFlag(ruleId)).thenReturn(1);
		int apartmentRuleDtlsList = apartmentRuleDao.updateDeleteFlag(ruleId);
		assertEquals(1, apartmentRuleDtlsList);
	}

	@Test
	public void testfindById() throws ParseException{
		long ruleId = 1L;
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		Mockito.when(apartmentRuleRepo.findById(ruleId)).thenReturn(rdEntity);
		RuleDetailsEntity apartmentRuleDtls = apartmentRuleDao.findById(ruleId);
		assertEquals("App1", apartmentRuleDtls.getApartmentId());
	}
	
	@Test
	public void testFindAll() throws ParseException{
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleRepo.findAll()).thenReturn(rdEntityList);
		List<RuleDetailsEntity> apartmentRuleDtlsList = apartmentRuleDao.findAll();
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test
	public void testGetAllRulesByUnitType() throws ParseException{
		String unitType = "2BHK";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleRepo.getAllRulesByUnitType(unitType)).thenReturn(rdEntityList);
		List<RuleDetailsEntity> apartmentRuleDtlsList = apartmentRuleDao.getAllRulesByUnitType(unitType);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test
	public void testSaveRule() throws ParseException{
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		Mockito.when(apartmentRuleRepo.save(Mockito.any(RuleDetailsEntity.class))).thenReturn(rdEntity);
		RuleDetailsEntity apartmentRuleDtls = apartmentRuleDao.saveRule(rdEntity);
		assertEquals("App1", apartmentRuleDtls.getApartmentId());
	}
	
	@Test
	public void testFindByApptIdAndUnitType() throws ParseException{
		String apartmentId="App1", unitType="2BHK";
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		Mockito.when(apartmentRuleRepo.findByApptIdAndUnitType(apartmentId, unitType)).thenReturn(rdEntity);
		RuleDetailsEntity apartmentRuleDtls = apartmentRuleDao.findByApptIdAndUnitType(apartmentId, unitType);
		assertEquals("App1", apartmentRuleDtls.getApartmentId());
	}
	
	@Test
	public void testFindAllCurrentRule() throws ParseException{
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleRepo.findAllCurrentRule()).thenReturn(rdEntityList);
		List<RuleDetailsEntity> apartmentRuleDtlsList = apartmentRuleDao.findAllCurrentRule();
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test
	public void testFindAllFutureRules() throws ParseException{
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleRepo.findAllFutureRules()).thenReturn(rdEntityList);
		List<RuleDetailsEntity> apartmentRuleDtlsList = apartmentRuleDao.findAllFutureRules();
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	
	@Test
	public void testFindAllCurrentRuleByApptId() throws ParseException{
		String apartmentId="App1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleRepo.findAllCurrentRuleByApptId(apartmentId)).thenReturn(rdEntityList);
		List<RuleDetailsEntity> apartmentRuleDtlsList = apartmentRuleDao.findAllCurrentRuleByApptId(apartmentId);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}
	
	@Test
	public void testFindAllFutureRulesByApptId() throws ParseException{
		String apartmentId="App1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		Mockito.when(apartmentRuleRepo.findAllFutureRulesByApptId(apartmentId)).thenReturn(rdEntityList);
		List<RuleDetailsEntity> apartmentRuleDtlsList = apartmentRuleDao.findAllFutureRulesByApptId(apartmentId);
		assertEquals("App1", apartmentRuleDtlsList.get(0).getApartmentId());
	}

}
