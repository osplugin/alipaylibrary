package com.osard.alipaylibrary.result.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class AliPayResultParam {

    @JSONField(name = "alipay_trade_app_pay_response")
    private AlipayTradeAppPayResponse alipayTradeAppPayResponse;

    @JSONField(name = "sign")
    private String sign;

    @JSONField(name = "sign_type")
    private String signType;

    public AlipayTradeAppPayResponse getAlipayTradeAppPayResponse() {
        return alipayTradeAppPayResponse;
    }

    public void setAlipayTradeAppPayResponse(AlipayTradeAppPayResponse alipayTradeAppPayResponse) {
        this.alipayTradeAppPayResponse = alipayTradeAppPayResponse;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
