package com.appartementrule.constant;

import java.net.URI;

public class TestConstant {
	public static final String GET_RULE_BY_RULE_ID_TEST_JSON = "/GetRuleByRuleIdTest.json";

	public static final String GET_RULE_BY_RULE_ID_TEST_ENDPOINT = "/apptruleservice/get/rule/1";

	public static final String CREATE_RULE_ENDPOINT = "/apptruleservice/create/rule";

	public static final String GET_RULES_BY_APPTID_ENDPOINT = "/apptruleservice/get/apartment/rules/App1";
	
	public static final String GET_RULES_BY_UNIT_TYPE_ENDPOINT = "/apptruleservice/get/rules/2BHK";
	
	public static final String DELETE_RULE_ENDPOINT = "/apptruleservice/delete/rule/1";
	
	public static final String GET_RULE_BY_APPT_UNIT_ENDPOINT = "/apptruleservice/get/rule/App1/2BHK";
	
	public static final String CREATE_REQ_JSON = "{\"apartmentId\":\"App1\",\"unitType\":\"2BHK\",\"fixedCharge\":\"200\",\"perSquarefootCharge\":0,\"rentCharge\":0,\"effectiveDate\":\"2020-01-10\"}";

	public static final String CREATE_RES_JSON = "{\"status_code\":\"0\",\"message\":\"Rule has been created Successfuly\",\"ruleList\":[{\"ruleId\":1,\"apartmentId\":\"App1\",\"unitType\":\"2BHK\",\"fixedCharge\":\"200\",\"perSquarefootCharge\":\"0\",\"rentCharge\":\"0\",\"effectiveDate\":\"2019-01-01\"}]}";

	public static final String GET_ALL_RULES_RES_JSON = "{\"status_code\":\"0\",\"message\":\"Rules have been fetched Successfuly\",\"ruleList\":[{\"ruleId\":1,\"apartmentId\":\"App1\",\"unitType\":\"2BHK\",\"fixedCharge\":\"200\",\"perSquarefootCharge\":\"0\",\"rentCharge\":\"0\",\"effectiveDate\":\"2019-01-01\"}]}";

	public static final String GET_ALL_RULES_ENDPOINT = "/apptruleservice/get/rules";

	public static final String GET_RULE_BY_RULE_ID_RES_JSON = "{\"status_code\":\"0\",\"message\":\"Rule has been fetched Successfuly\",\"ruleList\":[{\"ruleId\":1,\"apartmentId\":\"App1\",\"unitType\":\"2BHK\",\"fixedCharge\":\"200\",\"perSquarefootCharge\":\"0\",\"rentCharge\":\"0\",\"effectiveDate\":\"2019-01-01\"}]}";

	public static final String GET_RULES_BY_APPTID_RES_JSON = "{\"status_code\":\"0\",\"message\":\"Rules have been fetched Successfuly\",\"ruleList\":[{\"ruleId\":1,\"apartmentId\":\"App1\",\"unitType\":\"2BHK\",\"fixedCharge\":\"200\",\"perSquarefootCharge\":\"0\",\"rentCharge\":\"0\",\"effectiveDate\":\"2019-01-01\"}]}";

	public static final String DELETE_RULE_RES_JSON = "{\"status_code\":\"0\",\"message\":\"Rule has been Deleted Successfully\"}";

	public static final String GET_ALL_CURRENT_RULES_ENDPOINT = "/apptruleservice/get/current/rules";
	
	public static final String GET_ALL_FUTURE_RULES_ENDPOINT = "/apptruleservice/get/future/rules";

	public static final String GET_ALL_CURRENT_RULES_BY_APPTID_ENDPOINT = "/apptruleservice/get/current/rules/apartment/App1";
	
	public static final String GET_ALL_FUTURE_RULES_BY_APPTID_ENDPOINT = "/apptruleservice/get/future/rules/apartment/App1";



}
