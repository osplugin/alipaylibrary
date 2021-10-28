package com.osard.alipaylibrary.util;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.alipay.sdk.app.AuthTask;
import com.osard.alipaylibrary.callback.auth.AliAuthSucceed;
import com.osard.alipaylibrary.callback.auth.AliAuthFail;
import com.osard.alipaylibrary.callback.AliUserCancel;
import com.osard.alipaylibrary.enumeration.AliAuthResultCode;
import com.osard.alipaylibrary.enumeration.AliAuthResultStatus;
import com.osard.alipaylibrary.result.AliAuthResult;

import java.util.Map;

/**
 * 支付宝登录授权的工具
 */
public class AliAuthUtil {

    private AliAuthUtil() {
    }

    public static AliAuthUtil newInstance() {
        return new AliAuthUtil();
    }

    private AliAuthSucceed succeed;
    private AliAuthFail fail;
    private AliUserCancel userCancel;

    /**
     * 注册成功监听
     */
    public AliAuthUtil setSucceed(AliAuthSucceed succeed) {
        this.succeed = succeed;
        return this;
    }

    /**
     * 注册失败监听
     */
    public AliAuthUtil setFail(AliAuthFail fail) {
        this.fail = fail;
        return this;
    }

    /**
     * 注册用户取消监听
     */
    public AliAuthUtil setUserCancel(AliUserCancel userCancel) {
        this.userCancel = userCancel;
        return this;
    }

    /**
     * 支付宝账户授权
     *
     * @param authInfo 按照支付宝要求从服务器获取签名后的授权信息
     */
    public void authV2(Activity activity, String authInfo) {
        // 必须异步调用
        Thread authThread = new Thread(() -> {
            // 构造AuthTask 对象
            AuthTask authTask = new AuthTask(activity);
            // 调用授权接口，获取授权结果
            Map<String, String> result = authTask.authV2(authInfo, true);

            AliAuthResult aliAuthResult = new AliAuthResult(result, true);
            String resultStatus = aliAuthResult.getResultStatus();
            String resultCode = aliAuthResult.getResultCode();

            if (AliAuthResultStatus.USER_CANCELLED.getType().equals(resultStatus)) {
                if (null != userCancel) {
                    new Handler(Looper.getMainLooper()).post(() -> userCancel.userCancel());
                }
            } else if (AliAuthResultStatus.isSucceed(resultStatus)) {
                if (AliAuthResultCode.isSucceed(resultCode)) {
                    if (null != succeed) {
                        new Handler(Looper.getMainLooper()).post(() -> succeed.succeed(aliAuthResult));
                    }
                } else {
                    if (null != fail) {
                        new Handler(Looper.getMainLooper()).post(() -> fail.fail(aliAuthResult));
                    }
                }
            } else {
                if (null != fail) {
                    new Handler(Looper.getMainLooper()).post(() -> fail.fail(aliAuthResult));
                }
            }
        });
        authThread.start();
    }

}
