package com.appartementrule.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.apartmentrule.bean.ApartmentRuleDtls;
import com.apartmentrule.controller.ApartmentRuleController;
import com.apartmentrule.dao.ApartmentRuleDao;
import com.apartmentrule.exception.ApartmentRuleCustomException;
import com.apartmentrule.model.RuleDetailsEntity;
import com.apartmentrule.services.ApartmentRuleImpl;
import com.appartementrule.AppartementRuleApplicationTests;
import com.appartementrule.constant.TestConstant;
import com.appartementrule.util.TestHelper;

@WebAppConfiguration
public class ApartmentRuleControllerTest extends AppartementRuleApplicationTests{
	
	@Mock
	ApartmentRuleDao apartmentRuleDao;
	
	@Mock
	ApartmentRuleImpl apartmentRuleImpl;
	
	@InjectMocks
	private ApartmentRuleController apartmentRuleController;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(apartmentRuleController).build();
	}
	
	@Test
	public void testCreateRuleEndpoint() throws Exception {
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		ApartmentRuleDtls ardObj= TestHelper.prepareApptRuleDetailsEntityObj();
		String expectedReq = TestConstant.CREATE_REQ_JSON;
		Mockito.when(apartmentRuleDao.saveRule(Mockito.any(RuleDetailsEntity.class))).thenReturn(rdEntity);
		Mockito.when(apartmentRuleImpl.createRule(Mockito.any(ApartmentRuleDtls.class))).thenReturn(ardObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(TestConstant.CREATE_RULE_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(expectedReq);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.CREATE_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(), true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testCreateRuleEndpointException() throws Exception {
		RuleDetailsEntity rdEntity = null;
		ApartmentRuleDtls ardObj= null;
		String expectedReq = TestConstant.CREATE_REQ_JSON;
		Mockito.when(apartmentRuleDao.saveRule(Mockito.any(RuleDetailsEntity.class))).thenReturn(rdEntity);
		Mockito.when(apartmentRuleImpl.createRule(Mockito.any(ApartmentRuleDtls.class))).thenReturn(ardObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(TestConstant.CREATE_RULE_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(expectedReq);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	
	@Test
	public void testGetRuleByRuleIdEndpoint() throws Exception {
		long ruleId=1L;
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		ApartmentRuleDtls ardObj= TestHelper.prepareApptRuleDetailsEntityObj();
		Mockito.when(apartmentRuleDao.findById(ruleId)).thenReturn(rdEntity);
		Mockito.when(apartmentRuleImpl.getRulesById(ruleId)).thenReturn(ardObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULE_BY_RULE_ID_TEST_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_RULE_BY_RULE_ID_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testGetRuleByRuleIdEndpointExp() throws Exception {
		long ruleId=1L;
		RuleDetailsEntity rdEntity = null;
		ApartmentRuleDtls ardObj= null;
		Mockito.when(apartmentRuleDao.findById(ruleId)).thenReturn(rdEntity);
		Mockito.when(apartmentRuleImpl.getRulesById(ruleId)).thenReturn(ardObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULE_BY_RULE_ID_TEST_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
		
	}
	
	@Test
	public void testGetRuleByApptIdEndpoint() throws Exception {
		String apptId="App1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		List<ApartmentRuleDtls> ardObjList= TestHelper.prepareApptRuleDetailsEntityLsitObj();
		Mockito.when(apartmentRuleDao.findRuleDetails(apptId)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getRulesByApptId(apptId)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULES_BY_APPTID_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_RULES_BY_APPTID_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testGetRuleByApptIdEndpointExp() throws Exception {
		String apptId="App1";
		List<RuleDetailsEntity> rdEntityList = new ArrayList<>();
		List<ApartmentRuleDtls> ardObjList= new ArrayList<>();
		Mockito.when(apartmentRuleDao.findRuleDetails(apptId)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getRulesByApptId(apptId)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULES_BY_APPTID_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
		
	}
	
	@Test
	public void testGetRulesEndpoint() throws Exception {
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		List<ApartmentRuleDtls> ardObjList= TestHelper.prepareApptRuleDetailsEntityLsitObj();
		Mockito.when(apartmentRuleDao.findAll()).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllRules()).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_RULES_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_ALL_RULES_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testGetRulesEndpointExp() throws Exception {
		List<RuleDetailsEntity> rdEntityList = new ArrayList<>();
		List<ApartmentRuleDtls> ardObjList= new ArrayList<>();
		Mockito.when(apartmentRuleDao.findAll()).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllRules()).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_RULES_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	@Test
	public void testGetRuleByUnitTypeEndpoint() throws Exception {
		String unitType="2BHK";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		List<ApartmentRuleDtls> ardObjList= TestHelper.prepareApptRuleDetailsEntityLsitObj();
		Mockito.when(apartmentRuleDao.getAllRulesByUnitType(unitType)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getRulesByUnitType(unitType)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULES_BY_UNIT_TYPE_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_ALL_RULES_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testGetRuleByUnitTypeEndpointExp() throws Exception {
		String unitType="2BHK";
		List<RuleDetailsEntity> rdEntityList = new ArrayList<>();
		List<ApartmentRuleDtls> ardObjList= new ArrayList<>();
		Mockito.when(apartmentRuleDao.getAllRulesByUnitType(unitType)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getRulesByUnitType(unitType)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULES_BY_UNIT_TYPE_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	@Test
	public void testDeleteRuleByRuleIdEndpoint() throws Exception {
		long ruleId=1L;
		Mockito.when(apartmentRuleDao.updateDeleteFlag(ruleId)).thenReturn(1);
		Mockito.when(apartmentRuleImpl.updateDeleteFlag(ruleId)).thenReturn(1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(TestConstant.DELETE_RULE_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.DELETE_RULE_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testDeleteRuleByRuleIdEndpointExp() throws Exception {
		long ruleId=1L;
		Mockito.when(apartmentRuleDao.updateDeleteFlag(ruleId)).thenReturn(0);
		Mockito.when(apartmentRuleImpl.updateDeleteFlag(ruleId)).thenReturn(0);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(TestConstant.DELETE_RULE_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	@Test
	public void testGetRuleByApptIdAndUnitTypeEndpoint() throws Exception {
		String apptId="App1";
		String unitType="2BHK";
		RuleDetailsEntity rdEntity = TestHelper.prepareRuleDetailsEntityObj();
		ApartmentRuleDtls ardObj= TestHelper.prepareApptRuleDetailsEntityObj();
		Mockito.when(apartmentRuleDao.findByApptIdAndUnitType(apptId, unitType)).thenReturn(rdEntity);
		Mockito.when(apartmentRuleImpl.getRuleByApptIdAndUnitType(apptId, unitType)).thenReturn(ardObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULE_BY_APPT_UNIT_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_RULE_BY_RULE_ID_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testGetRuleByApptIdAndUnitTypeEndpointExp() throws Exception {
		String apptId="App1";
		String unitType="2BHK";
		RuleDetailsEntity rdEntity = null;
		ApartmentRuleDtls ardObj= null;
		Mockito.when(apartmentRuleDao.findByApptIdAndUnitType(apptId, unitType)).thenReturn(rdEntity);
		Mockito.when(apartmentRuleImpl.getRuleByApptIdAndUnitType(apptId, unitType)).thenReturn(ardObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_RULE_BY_APPT_UNIT_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	@Test
	public void testgetAllCurrentRulesEndpoint() throws Exception {
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		List<ApartmentRuleDtls> ardObjList= TestHelper.prepareApptRuleDetailsEntityLsitObj();
		Mockito.when(apartmentRuleDao.findAllCurrentRule()).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllCurrentRules()).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_CURRENT_RULES_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_ALL_RULES_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testgetAllCurrentRulesEndpointExp() throws Exception {
		List<RuleDetailsEntity> rdEntityList = new ArrayList<>();
		List<ApartmentRuleDtls> ardObjList= new ArrayList<>();
		Mockito.when(apartmentRuleDao.findAllCurrentRule()).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllCurrentRules()).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_CURRENT_RULES_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	@Test
	public void testgetAllCurrentRulesByApptIdEndpoint() throws Exception {
		String apptId="App1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		List<ApartmentRuleDtls> ardObjList= TestHelper.prepareApptRuleDetailsEntityLsitObj();
		Mockito.when(apartmentRuleDao.findAllCurrentRuleByApptId(apptId)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllCurrentRulesByApptId(apptId)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_CURRENT_RULES_BY_APPTID_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_ALL_RULES_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testgetAllCurrentRulesByApptIdEndpointExp() throws Exception {
		String apptId="App1";
		List<RuleDetailsEntity> rdEntityList = new ArrayList<>();
		List<ApartmentRuleDtls> ardObjList= new ArrayList<>();
		Mockito.when(apartmentRuleDao.findAllCurrentRuleByApptId(apptId)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllCurrentRulesByApptId(apptId)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_CURRENT_RULES_BY_APPTID_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	@Test
	public void testgetAllFutureRulesEndpoint() throws Exception {
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		List<ApartmentRuleDtls> ardObjList= TestHelper.prepareApptRuleDetailsEntityLsitObj();
		Mockito.when(apartmentRuleDao.findAllFutureRules()).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllFutureRules()).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_FUTURE_RULES_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_ALL_RULES_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testgetAllFutureRulesEndpointExp() throws Exception {
		List<RuleDetailsEntity> rdEntityList = new ArrayList<>();
		List<ApartmentRuleDtls> ardObjList= new ArrayList<>();
		Mockito.when(apartmentRuleDao.findAllFutureRules()).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllFutureRules()).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_FUTURE_RULES_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	@Test
	public void testgetAllFutureRulesByApptIdEndpoint() throws Exception {
		String apptId="App1";
		List<RuleDetailsEntity> rdEntityList = TestHelper.prepareRuleDetailsEntityList();
		List<ApartmentRuleDtls> ardObjList= TestHelper.prepareApptRuleDetailsEntityLsitObj();
		Mockito.when(apartmentRuleDao.findAllFutureRulesByApptId(apptId)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllFutureRulesByApptId(apptId)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_FUTURE_RULES_BY_APPTID_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedRes = TestConstant.GET_ALL_RULES_RES_JSON;
		JSONAssert.assertEquals(expectedRes, result.getResponse().getContentAsString(),true);
	}
	
	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void testgetAllFutureRulesByApptIdEndpointExp() throws Exception {
		String apptId="App1";
		List<RuleDetailsEntity> rdEntityList = new ArrayList<>();
		List<ApartmentRuleDtls> ardObjList= new ArrayList<>();
		Mockito.when(apartmentRuleDao.findAllFutureRulesByApptId(apptId)).thenReturn(rdEntityList);
		Mockito.when(apartmentRuleImpl.getAllFutureRulesByApptId(apptId)).thenReturn(ardObjList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstant.GET_ALL_FUTURE_RULES_BY_APPTID_ENDPOINT).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andReturn();
	}
	

}
