package com.extra.loyalty.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 戴尔 on 2017/12/29.
 */

public class UserBean implements Serializable {




    /**
     * user_id : SKS
     * user_first_name : 98
     * user_last_name : K
     * user_api_key : 7c4a8d09ca3762af61e59520943dc26494f8941b
     * user_PIN :
     * user_addtl_info : JDQS
     * user_language : FR
     * user_language_custom : No
     * user_timezone : 5
     * user_role : M
     * user_allowed_campaigns : {"status":"some","campaign_ids":[{"xml_value_to_show":"1696214675052881"},{"xml_value_to_show":"1655055478534996"}]}
     */

    private String user_id;
    private String user_first_name;
    private String user_last_name;
    private String user_api_key;
    private String user_PIN;
    private String user_addtl_info;
    private String user_language;
    private String user_language_custom;
    private String user_timezone;
    private String user_role;
    private String user_level;
    private UserAllowedCampaignsBean user_allowed_campaigns;
    private UserAllowedCampaignsBean allowed_campaigns;
    /**
     * user_is_owner : false
     * user_permissions : {"activate_account":"Yes","view_account_info":"Yes","edit_account_info":"Yes","manage_campaign_fields":"Yes","api_user_long_account_info":"Yes","add_campaign":"Yes","delete_campaign":"Yes","activate_campaign":"Yes","deactivate_campaign":"Yes","open_campaign":"Yes","access_campaign_settings":"Yes","update_campaign":"Yes","edit_campaign_name":"Yes","view_gift_card_prefs":"Yes","edit_gift_card_prefs":"Yes","view_points_prefs":"Yes","edit_points_prefs":"Yes","view_events_prefs":"Yes","edit_events_prefs":"Yes","view_buyx_prefs":"Yes","edit_buyx_prefs":"Yes","view_earn_prefs":"Yes","edit_earn_prefs":"Yes","view_campaign_email":"Yes","edit_campaign_email":"Yes","view_point_to_dollar_ratio":"Yes","edit_point_to_dollar_ratio":"Yes","view_profit_ratio":"Yes","edit_profit_ratio":"Yes","view_campaign_points_expiration":"Yes","add_campaign_points_expiration":"Yes","edit_campaign_points_expiration":"Yes","delete_campaign_points_expiration":"Yes","view_promotions":"Yes","edit_promotions":"Yes","add_promotions":"Yes","delete_promotions":"Yes","view_rewards":"Yes","edit_rewards":"Yes","add_rewards":"Yes","delete_rewards":"Yes","show_dollars_earned":"Yes","edit_dollars_earned":"Yes","view_buyx_items":"Yes","edit_buyx_items":"Yes","add_buyx_items":"Yes","delete_buyx_items":"Yes","view_buyx_default":"Yes","edit_buyx_default":"Yes","view_depreciations":"Yes","add_depreciation":"Yes","add_depreciations":"Yes","edit_depreciation":"Yes","edit_depreciations":"Yes","delete_depreciation":"Yes","delete_depreciations":"Yes","view_recurring_fee":"Yes","add_recurring_fee":"Yes","edit_recurring_fee":"Yes","delete_recurring_fee":"Yes","view_customer_search":"Yes","add_customer":"Yes","edit_customer_card_number":"Yes","print_customer_transactions":"Yes","edit_customer_info":"Yes","customer_visit_add":"Yes","customer_visit_redeem":"Yes","customer_visit_delete":"Yes","customer_visits_view":"Yes","manage_cards":"Yes","manage_fields":"Yes","list_fields":"Yes","import_customers":"Yes","import_transactions":"Yes","api_batch_customers":"Yes","api_batch_transactions":"Yes","view_user":"Yes","edit_user":"Yes","delete_user":"Yes","add_user":"Yes","edit_users_campaigns_list":"Yes","edit_my_info":"Yes","report_on_users":"Yes","show_administrator_option":"Yes","show_manager_option":"Yes","show_coaltion_store_manager_option":"Yes","show_agency_client_import_option":"Yes","show_agency_client_option":"Yes","show_campaign_manager_option":"Yes","show_fulfillment_contractor_option":"Yes","show_accountant_option":"Yes","show_observer_option":"Yes","access_reports":"Yes","export_apex":"Yes","export_excel":"Yes","export_csv":"Yes","export_mailchimp":"Yes","make_bookmarks":"Yes","affiliate":"Yes"}
     * account_c2t : yes
     * account_coalition : yes
     */

