package com.lx.benefits.constant;

/**
 * @author by yingcai on 2018/9/13.
 */
public class PlatformConstatnts {

    public static final String FULI_TOKEN = "fuli-cache";
    public static final String SUPPLIER_TOKEN = "supplier-cache";
    public static final String ENTERPRISE_TOKEN = "enterprise-cache";
    public static final String EMPLOYEE_TOKEN = "employee-cache";
    public static final String AGENT_TOKEN = "agent-cache";
    public static final String YIAN_TOKEN = "yian-cache";
    public static final String CUSTOMER_TOKEN = "customer-cache";


    public static final String FULI_NAME = "fuli-name";
    public static final String SUPPLIER_NAME = "supplier-name";
    public static final String ENTERPRISE_NAME = "enterprise-name";
    public static final String EMPLOYEE_NAME = "employee-name";
    public static final String AGENT_NAME = "agent-name";
    public static final String ENTERPRISE_ID = "enterprise-id";
    public static final String YIAN_NAME = "yian-name";
    public static final String CUSTOMER_NAME = "customer-name";
    
    public static final String EXPOSE_HEADERS = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s", FULI_TOKEN, FULI_NAME, SUPPLIER_TOKEN, SUPPLIER_NAME, ENTERPRISE_TOKEN, ENTERPRISE_NAME, EMPLOYEE_TOKEN, EMPLOYEE_NAME, YIAN_TOKEN, YIAN_NAME, AGENT_TOKEN, AGENT_NAME,CUSTOMER_TOKEN,CUSTOMER_NAME);
    
    public static final String ALLOW_HEADERS = String.format("X-Requested-With, Content-Type, %s, %s, %s, %s, %s, %s, %s", FULI_TOKEN, SUPPLIER_TOKEN, ENTERPRISE_TOKEN, EMPLOYEE_TOKEN, YIAN_TOKEN, AGENT_TOKEN, CUSTOMER_TOKEN);
}
