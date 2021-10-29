package com.osard.alipaylibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
import com.osard.alipaylibrary.util.AliAuthUtil;
import com.osard.alipaylibrary.util.AliPayUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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