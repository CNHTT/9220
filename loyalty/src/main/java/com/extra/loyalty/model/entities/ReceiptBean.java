package com.extra.loyalty.model.entities;

/**
 * Created by 戴尔 on 2018/1/16.
 */

public class ReceiptBean {


    /**
     * account_name : RAYONS SPORT FC
     * campaign : {"id":"1106069098931231","name":"LABODEGA WALLET","type":"giftcard"}
     * customer : {"first_name":"nnn","last_name":"mmk","card_number":"1540280f","phone":"666","email":"jjjkn@x.jn","custom_date":"2018-01-11","street1":"jjii","custom_field_3":{"label":"Sentinel ALT","data":""}}
     * transaction : {"id":"517","add":{"amount":"1.00","currency":"RWF","glyph":"Fr","description":"LABODEGA WALLET"},"balance":{"amount":"1.00","currency":"RWF","glyph":"Fr"},"cumulative_balance":{"amount":"1.00","currency":"RWF","glyph":"Fr"},"coalition_cumulative_balance":{"amount":"1.00","currency":"RWF","glyph":"Fr"}}
     */

    private String account_name;
    private CampaignBean campaign;
    private CustomerBean customer;
    private TransactionBean transaction;
    /**
     * agency : {"id":"loyaltygator","name":"Loyalty Gator Inc.","email":"support@loyaltygator.com"}
     * transaction : {"id":"522","deduct":{"amount":"-1","currency":"RWF","description":"LABODEGA WALLET"},"balance":{"amount":"6.00","currency":"RWF"},"cumulative_balance":{"amount":"7.00","currency":"RWF"},"coalition_cumulative_balance":{"amount":"7.00","currency":"RWF"}}
     */

    private AgencyBean agency;

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public CampaignBean getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignBean campaign) {
        this.campaign = campaign;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public TransactionBean getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionBean transaction) {
        this.transaction = transaction;
    }

    public AgencyBean getAgency() {
        return agency;
    }

    public void setAgency(AgencyBean agency) {
        this.agency = agency;
    }


    public static class CampaignBean {
        /**
         * id : 1106069098931231
         * name : LABODEGA WALLET
         * type : giftcard
         */

        private String id;
        private String name;
        private String type;

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
    }

    public static class CustomerBean {
        /**
         * first_name : nnn
         * last_name : mmk
         * card_number : 1540280f
         * phone : 666
         * email : jjjkn@x.jn
         * custom_date : 2018-01-11
         * street1 : jjii
         * custom_field_3 : {"label":"Sentinel ALT","data":""}
         */

        private String first_name;
        private String last_name;
        private String card_number;
        private String phone;
        private String email;
        private String custom_date;
        private String street1;
        private CustomField3Bean custom_field_3;

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

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCustom_date() {
            return custom_date;
        }

        public void setCustom_date(String custom_date) {
            this.custom_date = custom_date;
        }

        public String getStreet1() {
            return street1;
        }

        public void setStreet1(String street1) {
            this.street1 = street1;
        }

        public CustomField3Bean getCustom_field_3() {
            return custom_field_3;
        }

        public void setCustom_field_3(CustomField3Bean custom_field_3) {
            this.custom_field_3 = custom_field_3;
        }

        public static class CustomField3Bean {
            /**
             * label : Sentinel ALT
             * data :
             */

            private String label;
            private String data;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }

    public static class TransactionBean {
        /**
         * id : 517
         * add : {"amount":"1.00","currency":"RWF","glyph":"Fr","description":"LABODEGA WALLET"}
         * balance : {"amount":"1.00","currency":"RWF","glyph":"Fr"}
         * cumulative_balance : {"amount":"1.00","currency":"RWF","glyph":"Fr"}
         * coalition_cumulative_balance : {"amount":"1.00","currency":"RWF","glyph":"Fr"}
         */




        private String id;
        private AddBean add;
        private BalanceBean balance;
        private CumulativeBalanceBean cumulative_balance;
        private CoalitionCumulativeBalanceBean coalition_cumulative_balance;
        /**
         * purchase : {"amount":"1","currency":"RWF","glyph":"Fr"}
         * recorded : {"points":"0","description":""}
         */

        private BalanceBean purchase;
        private RecordedBean recorded;
        /**
         * deduct : {"amount":"-1","currency":"RWF","description":"LABODEGA WALLET"}
         */

        private AddBean deduct;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public AddBean getAdd() {
            return add;
        }

        public void setAdd(AddBean add) {
            this.add = add;
        }

        public BalanceBean getBalance() {
            return balance;
        }

        public void setBalance(BalanceBean balance) {
            this.balance = balance;
        }

        public CumulativeBalanceBean getCumulative_balance() {
            return cumulative_balance;
        }

        public void setCumulative_balance(CumulativeBalanceBean cumulative_balance) {
            this.cumulative_balance = cumulative_balance;
        }

        public CoalitionCumulativeBalanceBean getCoalition_cumulative_balance() {
            return coalition_cumulative_balance;
        }

        public void setCoalition_cumulative_balance(CoalitionCumulativeBalanceBean coalition_cumulative_balance) {
            this.coalition_cumulative_balance = coalition_cumulative_balance;
        }

        public BalanceBean getPurchase() {
            return purchase;
        }

        public void setPurchase(BalanceBean purchase) {
            this.purchase = purchase;
        }

        public RecordedBean getRecorded() {
            return recorded;
        }

        public void setRecorded(RecordedBean recorded) {
            this.recorded = recorded;
        }

        public AddBean getDeduct() {
            return deduct;
        }

        public void setDeduct(AddBean deduct) {
            this.deduct = deduct;
        }

        public static class AddBean {
            /**
             * amount : 1.00
             * currency : RWF
             * glyph : Fr
             * description : LABODEGA WALLET
             */

            private String amount;
            private String currency;
            private String glyph;
            private String description;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class BalanceBean {
            /**
             * amount : 1.00
             * currency : RWF
             * glyph : Fr
             */

            private String amount;
            private String currency;
            private String glyph;
            private String points;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
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
        }

        public static class CumulativeBalanceBean {
            /**
             * amount : 1.00
             * currency : RWF
             * glyph : Fr
             */

            private String amount;
            private String currency;
            private String glyph;
            private String points;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
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
        }

        public static class CoalitionCumulativeBalanceBean {
            /**
             * amount : 1.00
             * currency : RWF
             * glyph : Fr
             */

            private String amount;
            private String currency;
            private String glyph;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
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
        }

        public static class RecordedBean {
            /**
             * points : 0
             * description :
             */

            private String points;
            private String description;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }

    public static class AgencyBean {
        /**
         * id : loyaltygator
         * name : Loyalty Gator Inc.
         * email : support@loyaltygator.com
         */

        private String id;
        private String name;
        private String email;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
