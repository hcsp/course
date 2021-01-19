package com.github.hcsp.course.model;

public class AlipayQueryResult {

    /**
     * alipay_trade_query_response : {"code":"10000","msg":"Success","buyer_logon_id":"rbj***@sandbox.com","buyer_pay_amount":"0.00","buyer_user_id":"2088622955098639","buyer_user_type":"PRIVATE","invoice_amount":"0.00","out_trade_no":"testOrder1","point_amount":"0.00","receipt_amount":"0.00","send_pay_date":"2020-12-01 21:40:43","total_amount":"100.00","trade_no":"2020120122001498630504790788","trade_status":"TRADE_SUCCESS"}
     * sign : T5bbrgTzT8HRBcfEYCuboCYcmTcepOtCaVCauF0VN7qeAJXJwQKuwtF16sJaduiCGBftkEXAUOo4DsGJD3juikkQKmZ1jKOi15wRYIHrwhpXDsdP98mbbAmcd1JeWxmVDUPm57Ix0XX/YzHBd3EPK3LTGCj+kv05SOz9J35FNXrRPP9yprDNyef1fYSrf5jFvtrEKSttHOX95GqU/l9QAozIFw2rFP1WMM8tMctpaIjdYEd46bkyjz8js3qIslDlHpFubuFaROgUNP3Tbbzu8SLOVPYMpnEXQEnTtFq/msqlV7bA0zUKx/KnUmzhu6/cSplRDx3QZ+0/HRzgFV+YOw==
     */

    private AlipayTradeQueryResponseBean alipay_trade_query_response;
    private String sign;

    public AlipayTradeQueryResponseBean getAlipay_trade_query_response() {
        return alipay_trade_query_response;
    }

    public void setAlipay_trade_query_response(AlipayTradeQueryResponseBean alipay_trade_query_response) {
        this.alipay_trade_query_response = alipay_trade_query_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class AlipayTradeQueryResponseBean {
        /**
         * code : 10000
         * msg : Success
         * buyer_logon_id : rbj***@sandbox.com
         * buyer_pay_amount : 0.00
         * buyer_user_id : 2088622955098639
         * buyer_user_type : PRIVATE
         * invoice_amount : 0.00
         * out_trade_no : testOrder1
         * point_amount : 0.00
         * receipt_amount : 0.00
         * send_pay_date : 2020-12-01 21:40:43
         * total_amount : 100.00
         * trade_no : 2020120122001498630504790788
         * trade_status : TRADE_SUCCESS
         */

        private String code;
        private String msg;
        private String buyer_logon_id;
        private String buyer_pay_amount;
        private String buyer_user_id;
        private String buyer_user_type;
        private String invoice_amount;
        private String out_trade_no;
        private String point_amount;
        private String receipt_amount;
        private String send_pay_date;
        private String total_amount;
        private String trade_no;
        private String trade_status;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getBuyer_logon_id() {
            return buyer_logon_id;
        }

        public void setBuyer_logon_id(String buyer_logon_id) {
            this.buyer_logon_id = buyer_logon_id;
        }

        public String getBuyer_pay_amount() {
            return buyer_pay_amount;
        }

        public void setBuyer_pay_amount(String buyer_pay_amount) {
            this.buyer_pay_amount = buyer_pay_amount;
        }

        public String getBuyer_user_id() {
            return buyer_user_id;
        }

        public void setBuyer_user_id(String buyer_user_id) {
            this.buyer_user_id = buyer_user_id;
        }

        public String getBuyer_user_type() {
            return buyer_user_type;
        }

        public void setBuyer_user_type(String buyer_user_type) {
            this.buyer_user_type = buyer_user_type;
        }

        public String getInvoice_amount() {
            return invoice_amount;
        }

        public void setInvoice_amount(String invoice_amount) {
            this.invoice_amount = invoice_amount;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getPoint_amount() {
            return point_amount;
        }

        public void setPoint_amount(String point_amount) {
            this.point_amount = point_amount;
        }

        public String getReceipt_amount() {
            return receipt_amount;
        }

        public void setReceipt_amount(String receipt_amount) {
            this.receipt_amount = receipt_amount;
        }

        public String getSend_pay_date() {
            return send_pay_date;
        }

        public void setSend_pay_date(String send_pay_date) {
            this.send_pay_date = send_pay_date;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getTrade_status() {
            return trade_status;
        }

        public void setTrade_status(String trade_status) {
            this.trade_status = trade_status;
        }
    }
}
