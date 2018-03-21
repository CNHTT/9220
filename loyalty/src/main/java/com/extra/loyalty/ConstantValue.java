package com.extra.loyalty;

import com.extra.utils.DataUtils;
import com.extra.utils.SPUtils;
import com.extra.utils.Utils;

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
    public static final String CAMPAIGN_STR = "CAMPAIGN_STR";
    public static final String USER_NEW = "user_new";
    public static final String SCALE ="SCALE" ;
    public static final String HEAD ="HEAD" ;
    public static final String TITLE = "TITLE";
    public static final String FOOT = "FOOT";
    public static final String ISLOGIN ="ISLOGIN" ;
    public static final String ACC ="ACC" ;
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
    public static String USER_NAME="user_name";
    public static String USER_NEW_PASSWORD="user_new_password";
    public static String USER_FIRST_NAME="user_first_name";
    public static String USER_LAST_NAME="user_last_name";
    public static String user_custom1="user_custom1";
    public static String user_PIN="user_PIN";
    public static String language_selector="language_selector";
    public static String timezone_selector="timezone_selector";
    public static String user_role="user_role";
    public static String allowed_campaigns="allowed_campaigns";
    public static String offset="offset";
    public static String users_list="users_list";
    public static String length="length";
    public static String user_info="user_info";
    public static String user_name="user_name";
    public static String record_customer="record_customer";
    public static String customer_action="customer_action";
    public static String campaign_id="campaign_id";
    public static String card_number="card_number";
    public static String card_number_generate="card_number_generate";
    public static String code="code";
    public static String first_name="first_name";
    public static String last_name="last_name";
    public static String phone="phone";
    public static String email="email";
    public static String street1="street1";
    public static String street2="street2";
    public static String city= "city";
    public static String state="state";
    public static String postal_code="postal_code";
    public static String country= "country";
    public static String custom_date="custom_date";
    public static String custom_field="custom_field";
    public static String customer_username="customer_username";
    public static String customer_password="customer_password";
    public static String customer_PIN="customer_PIN";
    public static String new_code="new_code";
    public static String exact_match="exact_match";
    public static String include_balances="include_balances";
    public static String customer_search="customer_search";
    public static String customer_find="customer_find";
    public static String find_customer="find_customer";
    public static String hide_custom_field="hide_custom_field";
    public static String customer_info="customer_info";
    public static String customer_balance="customer_balance";
    public static String customer_delete="customer_delete";
    public static String user_new_password="user_new_password\t";

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

    public static String getUserRole(String user_role) {
        //显示权限
        for (int i = 0; i <App.roles.size() ; i++) {
            if (App.roles.get(i).equals(user_role))
                return App.rolesStr.get(i);
        }
        return user_role;
    }

    public static final int[] IMGS2 = {
            R.drawable.home2_setting_commun,
            R.drawable.home2_setting_trans,
            R.drawable.home2_setting_privacy
    };

    public static String HEAD_STR(){
        String t = SPUtils.getString(Utils.getContext(), ConstantValue.HEAD);
        if (DataUtils.isNullString(t))return App.HEAD;
        return  t;
    }
    public static String TITLE_STR(){
        String t = SPUtils.getString(Utils.getContext(), ConstantValue.TITLE);
        if (DataUtils.isNullString(t))return App.TITlE;
        return  t;
    }
    public static String FOOT_STR(){
        String t = SPUtils.getString(Utils.getContext(), ConstantValue.FOOT);
        if (DataUtils.isNullString(t))return App.FOOT;
        return  t;
    }
}
