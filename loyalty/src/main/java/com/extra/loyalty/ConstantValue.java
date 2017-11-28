package com.extra.loyalty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 戴尔 on 2017/11/23.
 */

public class ConstantValue {
    public static final String USER_ID ="user_id" ;
    public static final String USER_PASSWORD = "user_password";
    public static final Object OUTPUT = "output";
    public static final String TYPE ="type" ;
    public static final String ACCOUNT_ID ="account_id" ;
    public static final String CAMPAIGN_USERS ="campaign_users" ;
    public static final String CAMPAIGNS_LIST = "campaigns_list";
    public static final String CAMPAIGN_TYPE ="campaign_type" ;
    public static final String DESCRIPTION = "description";
    public static final String AMOUNT_PER_EVENT = "amount_per_event";
    public static final String POINTS_RATIO = "points_ratio";
    public static final String REWARD_RATIO = "reward_ratio";
    public static final String CAMPAIGN_NAME = "campaign_name";
    public static String USER_ID_VALUE ="" ;
    public static String USER_API_KEY_VALUE="";
    public static  String ACCOUNT_ID_VALUE = "greatwidgets";
    public static String localhost = "https://api.clienttoolbox.com";


    //ACCOUNT_INFO
    public static final String ACCOUNT_INFO = "account_info";



    public static String API = "API";
    public static String API_VALUE = "1.5";
    public static String USER_API_KEY="user_api_key";
    public static String TYPE_RESTRICT="type_restrict";
    public static String CAMPAIGNS_INACTIVE_LIST="campaigns_inactive_list";
    public static String CAMPAIGN_NEW="campaign_new";
    public static String ACTION = "action";
    public static String CAMPAIGN="campaign";

    public static Map<String,String> getPostMap(Map<String, String> map) {
        map= new HashMap<>();
        map.put(ConstantValue.USER_ID, ConstantValue.USER_ID_VALUE);
        map.put(ConstantValue.USER_API_KEY, ConstantValue.USER_API_KEY_VALUE);
        map.put(ConstantValue.ACCOUNT_ID, ConstantValue.ACCOUNT_ID_VALUE);
        map.put(ConstantValue.API,ConstantValue.API_VALUE);
        return map;
    }

    public static Map<String,String> getPostMap1(Map<String, String> map) {
        map= new HashMap<>();
        map.put(ConstantValue.USER_ID, ConstantValue.USER_ID_VALUE);
        map.put(ConstantValue.USER_PASSWORD, ConstantValue.USER_API_KEY_VALUE);
        map.put(ConstantValue.ACCOUNT_ID, ConstantValue.ACCOUNT_ID_VALUE);
        return map;
    }
}
