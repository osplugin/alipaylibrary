package com.osard.alipaylibrary.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录认证的ResultCode
 *
 * https://opendocs.alipay.com/open/218/105327
 */
public enum AliAuthResultCode {

    /*
        状态码 ， 官方描述
    */
    SUCCEED("200", "业务处理成功，会返回 authCode"),
    SYSTEM_ANOMALY("202", "系统异常，请稍后再试或联系支付宝技术支持"),
    ACCOUNT_FROZEN("1005", "账户已冻结，如有疑问，请联系支付宝技术支持"),

    DEFAULT("-100000", "其他错误");

    private final static Map<String, AliAuthResultCode> MAP = new HashMap<>();

    static {
        for (AliAuthResultCode obj : AliAuthResultCode.values()) {
            MAP.put(obj.type, obj);
        }
    }

    private final String type;
    private final String label;

    AliAuthResultCode(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }


    public static AliAuthResultCode getType(String type) {
        AliAuthResultCode obj = MAP.get(type);
        if (null == obj) {
            obj = AliAuthResultCode.DEFAULT;
        }
        return obj;
    }

    /**
     * 是否是成功
     *
     * @param aliPayStatus
     * @return
     */
    public static boolean isSucceed(AliAuthResultCode aliPayStatus) {
        return SUCCEED.equals(aliPayStatus);
    }

    /**
     * 是否是成功
     *
     * @param type
     * @return
     */
    public static boolean isSucceed(String type) {
        try {
            return isSucceed(getType(type));
        } catch (NumberFormatException ne) {
            return false;
        }
    }


}
