package com.extra.loyalty.model.entities;

import java.util.List;

/**
 * Created by 戴尔 on 2017/11/24.
 */

public class Result {





    private String status;


    private List<String> errors;

    private String error;

    //Client - Account Info
    private AccountInfo account;
    /**
     * account_name : coaldemolg
     * campaigns : [{"id":"1106069098931231","name":"LABODEGA WALLET","type":"giftcard","in_coalition":"Y","created_stamp":"2017-11-23 12:45:38","currency":"RWF","glyph":""},{"id":"1161841772406656","name":"Simba Rewards","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:56:01","points_ratio":"0.05","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"1289748557973069","name":"SATUGURU Rewards","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:55:30","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"1504532322686155","name":"Lemigo Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:59:54","points_ratio":"0.2","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"1609143174489333","name":"JACAJU  Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:54:22","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"1647489427517008","name":"Camelia Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 05:03:54","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"1655055478534996","name":"BeFit Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 05:03:09","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"1696214675052881","name":"Mirror Hotel reward","type":"points","in_coalition":"Y","created_stamp":"2017-11-16 02:50:04","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"1864896211315774","name":"Class Club Reward","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 05:02:29","points_ratio":"0.2","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"2090874850702625","name":"Red star Reward","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 05:04:33","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"3395303761728590","name":"Horizon Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 05:00:33","points_ratio":"0.05","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"4145294197053929","name":"Rwandair Dream milles","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:53:39","points_ratio":"0.05","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"5359845569788743","name":"Champion Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-26 09:49:02","points_ratio":"0.3","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"5388147263279769","name":"Sand downer Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:58:25","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"6294999403206718","name":"Plus250 Bonus","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 08:53:46","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"7065634070921819","name":"SP Reward","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 05:01:50","points_ratio":"0.5","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"8053393545371514","name":"Mariott Reward","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:59:04","points_ratio":"0.2","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"8405467525402928","name":"Engine Points","type":"points","in_coalition":"Y","created_stamp":"2017-11-14 04:57:16","points_ratio":"0.5","reward_ratio":"0","currency":"RWF","glyph":""},{"id":"9296417043104497","name":"LABODEGA REWARDS","type":"points","in_coalition":"Y","created_stamp":"2017-11-13 01:37:40","points_ratio":"0.1","reward_ratio":"0","currency":"RWF","glyph":""}]
     * pagination : {"total":"19","offset":"0"}
     */

    private String account_name;
    private PaginationBean pagination;
    private List<CampaignsBean> campaigns;
    /**
     * campaign : {"id":"6971168414799905"}
     */

    private CampaignsBean campaign;




    public AccountInfo getAccount() {
        return account;
    }


    public List<String> getErrors() {
        return errors;
    }

    public String getStatus() {
        return status;
    }

    public boolean checkResult(){
        return status.equals("success");
    }

    public String getError() {
        if (errors==null)
        return error;
        else return errors.get(0);
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public PaginationBean getPagination() {
        return pagination;
    }

    public void setPagination(PaginationBean pagination) {
        this.pagination = pagination;
    }

    public List<CampaignsBean> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignsBean> campaigns) {
        this.campaigns = campaigns;
    }

    public CampaignsBean getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignsBean campaign) {
        this.campaign = campaign;
    }

    public static class PaginationBean {
        /**
         * total : 19
         * offset : 0
         */

        private String total;
        private String offset;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }
    }

    public static class CampaignsBean {
        /**
         * id : 1106069098931231
         * name : LABODEGA WALLET
         * type : giftcard
         * in_coalition : Y
         * created_stamp : 2017-11-23 12:45:38
         * currency : RWF
         * glyph :
         * points_ratio : 0.05
         * reward_ratio : 0
         */

        private String id;
        private String name;
        private String type;
        private String in_coalition;
        private String created_stamp;
        private String currency;
        private String glyph;
        private String points_ratio;
        private String reward_ratio;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIn_coalition() {
            return in_coalition;
        }

        public void setIn_coalition(String in_coalition) {
            this.in_coalition = in_coalition;
        }

        public String getCreated_stamp() {
            return created_stamp;
        }

        public void setCreated_stamp(String created_stamp) {
            this.created_stamp = created_stamp;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getGlyph() {
            return glyph;
        }

        public void setGlyph(String glyph) {
            this.glyph = glyph;
        }

        public String getPoints_ratio() {
            return points_ratio;
        }

        public void setPoints_ratio(String points_ratio) {
            this.points_ratio = points_ratio;
        }

        public String getReward_ratio() {
            return reward_ratio;
        }

        public void setReward_ratio(String reward_ratio) {
            this.reward_ratio = reward_ratio;
        }

        @Override
        public String toString() {
            return "CampaignsBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' + '}'+"\t\r";
        }
    }
}
