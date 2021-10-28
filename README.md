# AlipayLibrary

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![](https://jitpack.io/v/com.gitee.osard/alipaylibrary.svg)](https://jitpack.io/#com.gitee.osard/alipaylibrary)

### 一、项目介绍
1. APP 使用示例项目，libs下含有以编译最新的aar资源。
2. AlipayLibrary arr资源项目，需要引入的资源包项目。
3. jitpack 仓库在线引入即可
4. 支付宝sdk单独引入
5. 项目内支付宝sdk版本为 **alipaysdk-15.8.05.211018174351**

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
    implementation 'com.gitee.osard:alipaylibrary:1.0.0'
    //引入alipaylibrary.aar的依赖资源，以下2个
    implementation fileTree(dir: 'libs', include: ['alipaysdk.aar'])
    implementation 'com.alibaba:fastjson:1.2.78'
}
```

### 三、使用说明

1.  支付
```
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
```
        AliAuthUtil.newInstance()
                .setSucceed(aliAuthResult -> {

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

