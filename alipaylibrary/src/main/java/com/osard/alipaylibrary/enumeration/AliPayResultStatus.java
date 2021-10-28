package com.osard.alipaylibrary.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付返回的状态码对应状态描述，仅用于在sdk返回支付状态不是成功是，出现的提示信息
 * <p>
 * https://opendocs.alipay.com/open/204/105301
 */

public enum AliPayResultStatus {

    /*
     状态码 ， 官方描述
     */
    FAIL("4000", "订单支付失败"),
    REPEATED_REQUESTS("5000", "重复请求"),
    USER_CANCELLED("6001", "用户中途取消"),
    NETWORK_ERROR("6002", "网络连接出错"),
    PAY_RESULT_UNKNOWN("6004", "支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态"),
    PROCESSING("8000", "正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态"),
    SUCCEED("9000", "订单支付成功"),
    DEFAULT("-100000", "其他支付错误");

    private final static Map<String, AliPayResultStatus> MAP = new HashMap<>();

    static {
        for (AliPayResultStatus obj : AliPayResultStatus.values()) {
            MAP.put(obj.type, obj);
        }
    }

    private final String type;
    private final String label;

    AliPayResultStatus(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public static AliPayResultStatus getType(String type) {
        AliPayResultStatus obj = MAP.get(type);
        if (null == obj) {
            obj = AliPayResultStatus.DEFAULT;
        }
        return obj;
    }

    /**
     * 是否是支付成功
     *
     * @param aliPayStatus
     * @return
     */
    public static boolean isSucceed(AliPayResultStatus aliPayStatus) {
        return SUCCEED.equals(aliPayStatus);
    }

    /**
     * 是否是支付成功
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
