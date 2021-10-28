package com.osard.alipaylibrary.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.osard.alipaylibrary.callback.AliUserCancel;
import com.osard.alipaylibrary.callback.pay.AliPayFail;
import com.osard.alipaylibrary.callback.pay.AliPaySucceed;
import com.osard.alipaylibrary.enumeration.AliAuthResultCode;
import com.osard.alipaylibrary.enumeration.AliAuthResultStatus;
import com.osard.alipaylibrary.result.AliPayResult;

import java.util.Map;

/**
 * 支付宝支付工具
 */
public class AliPayUtil {

    private AliPayUtil() {
    }

    public static AliPayUtil newInstance(){
        return new AliPayUtil();
    }

    private AliPaySucceed succeed;
    private AliPayFail fail;
    private AliUserCancel userCancel;

    /**
     * 注册成功监听
     */
    public AliPayUtil setSucceed(AliPaySucceed succeed) {
        this.succeed = succeed;
        return this;
    }

    /**
     * 注册失败监听
     */
    public AliPayUtil setFail(AliPayFail fail) {
        this.fail = fail;
        return this;
    }

    /**
     * 注册用户取消监听
     */
    public AliPayUtil setUserCancel(AliUserCancel userCancel) {
        this.userCancel = userCancel;
        return this;
    }

    /**
     * 支付宝支付业务示例
     * @param orderInfo 根据支付宝要求从服务器获取签名后的支付订单信息
     */
    public void payV2(Activity activity, String orderInfo) {
        // 必须异步调用
        Thread payThread = new Thread(() -> {
            PayTask alipay = new PayTask(activity);
            Map<String, String> result = alipay.payV2(orderInfo, true);
//            Log.i("msp", result.toString());

            AliPayResult aliPayResult = new AliPayResult(result);
            String resultStatus = aliPayResult.getResultStatus();
            String resultCode = aliPayResult.getResultCode();

            if (AliAuthResultStatus.USER_CANCELLED.getType().equals(resultStatus)) {
                if (null != userCancel) {
                    new Handler(Looper.getMainLooper()).post(() -> userCancel.userCancel());
                }
            } else if (AliAuthResultStatus.isSucceed(resultStatus)) {
                if (AliAuthResultCode.isSucceed(resultCode)) {
                    if (null != succeed) {
                        new Handler(Looper.getMainLooper()).post(() -> succeed.succeed(aliPayResult));
                    }
                } else {
                    if (null != fail) {
                        new Handler(Looper.getMainLooper()).post(() -> fail.fail(aliPayResult));
                    }
                }
            } else {
                if (null != fail) {
                    new Handler(Looper.getMainLooper()).post(() -> fail.fail(aliPayResult));
                }
            }
        });
        payThread.start();
    }

}
