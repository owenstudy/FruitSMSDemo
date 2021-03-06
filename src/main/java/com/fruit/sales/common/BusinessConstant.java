package com.fruit.sales.common;


public class BusinessConstant {

	//pubic conifg key
	public static final String MAX_ORDER_DAY_TO = "ORDER_DAY_TO";
	public static final String RECEIVER_EFF_YEAR = "RECEIVER_EFF_YEAR";
	public static final String WECHAT_SERVER_URL = "WECHAT_SERVER_URL";
	public static final String RECIVER_SMS_CONTENT = "RECIVER_SMS_CONTENT";

	public static final String MAX_WAITFOR_ORDER_NUM = "MAX_WAITFOR_ORDER_NUM";
	public static final String MAX_WAITFOR_ORDER_NUM_UNDEFINED = "UNDEFINED";


	//default address
	public static final Integer DEFAULT_ADDRESS = new Integer(1);
	public static final Integer NOT_DEFAULT_ADDRESS = new Integer(0);

	//test
	public static final String MAX_TEST_PARAM = "TEST_PARAM";
	
	//assign, salve phone no is or not virtual
	public static final Integer IS_VIRTUAL = new Integer(1);
	public static final Integer NOT_VIRTUAL = new Integer(0);
	
	
	//assign is active or not
	public static final Integer NOT_ACTIVE = new Integer(0);
	public static final Integer IS_ACTIVE = new Integer(1);
	
	
	//default wechat openid for inital setup
	public static final String DEFAULT_WECHAT_ID = "NA";
	
	//default effective period
	public static final Integer ASSIGN_DEF_EFF_PERIOD = new Integer(1);
	
	
	public static final String PARAM_NOT_CORRECT = new String("-1");
	public static final String PROCESS_SUCCESS = new String("0");
	
	
	public static final String DEFAULT_REMARK_DATA = new String("default_remark");
	public static final String DEFAULT_EXTEND_DATA = new String("{name:value}");
	public static final Integer DEFAULT_FRUIT_MAX_PROV = new Integer(99);
	
}
