package com.osard.alipaylibrary.result.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 支付结果返回参数
 */
public class AlipayTradeAppPayResponse {

    /**
     * 商户网站唯一订单号	70501111111S001111119
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 该交易在支付宝系统中的交易流水号。最长 64 位。	2014112400001000340011111118
     */
    @JSONField(name = "trade_no")
    private String tradeNo;
    /**
     * 支付宝分配给开发者的应用 Id。	2014072300007148
     */
    @JSONField(name = "app_id")
    private String appId;
    /**
     * 该笔订单的资金总额，单位为 RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位。	9.00
     */
    @JSONField(name = "total_amount")
    private double totalAmount;
    /**
     * 收款支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字	20886894
     */
    @JSONField(name = "seller_id")
    private String sellerId;
    /**
     * 处理结果的描述，信息来自于 code 返回结果的描述	success
     */
    @JSONField(name = "msg")
    private String msg;
    /**
     * 编码格式	utf-8
     */
    @JSONField(name = "charset")
    private String charset;
    /**
     * 时间	2016-10-11 17:43:36
     */
    @JSONField(name = "timestamp")
    private String timestamp;
    /**
     * 结果码。具体见 公共错误码 如：10000
     */
    @JSONField(name = "code")
    private String code;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
