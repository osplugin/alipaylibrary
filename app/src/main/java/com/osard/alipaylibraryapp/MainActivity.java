package com.osard.alipaylibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.osard.alipaylibrary.callback.AliUserCancel;
import com.osard.alipaylibrary.callback.auth.AliAuthFail;
import com.osard.alipaylibrary.callback.auth.AliAuthSucceed;
import com.osard.alipaylibrary.callback.pay.AliPayFail;
import com.osard.alipaylibrary.callback.pay.AliPaySucceed;
import com.osard.alipaylibrary.enumeration.AliAuthResultCode;
import com.osard.alipaylibrary.enumeration.AliAuthResultStatus;
import com.osard.alipaylibrary.enumeration.AliPayResultStatus;
import com.osard.alipaylibrary.enumeration.AliPublicCodeErr;
import com.osard.alipaylibrary.result.AliAuthResult;
import com.osard.alipaylibrary.result.AliPayResult;
import com.osard.alipaylibrary.result.entity.AliPayResultParam;
import com.osard.alipaylibrary.util.AliAuthUtil;
import com.osard.alipaylibrary.util.AliPayUtil;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String result = "{\"alipay_trade_app_pay_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2014072300007148\",\"out_trade_no\":\"081622560194853\",\"trade_no\":\"2016081621001004400236957647\",\"total_amount\":\"0.01\",\"seller_id\":\"2088702849871851\",\"charset\":\"utf-8\",\"timestamp\":\"2016-10-11 17:43:36\" },\"sign\":\"NGfStJf3i3ooWBuCDIQSumOpaGBcQz+aoAqyGh3W6EqA/gmyPYwLJ2REFijY9XPTApI9YglZyMw+ZMhd3kb0mh4RAXMrb6mekX4Zu8Nf6geOwIa9kLOnw0IMCjxi4abDIfXhxrXyj********\",\"sign_type\":\"RSA2\" }";
        AliPayResultParam param = JSON.parseObject(result, AliPayResultParam.class);

        Map<String, String> result2 = new HashMap<>();
        result2.put("result", "success=true&auth_code=d9d1b5acc26e461dbfcb6974c8ff5E64&result_code=200 &user_id=2088003646494707");
        result2.put("resultStatus", "9000");
        result2.put("memo", "处理成功");

        AliAuthResult ali = new AliAuthResult(result2, true);

        AliPayUtil.newInstance()
                .setSucceed(aliPayResult -> {

                })
                .setFail(aliPayResult -> {
                    //支付宝支付返回的状态码对应状态
                    AliPayResultStatus resultStatus = aliPayResult.getResultStatus();
                    //支付宝公共错误码
                    AliPublicCodeErr resultCode = aliPayResult.getResultCode();
                })
                .setUserCancel(() -> {

                })
                .payV2(this, "从服务器获取最终签名后的支付信息");


        AliAuthUtil.newInstance()
                .setSucceed(aliAuthResult -> {
                    aliAuthResult.getAuthCode();
                    aliAuthResult.getAlipayOpenId();
                })
                .setUserCancel(() -> {

                })
                .setFail(aliAuthResult -> {
                    //支付宝登录认证的ResultStatus
                    AliAuthResultStatus resultStatus = aliAuthResult.getResultStatus();
                    //支付宝登录认证的ResultCode
                    AliAuthResultCode resultCode = aliAuthResult.getResultCode();
                })
                .authV2(this, "从服务器获取最终签名后的登录信息");

    }
}