package com.osard.alipaylibrary.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录认证的ResultStatus
 *
 * https://opendocs.alipay.com/open/218/105327
 */
public enum AliAuthResultStatus {

    /*
    状态码 ， 官方描述
    */
    FAIL("4000", "系统异常"),
    USER_CANCELLED("6001", "用户中途取消"),
    NETWORK_ERROR("6002", "网络连接出错"),
    SUCCEED("9000", "请求处理成功"),

    DEFAULT("-100000", "其他错误");

    private final static Map<String, AliAuthResultStatus> MAP = new HashMap<>();

    static {
        for (AliAuthResultStatus obj : AliAuthResultStatus.values()) {
            MAP.put(obj.type, obj);
        }
    }

    private final String type;
    private final String label;

    AliAuthResultStatus(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public static AliAuthResultStatus getType(String type) {
        AliAuthResultStatus obj = MAP.get(type);
        if (null == obj) {
            obj = AliAuthResultStatus.DEFAULT;
        }
        return obj;
    }

    /**
     * 是否是成功
     *
     * @param aliPayStatus
     * @return
     */
    public static boolean isSucceed(AliAuthResultStatus aliPayStatus) {
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
