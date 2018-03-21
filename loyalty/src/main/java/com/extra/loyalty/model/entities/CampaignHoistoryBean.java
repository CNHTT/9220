package com.extra.loyalty.model.entities;

import java.util.List;

/**
 * Created by 戴尔 on 2018/1/12.
 */

public class CampaignHoistoryBean {


    /**
     * campaign_id : 1289748557973069
     * campaign_type : points
     * campaign_name : SATUGURU Rewards
     * customer : {"code":"6000000941594761","card_number":"1540280f","first_name":"nnn","last_name":"mmk","balance":"0","last_transaction":"2018-01-05","last_transaction_points":"0","last_redemption":"0000-00-00","last_redemption_points":"0","transactions":[{"id":"511","date":"2018-01-05","authorization":"Account Activated.","service_product":"Activation","redeemed":"N","amount":"0","campaign":"1289748557973069","user_name":"coaldemolgAPI","real":"Y"}]}
     */

    private String campaign_id;
    private String campaign_type;
    private String campaign_name;
    private CustomerBean customer;

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCampaign_type() {
        return campaign_type;
    }

    public void setCampaign_type(String campaign_type) {
        this.campaign_type = campaign_type;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public static class CustomerBean {
        /**
         * code : 6000000941594761
         * card_number : 1540280f
         * first_name : nnn
         * last_name : mmk
         * balance : 0
         * last_transaction : 2018-01-05
         * last_transaction_points : 0
         * last_redemption : 0000-00-00
         * last_redemption_points : 0
         * transactions : [{"id":"511","date":"2018-01-05","authorization":"Account Activated.","service_product":"Activation","redeemed":"N","amount":"0","campaign":"1289748557973069","user_name":"coaldemolgAPI","real":"Y"}]
         */

        private String code;
        private String card_number;
        private String first_name;
        private String last_name;
        private String balance;
        private String last_transaction;
        private String last_transaction_points;
        private String last_redemption;
        private String last_redemption_points;
        private List<TransactionsBean> transactions;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getLast_transaction() {
            return last_transaction;
        }

        public void setLast_transaction(String last_transaction) {
            this.last_transaction = last_transaction;
        }

        public String getLast_transaction_points() {
            return last_transaction_points;
        }

        public void setLast_transaction_points(String last_transaction_points) {
            this.last_transaction_points = last_transaction_points;
        }

        public String getLast_redemption() {
            return last_redemption;
        }

        public void setLast_redemption(String last_redemption) {
            this.last_redemption = last_redemption;
        }

        public String getLast_redemption_points() {
            return last_redemption_points;
        }

        public void setLast_redemption_points(String last_redemption_points) {
            this.last_redemption_points = last_redemption_points;
        }

        public List<TransactionsBean> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<TransactionsBean> transactions) {
            this.transactions = transactions;
        }

        public static class TransactionsBean {
            /**
             * id : 511
             * date : 2018-01-05
             * authorization : Account Activated.
             * service_product : Activation
             * redeemed : N
             * amount : 0
             * campaign : 1289748557973069
             * user_name : coaldemolgAPI
             * real : Y
             */

            private String id;
            private String date;
            private String authorization;
            private String service_product;
            private String redeemed;
            private String amount;
            private String campaign;
            private String user_name;
            private String real;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAuthorization() {
                return authorization;
            }

            public void setAuthorization(String authorization) {
                this.authorization = authorization;
            }

            public String getService_product() {
                return service_product;
            }

            public void setService_product(String service_product) {
                this.service_product = service_product;
            }

            public String getRedeemed() {
                return redeemed;
            }

            public void setRedeemed(String redeemed) {
                this.redeemed = redeemed;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCampaign() {
                return campaign;
            }

            public void setCampaign(String campaign) {
                this.campaign = campaign;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getReal() {
                return real;
            }

            public void setReal(String real) {
                this.real = real;
            }
        }
    }
}
