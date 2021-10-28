# AlipayLibrary

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![](https://jitpack.io/v/com.gitee.osard/alipaylibrary.svg)](https://jitpack.io/#com.gitee.osard/alipaylibrary)

### 一、项目介绍
1. APP 使用示例项目，libs下含有以最新的支付宝sdk aar资源。
2. AlipayLibrary arr资源项目，需要引入的资源包项目。
3. jitpack 仓库在线引入即可
4. 支付宝sdk单独引入
5. 项目内支付宝sdk版本为 **alipaysdk-15.8.05.211018174351**
6. 若使用比此版本更新的sdk时，必须将aar资源命名为**alipaysdk.aar**然后放入libs下进行引用

### 二、工程引入工具包
**工程的build.gradle文件添加**

```
allprojects {
    repositories {
        google()
        mavenCentral()

        //jitpack 仓库
        maven { url 'https://jitpack.io' }
    }
}
```
**下载项目获取APP下libs文件夹下的alipaysdk.aar资源到你的项目**

**APP的build.gradle文件添加**
```
dependencies {
    ...
    implementation 'com.gitee.osard:alipaylibrary:1.0.1'
    //引入alipaylibrary.aar的依赖资源，以下2个
    implementation fileTree(dir: 'libs', include: ['alipaysdk.aar'])
    implementation 'com.alibaba:fastjson:1.2.78'
}
```

### 三、使用说明

1.  支付
```java
        AliPayUtil.newInstance()
                .setSucceed(aliPayResult -> {

                })
                .setFail(aliPayResult -> {
                    //支付宝支付返回的状态码对应状态
                    String resultStatus = aliPayResult.getResultStatus();
                    //支付宝公共错误码
                    String resultCode = aliPayResult.getResultCode();
                })
                .setUserCancel(() -> {

                })
                .payV2(this, "从服务器获取最终签名后的支付信息");
```
2.  登录
```java
        AliAuthUtil.newInstance()
                .setSucceed(aliAuthResult -> {
                    aliAuthResult.getAuthCode();
                    aliAuthResult.getAlipayOpenId();
                })
                .setUserCancel(() -> {

                })
                .setFail(aliAuthResult -> {
                    //支付宝登录认证的ResultStatus
                    String resultStatus = aliAuthResult.getResultStatus();
                    //支付宝登录认证的ResultCode
                    String resultCode = aliAuthResult.getResultCode();
                })
                .authV2(this, "从服务器获取最终签名后的登录信息");
```

### 四、状态码说明，枚举类

1.  登录认证的ResultStatus **枚举类：AliAuthResultStatus**， 参考文档：https://opendocs.alipay.com/open/218/105327
```java
    FAIL("4000", "系统异常"),
    USER_CANCELLED("6001", "用户中途取消"),
    NETWORK_ERROR("6002", "网络连接出错"),
    SUCCEED("9000", "请求处理成功"),

    DEFAULT("-100000", "其他错误");
```
2.  登录认证的ResultCode **枚举类：AliAuthResultCode**， 参考文档：https://opendocs.alipay.com/open/218/105327
```java
    SUCCEED("200", "业务处理成功，会返回 authCode"),
    SYSTEM_ANOMALY("202", "系统异常，请稍后再试或联系支付宝技术支持"),
    ACCOUNT_FROZEN("1005", "账户已冻结，如有疑问，请联系支付宝技术支持"),

    DEFAULT("-100000", "其他错误");
```
3.  支付宝支付返回的状态码对应状态描述 **枚举类：AliPayResultStatus**， 参考文档：https://opendocs.alipay.com/open/204/105301
```java
    FAIL("4000", "订单支付失败"),
    REPEATED_REQUESTS("5000", "重复请求"),
    USER_CANCELLED("6001", "用户中途取消"),
    NETWORK_ERROR("6002", "网络连接出错"),
    PAY_RESULT_UNKNOWN("6004", "支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态"),
    PROCESSING("8000", "正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态"),
    SUCCEED("9000", "订单支付成功"),

    DEFAULT("-100000", "其他错误");
```
4.  支付宝公共错误码 **枚举类：AliPublicCodeErr**， 参考文档：https://opendocs.alipay.com/open/common/105806
```java
    SUCCEED("10000", "接口调用成功，调用结果请参考具体的 API 文档所对应的业务返回参数。"),
    SERVICE_UNAVAILABLE("20000", "服务不可用"),
    PERMISSION_DENIED_20001("20001", "授权权限不足"),
    MISSING_PARAMETERS("40001", "缺少必选参数"),
    INVALID_PARAMETER("40002", "非法的参数"),
    SERVICE_PROCESSING_FAILURE("40004", "业务处理失败，对应业务错误码，明细错误码和解决方案请参见具体的 API 接口文档。"),
    FREQUENCY_TRANSFINITE("40005", "调用频次超限"),
    PERMISSION_DENIED_40006("40006", "权限不足"),

    DEFAULT("-100000", "其他错误");
```

License
-------

    Copyright 2021 mjsoftking

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

