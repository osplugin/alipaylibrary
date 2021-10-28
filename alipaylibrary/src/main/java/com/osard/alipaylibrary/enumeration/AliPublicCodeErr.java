package com.osard.alipaylibrary.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝公共错误码
 * <p>
 * https://opendocs.alipay.com/open/common/105806
 */

public enum AliPublicCodeErr {

    /*
     状态码 ， 官方描述
     */
    SUCCEED("10000", "接口调用成功，调用结果请参考具体的 API 文档所对应的业务返回参数。"),
    SERVICE_UNAVAILABLE("20000", "服务不可用"),
    PERMISSION_DENIED_20001("20001", "授权权限不足"),
    MISSING_PARAMETERS("40001", "缺少必选参数"),
    INVALID_PARAMETER("40002", "非法的参数"),
    SERVICE_PROCESSING_FAILURE("40004", "业务处理失败，对应业务错误码，明细错误码和解决方案请参见具体的 API 接口文档。"),
    FREQUENCY_TRANSFINITE("40005", "调用频次超限"),
    PERMISSION_DENIED_40006("40006", "权限不足"),

    DEFAULT("-100000", "其他错误");

    private final static Map<String, AliPublicCodeErr> MAP = new HashMap<>();

    static {
        for (AliPublicCodeErr obj : AliPublicCodeErr.values()) {
            MAP.put(obj.type, obj);
        }
    }

    private final String type;
    private final String label;

    AliPublicCodeErr(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public static AliPublicCodeErr getType(String type) {
        AliPublicCodeErr obj = MAP.get(type);
        if (null == obj) {
            obj = AliPublicCodeErr.DEFAULT;
        }
        return obj;
    }

    /**
     * 是否是成功
     *
     * @param aliPayStatus
     * @return
     */
    public static boolean isSucceed(AliPublicCodeErr aliPayStatus) {
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
