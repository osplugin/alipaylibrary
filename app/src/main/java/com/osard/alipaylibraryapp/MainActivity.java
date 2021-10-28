package com.osard.alipaylibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.osard.alipaylibrary.callback.AliUserCancel;
import com.osard.alipaylibrary.callback.pay.AliPayFail;
import com.osard.alipaylibrary.callback.pay.AliPaySucceed;
import com.osard.alipaylibrary.result.AliPayResult;
import com.osard.alipaylibrary.util.AliPayUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AliPayUtil.newInstance()
                .setSucceed(new AliPaySucceed() {
                    @Override
                    public void succeed(AliPayResult aliPayResult) {

                    }
                })
                .setFail(new AliPayFail() {
                    @Override
                    public void fail(AliPayResult aliPayResult) {
                        //支付宝支付返回的状态码对应状态
                        String resultStatus = aliPayResult.getResultStatus();
                        //支付宝公共错误码
                        String resultCode = aliPayResult.getResultCode();
                    }
                })
                .setUserCancel(new AliUserCancel() {
                    @Override
                    public void userCancel() {

                    }
                })
                .payV2(this, "从服务器获取最终签名后的支付信息");

    }
}