    private String user_is_owner;
    private UserPermissionsBean user_permissions;
    private String account_c2t;
    private String account_coalition;

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public String getUser_api_key() {
        return user_api_key;
    }

    public void setUser_api_key(String user_api_key) {
        this.user_api_key = user_api_key;
    }

    public String getUser_PIN() {
        return user_PIN;
    }

    public void setUser_PIN(String user_PIN) {
        this.user_PIN = user_PIN;
    }

    public String getUser_addtl_info() {
        return user_addtl_info;
    }

    public void setUser_addtl_info(String user_addtl_info) {
        this.user_addtl_info = user_addtl_info;
    }

    public String getUser_language() {
        return user_language;
    }

    public void setUser_language(String user_language) {
        this.user_language = user_language;
    }

    public String getUser_language_custom() {
        return user_language_custom;
    }

    public void setUser_language_custom(String user_language_custom) {
        this.user_language_custom = user_language_custom;
    }

    public String getUser_timezone() {
        return user_timezone;
    }

    public void setUser_timezone(String user_timezone) {
        this.user_timezone = user_timezone;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public UserAllowedCampaignsBean getUser_allowed_campaigns() {
        return user_allowed_campaigns;
    }

    public void setUser_allowed_campaigns(UserAllowedCampaignsBean user_allowed_campaigns) {
        this.user_allowed_campaigns = user_allowed_campaigns;
    }

    public String getUser_is_owner() {
        return user_is_owner;
    }

    public void setUser_is_owner(String user_is_owner) {
        this.user_is_owner = user_is_owner;
    }

    public UserPermissionsBean getUser_permissions() {
        return user_permissions;
    }

    public void setUser_permissions(UserPermissionsBean user_permissions) {
        this.user_permissions = user_permissions;
    }

    public String getAccount_c2t() {
        return account_c2t;
    }

    public void setAccount_c2t(String account_c2t) {
        this.account_c2t = account_c2t;
    }

    public String getAccount_coalition() {
        return account_coalition;
    }

    public void setAccount_coalition(String account_coalition) {
        this.account_coalition = account_coalition;
    }


    public UserAllowedCampaignsBean getAllowed_campaigns() {
        return allowed_campaigns;
    }

    public void setAllowed_campaigns(UserAllowedCampaignsBean allowed_campaigns) {
        this.allowed_campaigns = allowed_campaigns;
    }

    public static class UserAllowedCampaignsBean  implements Serializable {
        /**
         * status : some
         * campaign_ids : [{"xml_value_to_show":"1696214675052881"},{"xml_value_to_show":"1655055478534996"}]
         */

        private String status;
        private List<CampaignIdsBean> campaign_ids;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<CampaignIdsBean> getCampaign_ids() {
            return campaign_ids;
        }

        public void setCampaign_ids(List<CampaignIdsBean> campaign_ids) {
            this.campaign_ids = campaign_ids;
        }

        public static class CampaignIdsBean implements Serializable  {
            /**
             * xml_value_to_show : 1696214675052881
             */

            private String xml_value_to_show;

            public String getXml_value_to_show() {
                return xml_value_to_show;
            }

            public void setXml_value_to_show(String xml_value_to_show) {
                this.xml_value_to_show = xml_value_to_show;
            }
        }
    }

    public static class UserPermissionsBean implements Serializable {
        /**
         * activate_account : Yes
         * view_account_info : Yes
         * edit_account_info : Yes
         * manage_campaign_fields : Yes
         * api_user_long_account_info : Yes
         * add_campaign : Yes
         * delete_campaign : Yes
         * activate_campaign : Yes
         * deactivate_campaign : Yes
         * open_campaign : Yes
         * access_campaign_settings : Yes
         * update_campaign : Yes
         * edit_campaign_name : Yes
         * view_gift_card_prefs : Yes
         * edit_gift_card_prefs : Yes
         * view_points_prefs : Yes
         * edit_points_prefs : Yes
         * view_events_prefs : Yes
         * edit_events_prefs : Yes
         * view_buyx_prefs : Yes
         * edit_buyx_prefs : Yes
         * view_earn_prefs : Yes
         * edit_earn_prefs : Yes
         * view_campaign_email : Yes
         * edit_campaign_email : Yes
         * view_point_to_dollar_ratio : Yes
         * edit_point_to_dollar_ratio : Yes
         * view_profit_ratio : Yes
         * edit_profit_ratio : Yes
         * view_campaign_points_expiration : Yes
         * add_campaign_points_expiration : Yes
         * edit_campaign_points_expiration : Yes
         * delete_campaign_points_expiration : Yes
         * view_promotions : Yes
         * edit_promotions : Yes
         * add_promotions : Yes
         * delete_promotions : Yes
         * view_rewards : Yes
         * edit_rewards : Yes
         * add_rewards : Yes
         * delete_rewards : Yes
         * show_dollars_earned : Yes
         * edit_dollars_earned : Yes
         * view_buyx_items : Yes
         * edit_buyx_items : Yes
         * add_buyx_items : Yes
         * delete_buyx_items : Yes
         * view_buyx_default : Yes
         * edit_buyx_default : Yes
         * view_depreciations : Yes
         * add_depreciation : Yes
         * add_depreciations : Yes
         * edit_depreciation : Yes
         * edit_depreciations : Yes
         * delete_depreciation : Yes
         * delete_depreciations : Yes
         * view_recurring_fee : Yes
         * add_recurring_fee : Yes
         * edit_recurring_fee : Yes
         * delete_recurring_fee : Yes
         * view_customer_search : Yes
         * add_customer : Yes
         * edit_customer_card_number : Yes
         * print_customer_transactions : Yes
         * edit_customer_info : Yes
         * customer_visit_add : Yes
         * customer_visit_redeem : Yes
         * customer_visit_delete : Yes
         * customer_visits_view : Yes
         * manage_cards : Yes
         * manage_fields : Yes
         * list_fields : Yes
         * import_customers : Yes
         * import_transactions : Yes
         * api_batch_customers : Yes
         * api_batch_transactions : Yes
         * view_user : Yes
         * edit_user : Yes
         * delete_user : Yes
         * add_user : Yes
         * edit_users_campaigns_list : Yes
         * edit_my_info : Yes
         * report_on_users : Yes
         * show_administrator_option : Yes
         * show_manager_option : Yes
         * show_coaltion_store_manager_option : Yes
         * show_agency_client_import_option : Yes
         * show_agency_client_option : Yes
         * show_campaign_manager_option : Yes
         * show_fulfillment_contractor_option : Yes
         * show_accountant_option : Yes
         * show_observer_option : Yes
         * access_reports : Yes
         * export_apex : Yes
         * export_excel : Yes
         * export_csv : Yes
         * export_mailchimp : Yes
         * make_bookmarks : Yes
         * affiliate : Yes
         */

        private String activate_account;
        private String view_account_info;
        private String edit_account_info;
        private String manage_campaign_fields;
        private String api_user_long_account_info;
        private String add_campaign;
        private String delete_campaign;
        private String activate_campaign;
        private String deactivate_campaign;
        private String open_campaign;
        private String access_campaign_settings;
        private String update_campaign;
        private String edit_campaign_name;
        private String view_gift_card_prefs;
        private String edit_gift_card_prefs;
        private String view_points_prefs;
        private String edit_points_prefs;
        private String view_events_prefs;
        private String edit_events_prefs;
        private String view_buyx_prefs;
        private String edit_buyx_prefs;
        private String view_earn_prefs;
        private String edit_earn_prefs;
        private String view_campaign_email;
        private String edit_campaign_email;
        private String view_point_to_dollar_ratio;
        private String edit_point_to_dollar_ratio;
        private String view_profit_ratio;
        private String edit_profit_ratio;
        private String view_campaign_points_expiration;
        private String add_campaign_points_expiration;
        private String edit_campaign_points_expiration;
        private String delete_campaign_points_expiration;
        private String view_promotions;
        private String edit_promotions;
        private String add_promotions;
        private String delete_promotions;
        private String view_rewards;
        private String edit_rewards;
        private String add_rewards;
        private String delete_rewards;
        private String show_dollars_earned;
        private String edit_dollars_earned;
        private String view_buyx_items;
        private String edit_buyx_items;
        private String add_buyx_items;
        private String delete_buyx_items;
        private String view_buyx_default;
        private String edit_buyx_default;
        private String view_depreciations;
        private String add_depreciation;
        private String add_depreciations;
        private String edit_depreciation;
        private String edit_depreciations;
        private String delete_depreciation;
        private String delete_depreciations;
        private String view_recurring_fee;
        private String add_recurring_fee;
        private String edit_recurring_fee;
        private String delete_recurring_fee;
        private String view_customer_search;
        private String add_customer;
        private String edit_customer_card_number;
        private String print_customer_transactions;
        private String edit_customer_info;
        private String customer_visit_add;
        private String customer_visit_redeem;
        private String customer_visit_delete;
        private String customer_visits_view;
        private String manage_cards;
        private String manage_fields;
        private String list_fields;
        private String import_customers;
        private String import_transactions;
        private String api_batch_customers;
        private String api_batch_transactions;
        private String view_user;
        private String edit_user;
        private String delete_user;
        private String add_user;
        private String edit_users_campaigns_list;
        private String edit_my_info;
        private String report_on_users;
        private String show_administrator_option;
        private String show_manager_option;
        private String show_coaltion_store_manager_option;
        private String show_agency_client_import_option;
        private String show_agency_client_option;
        private String show_campaign_manager_option;
        private String show_fulfillment_contractor_option;
        private String show_accountant_option;
        private String show_observer_option;
        private String access_reports;
        private String export_apex;
        private String export_excel;
        private String export_csv;
        private String export_mailchimp;
        private String make_bookmarks;
        private String affiliate;

        public String getActivate_account() {
            return activate_account;
        }

        public void setActivate_account(String activate_account) {
            this.activate_account = activate_account;
        }

        public String getView_account_info() {
            return view_account_info;
        }

        public void setView_account_info(String view_account_info) {
            this.view_account_info = view_account_info;
        }

        public String getEdit_account_info() {
            return edit_account_info;
        }

        public void setEdit_account_info(String edit_account_info) {
            this.edit_account_info = edit_account_info;
        }

        public String getManage_campaign_fields() {
            return manage_campaign_fields;
        }

        public void setManage_campaign_fields(String manage_campaign_fields) {
            this.manage_campaign_fields = manage_campaign_fields;
        }

        public String getApi_user_long_account_info() {
            return api_user_long_account_info;
        }

        public void setApi_user_long_account_info(String api_user_long_account_info) {
            this.api_user_long_account_info = api_user_long_account_info;
        }

        public String getAdd_campaign() {
            return add_campaign;
        }

        public void setAdd_campaign(String add_campaign) {
            this.add_campaign = add_campaign;
        }

        public String getDelete_campaign() {
            return delete_campaign;
        }

        public void setDelete_campaign(String delete_campaign) {
            this.delete_campaign = delete_campaign;
        }

        public String getActivate_campaign() {
            return activate_campaign;
        }

        public void setActivate_campaign(String activate_campaign) {
            this.activate_campaign = activate_campaign;
        }

        public String getDeactivate_campaign() {
            return deactivate_campaign;
        }

        public void setDeactivate_campaign(String deactivate_campaign) {
            this.deactivate_campaign = deactivate_campaign;
        }

        public String getOpen_campaign() {
            return open_campaign;
        }

        public void setOpen_campaign(String open_campaign) {
            this.open_campaign = open_campaign;
        }

        public String getAccess_campaign_settings() {
            return access_campaign_settings;
        }

        public void setAccess_campaign_settings(String access_campaign_settings) {
            this.access_campaign_settings = access_campaign_settings;
        }

        public String getUpdate_campaign() {
            return update_campaign;
        }

        public void setUpdate_campaign(String update_campaign) {
            this.update_campaign = update_campaign;
        }

        public String getEdit_campaign_name() {
            return edit_campaign_name;
        }

        public void setEdit_campaign_name(String edit_campaign_name) {
            this.edit_campaign_name = edit_campaign_name;
        }

        public String getView_gift_card_prefs() {
            return view_gift_card_prefs;
        }

        public void setView_gift_card_prefs(String view_gift_card_prefs) {
            this.view_gift_card_prefs = view_gift_card_prefs;
        }

        public String getEdit_gift_card_prefs() {
            return edit_gift_card_prefs;
        }

        public void setEdit_gift_card_prefs(String edit_gift_card_prefs) {
            this.edit_gift_card_prefs = edit_gift_card_prefs;
        }

        public String getView_points_prefs() {
            return view_points_prefs;
        }

        public void setView_points_prefs(String view_points_prefs) {
            this.view_points_prefs = view_points_prefs;
        }

        public String getEdit_points_prefs() {
            return edit_points_prefs;
        }

        public void setEdit_points_prefs(String edit_points_prefs) {
            this.edit_points_prefs = edit_points_prefs;
        }

        public String getView_events_prefs() {
            return view_events_prefs;
        }

        public void setView_events_prefs(String view_events_prefs) {
            this.view_events_prefs = view_events_prefs;
        }

        public String getEdit_events_prefs() {
            return edit_events_prefs;
        }

        public void setEdit_events_prefs(String edit_events_prefs) {
            this.edit_events_prefs = edit_events_prefs;
        }

        public String getView_buyx_prefs() {
            return view_buyx_prefs;
        }

        public void setView_buyx_prefs(String view_buyx_prefs) {
            this.view_buyx_prefs = view_buyx_prefs;
        }

        public String getEdit_buyx_prefs() {
            return edit_buyx_prefs;
        }

        public void setEdit_buyx_prefs(String edit_buyx_prefs) {
            this.edit_buyx_prefs = edit_buyx_prefs;
        }

        public String getView_earn_prefs() {
            return view_earn_prefs;
        }

        public void setView_earn_prefs(String view_earn_prefs) {
            this.view_earn_prefs = view_earn_prefs;
        }

        public String getEdit_earn_prefs() {
            return edit_earn_prefs;
        }

        public void setEdit_earn_prefs(String edit_earn_prefs) {
            this.edit_earn_prefs = edit_earn_prefs;
        }

        public String getView_campaign_email() {
            return view_campaign_email;
        }

        public void setView_campaign_email(String view_campaign_email) {
            this.view_campaign_email = view_campaign_email;
        }

        public String getEdit_campaign_email() {
            return edit_campaign_email;
        }

        public void setEdit_campaign_email(String edit_campaign_email) {
            this.edit_campaign_email = edit_campaign_email;
        }

        public String getView_point_to_dollar_ratio() {
            return view_point_to_dollar_ratio;
        }

        public void setView_point_to_dollar_ratio(String view_point_to_dollar_ratio) {
            this.view_point_to_dollar_ratio = view_point_to_dollar_ratio;
        }

        public String getEdit_point_to_dollar_ratio() {
            return edit_point_to_dollar_ratio;
        }

        public void setEdit_point_to_dollar_ratio(String edit_point_to_dollar_ratio) {
            this.edit_point_to_dollar_ratio = edit_point_to_dollar_ratio;
        }

        public String getView_profit_ratio() {
            return view_profit_ratio;
        }

        public void setView_profit_ratio(String view_profit_ratio) {
            this.view_profit_ratio = view_profit_ratio;
        }

        public String getEdit_profit_ratio() {
            return edit_profit_ratio;
        }

        public void setEdit_profit_ratio(String edit_profit_ratio) {
            this.edit_profit_ratio = edit_profit_ratio;
        }

        public String getView_campaign_points_expiration() {
            return view_campaign_points_expiration;
        }

        public void setView_campaign_points_expiration(String view_campaign_points_expiration) {
            this.view_campaign_points_expiration = view_campaign_points_expiration;
        }

        public String getAdd_campaign_points_expiration() {
            return add_campaign_points_expiration;
        }

        public void setAdd_campaign_points_expiration(String add_campaign_points_expiration) {
            this.add_campaign_points_expiration = add_campaign_points_expiration;
        }

        public String getEdit_campaign_points_expiration() {
            return edit_campaign_points_expiration;
        }

        public void setEdit_campaign_points_expiration(String edit_campaign_points_expiration) {
            this.edit_campaign_points_expiration = edit_campaign_points_expiration;
        }

        public String getDelete_campaign_points_expiration() {
            return delete_campaign_points_expiration;
        }

        public void setDelete_campaign_points_expiration(String delete_campaign_points_expiration) {
            this.delete_campaign_points_expiration = delete_campaign_points_expiration;
        }

        public String getView_promotions() {
            return view_promotions;
        }

        public void setView_promotions(String view_promotions) {
            this.view_promotions = view_promotions;
        }

        public String getEdit_promotions() {
            return edit_promotions;
        }

        public void setEdit_promotions(String edit_promotions) {
            this.edit_promotions = edit_promotions;
        }

        public String getAdd_promotions() {
            return add_promotions;
        }

        public void setAdd_promotions(String add_promotions) {
            this.add_promotions = add_promotions;
        }

        public String getDelete_promotions() {
            return delete_promotions;
        }

        public void setDelete_promotions(String delete_promotions) {
            this.delete_promotions = delete_promotions;
        }

        public String getView_rewards() {
            return view_rewards;
        }

        public void setView_rewards(String view_rewards) {
            this.view_rewards = view_rewards;
        }

        public String getEdit_rewards() {
            return edit_rewards;
        }

        public void setEdit_rewards(String edit_rewards) {
            this.edit_rewards = edit_rewards;
        }

        public String getAdd_rewards() {
            return add_rewards;
        }

        public void setAdd_rewards(String add_rewards) {
            this.add_rewards = add_rewards;
        }

        public String getDelete_rewards() {
            return delete_rewards;
        }

        public void setDelete_rewards(String delete_rewards) {
            this.delete_rewards = delete_rewards;
        }

        public String getShow_dollars_earned() {
            return show_dollars_earned;
        }

        public void setShow_dollars_earned(String show_dollars_earned) {
            this.show_dollars_earned = show_dollars_earned;
        }

        public String getEdit_dollars_earned() {
            return edit_dollars_earned;
        }

        public void setEdit_dollars_earned(String edit_dollars_earned) {
            this.edit_dollars_earned = edit_dollars_earned;
        }

        public String getView_buyx_items() {
            return view_buyx_items;
        }

        public void setView_buyx_items(String view_buyx_items) {
            this.view_buyx_items = view_buyx_items;
        }

        public String getEdit_buyx_items() {
            return edit_buyx_items;
        }

        public void setEdit_buyx_items(String edit_buyx_items) {
            this.edit_buyx_items = edit_buyx_items;
        }

        public String getAdd_buyx_items() {
            return add_buyx_items;
        }

        public void setAdd_buyx_items(String add_buyx_items) {
            this.add_buyx_items = add_buyx_items;
        }

        public String getDelete_buyx_items() {
            return delete_buyx_items;
        }

        public void setDelete_buyx_items(String delete_buyx_items) {
            this.delete_buyx_items = delete_buyx_items;
        }

        public String getView_buyx_default() {
            return view_buyx_default;
        }

        public void setView_buyx_default(String view_buyx_default) {
            this.view_buyx_default = view_buyx_default;
        }

        public String getEdit_buyx_default() {
            return edit_buyx_default;
        }

        public void setEdit_buyx_default(String edit_buyx_default) {
            this.edit_buyx_default = edit_buyx_default;
        }

        public String getView_depreciations() {
            return view_depreciations;
        }

        public void setView_depreciations(String view_depreciations) {
            this.view_depreciations = view_depreciations;
        }

        public String getAdd_depreciation() {
            return add_depreciation;
        }

        public void setAdd_depreciation(String add_depreciation) {
            this.add_depreciation = add_depreciation;
        }

        public String getAdd_depreciations() {
            return add_depreciations;
        }

        public void setAdd_depreciations(String add_depreciations) {
            this.add_depreciations = add_depreciations;
        }

        public String getEdit_depreciation() {
            return edit_depreciation;
        }

        public void setEdit_depreciation(String edit_depreciation) {
            this.edit_depreciation = edit_depreciation;
        }

        public String getEdit_depreciations() {
            return edit_depreciations;
        }

        public void setEdit_depreciations(String edit_depreciations) {
            this.edit_depreciations = edit_depreciations;
        }

        public String getDelete_depreciation() {
            return delete_depreciation;
        }

        public void setDelete_depreciation(String delete_depreciation) {
            this.delete_depreciation = delete_depreciation;
        }

        public String getDelete_depreciations() {
            return delete_depreciations;
        }

        public void setDelete_depreciations(String delete_depreciations) {
            this.delete_depreciations = delete_depreciations;
        }

        public String getView_recurring_fee() {
            return view_recurring_fee;
        }

        public void setView_recurring_fee(String view_recurring_fee) {
            this.view_recurring_fee = view_recurring_fee;
        }

        public String getAdd_recurring_fee() {
            return add_recurring_fee;
        }

        public void setAdd_recurring_fee(String add_recurring_fee) {
            this.add_recurring_fee = add_recurring_fee;
        }

        public String getEdit_recurring_fee() {
            return edit_recurring_fee;
        }

        public void setEdit_recurring_fee(String edit_recurring_fee) {
            this.edit_recurring_fee = edit_recurring_fee;
        }

        public String getDelete_recurring_fee() {
            return delete_recurring_fee;
        }

        public void setDelete_recurring_fee(String delete_recurring_fee) {
            this.delete_recurring_fee = delete_recurring_fee;
        }

        public String getView_customer_search() {
            return view_customer_search;
        }

        public void setView_customer_search(String view_customer_search) {
            this.view_customer_search = view_customer_search;
        }

        public String getAdd_customer() {
            return add_customer;
        }

        public void setAdd_customer(String add_customer) {
            this.add_customer = add_customer;
        }

        public String getEdit_customer_card_number() {
            return edit_customer_card_number;
        }

        public void setEdit_customer_card_number(String edit_customer_card_number) {
            this.edit_customer_card_number = edit_customer_card_number;
        }

        public String getPrint_customer_transactions() {
            return print_customer_transactions;
        }

        public void setPrint_customer_transactions(String print_customer_transactions) {
            this.print_customer_transactions = print_customer_transactions;
        }

        public String getEdit_customer_info() {
            return edit_customer_info;
        }

        public void setEdit_customer_info(String edit_customer_info) {
            this.edit_customer_info = edit_customer_info;
        }

        public String getCustomer_visit_add() {
            return customer_visit_add;
        }

        public void setCustomer_visit_add(String customer_visit_add) {
            this.customer_visit_add = customer_visit_add;
        }

        public String getCustomer_visit_redeem() {
            return customer_visit_redeem;
        }

        public void setCustomer_visit_redeem(String customer_visit_redeem) {
            this.customer_visit_redeem = customer_visit_redeem;
        }

        public String getCustomer_visit_delete() {
            return customer_visit_delete;
        }

        public void setCustomer_visit_delete(String customer_visit_delete) {
            this.customer_visit_delete = customer_visit_delete;
        }

        public String getCustomer_visits_view() {
            return customer_visits_view;
        }

        public void setCustomer_visits_view(String customer_visits_view) {
            this.customer_visits_view = customer_visits_view;
        }

        public String getManage_cards() {
            return manage_cards;
        }

        public void setManage_cards(String manage_cards) {
            this.manage_cards = manage_cards;
        }

        public String getManage_fields() {
            return manage_fields;
        }

        public void setManage_fields(String manage_fields) {
            this.manage_fields = manage_fields;
        }

        public String getList_fields() {
            return list_fields;
        }

        public void setList_fields(String list_fields) {
            this.list_fields = list_fields;
        }

        public String getImport_customers() {
            return import_customers;
        }

        public void setImport_customers(String import_customers) {
            this.import_customers = import_customers;
        }

        public String getImport_transactions() {
            return import_transactions;
        }

        public void setImport_transactions(String import_transactions) {
            this.import_transactions = import_transactions;
        }

        public String getApi_batch_customers() {
            return api_batch_customers;
        }

        public void setApi_batch_customers(String api_batch_customers) {
            this.api_batch_customers = api_batch_customers;
        }

        public String getApi_batch_transactions() {
            return api_batch_transactions;
        }

        public void setApi_batch_transactions(String api_batch_transactions) {
            this.api_batch_transactions = api_batch_transactions;
        }

        public String getView_user() {
            return view_user;
        }

        public void setView_user(String view_user) {
            this.view_user = view_user;
        }

        public String getEdit_user() {
            return edit_user;
        }

        public void setEdit_user(String edit_user) {
            this.edit_user = edit_user;
        }

        public String getDelete_user() {
            return delete_user;
        }

        public void setDelete_user(String delete_user) {
            this.delete_user = delete_user;
        }

        public String getAdd_user() {
            return add_user;
        }

        public void setAdd_user(String add_user) {
            this.add_user = add_user;
        }

        public String getEdit_users_campaigns_list() {
            return edit_users_campaigns_list;
        }

        public void setEdit_users_campaigns_list(String edit_users_campaigns_list) {
            this.edit_users_campaigns_list = edit_users_campaigns_list;
        }

        public String getEdit_my_info() {
            return edit_my_info;
        }

        public void setEdit_my_info(String edit_my_info) {
            this.edit_my_info = edit_my_info;
        }

        public String getReport_on_users() {
            return report_on_users;
        }

        public void setReport_on_users(String report_on_users) {
            this.report_on_users = report_on_users;
        }

        public String getShow_administrator_option() {
            return show_administrator_option;
        }

        public void setShow_administrator_option(String show_administrator_option) {
            this.show_administrator_option = show_administrator_option;
        }

        public String getShow_manager_option() {
            return show_manager_option;
        }

        public void setShow_manager_option(String show_manager_option) {
            this.show_manager_option = show_manager_option;
        }

        public String getShow_coaltion_store_manager_option() {
            return show_coaltion_store_manager_option;
        }

        public void setShow_coaltion_store_manager_option(String show_coaltion_store_manager_option) {
            this.show_coaltion_store_manager_option = show_coaltion_store_manager_option;
        }

        public String getShow_agency_client_import_option() {
            return show_agency_client_import_option;
        }

        public void setShow_agency_client_import_option(String show_agency_client_import_option) {
            this.show_agency_client_import_option = show_agency_client_import_option;
        }

        public String getShow_agency_client_option() {
            return show_agency_client_option;
        }

        public void setShow_agency_client_option(String show_agency_client_option) {
            this.show_agency_client_option = show_agency_client_option;
        }

        public String getShow_campaign_manager_option() {
            return show_campaign_manager_option;
        }

        public void setShow_campaign_manager_option(String show_campaign_manager_option) {
            this.show_campaign_manager_option = show_campaign_manager_option;
        }

        public String getShow_fulfillment_contractor_option() {
            return show_fulfillment_contractor_option;
        }

        public void setShow_fulfillment_contractor_option(String show_fulfillment_contractor_option) {
            this.show_fulfillment_contractor_option = show_fulfillment_contractor_option;
        }

        public String getShow_accountant_option() {
            return show_accountant_option;
        }

        public void setShow_accountant_option(String show_accountant_option) {
            this.show_accountant_option = show_accountant_option;
        }

        public String getShow_observer_option() {
            return show_observer_option;
        }

        public void setShow_observer_option(String show_observer_option) {
            this.show_observer_option = show_observer_option;
        }

        public String getAccess_reports() {
            return access_reports;
        }

        public void setAccess_reports(String access_reports) {
            this.access_reports = access_reports;
        }

        public String getExport_apex() {
            return export_apex;
        }

        public void setExport_apex(String export_apex) {
            this.export_apex = export_apex;
        }

        public String getExport_excel() {
            return export_excel;
        }

        public void setExport_excel(String export_excel) {
            this.export_excel = export_excel;
        }

        public String getExport_csv() {
            return export_csv;
        }

        public void setExport_csv(String export_csv) {
            this.export_csv = export_csv;
        }

        public String getExport_mailchimp() {
            return export_mailchimp;
        }

        public void setExport_mailchimp(String export_mailchimp) {
            this.export_mailchimp = export_mailchimp;
        }

        public String getMake_bookmarks() {
            return make_bookmarks;
        }

        public void setMake_bookmarks(String make_bookmarks) {
            this.make_bookmarks = make_bookmarks;
        }

        public String getAffiliate() {
            return affiliate;
        }

        public void setAffiliate(String affiliate) {
            this.affiliate = affiliate;
        }
    }
}
