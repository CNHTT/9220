package com.extra.loyalty.model.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 戴尔 on 2017/11/24.
 */

public class Result implements Serializable {

    private String status;
    private String message;


    private  ReceiptBean receipt;
    private List<Object> errors;

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
    private List<UserBean> users;
    private List<CustomerBean> customers;
    private CustomerBean customer;
    /**
     * campaign : {"id":"6971168414799905"}
     */

    private CampaignsBean campaign;
    /**
     * user : {"user_id":"Xtra","user_api_key":"7c4a8d09ca3762af61e59520943dc26494f8941b"}
     */

    private UserBean user;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AccountInfo getAccount() {
        return account;
    }

    public List<CustomerBean> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerBean> customers) {
        this.customers = customers;
    }

    public List<Object> getErrors() {
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
        else return (String) errors.get(0);
    }

    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerBean getCustomer() {
        return customer;
    }


    public ReceiptBean getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptBean receipt) {
        this.receipt = receipt;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public static class CampaignsBean  implements Serializable{
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


        /**
         * balance : 0
         * cumulative : 0
         * balance_coalition : 0
         * cumulative_coalition : 0
         * last_transaction : 2018-01-05
         * first_transaction : 2018-01-05
         */

        private String balance;
        private String cumulative;
        private String balance_coalition;
        private String cumulative_coalition;
        private String last_transaction;
        private String first_transaction;
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

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getCumulative() {
            return cumulative;
        }

        public void setCumulative(String cumulative) {
            this.cumulative = cumulative;
        }

        public String getBalance_coalition() {
            return balance_coalition;
        }

        public void setBalance_coalition(String balance_coalition) {
            this.balance_coalition = balance_coalition;
        }

        public String getCumulative_coalition() {
            return cumulative_coalition;
        }

        public void setCumulative_coalition(String cumulative_coalition) {
            this.cumulative_coalition = cumulative_coalition;
        }

        public String getLast_transaction() {
            return last_transaction;
        }

        public void setLast_transaction(String last_transaction) {
            this.last_transaction = last_transaction;
        }

        public String getFirst_transaction() {
            return first_transaction;
        }

        public void setFirst_transaction(String first_transaction) {
            this.first_transaction = first_transaction;
        }

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
            @SerializedName("balance")
            private String balanceX;
            @SerializedName("last_transaction")
            private String last_transactionX;
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

            public String getBalanceX() {
                return balanceX;
            }

            public void setBalanceX(String balanceX) {
                this.balanceX = balanceX;
            }

            public String getLast_transactionX() {
                return last_transactionX;
            }

            public void setLast_transactionX(String last_transactionX) {
                this.last_transactionX = last_transactionX;
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

                @SerializedName("id")
                private String idX;
                private String date;
                private String authorization;
                private String service_product;
                private String redeemed;
                private String amount;
                private String campaign;
                private String user_name;
                private String real;

                public String getIdX() {
                    return idX;
                }

                public void setIdX(String idX) {
                    this.idX = idX;
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

}
