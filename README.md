# Android 开源UI库(Android Benchmark UI) -- 已停更

Android开源版基础UI库

## 版本说明
### 最新版本

[![](https://www.jitpack.io/v/ZealP/Android_BenchmarkUI.svg)](https://github.com/ZealP/Android_BenchmarkUI)

<!-- ### Demo下载
[![downloads](https://img.shields.io/badge/downloads-430k-blue.svg)](https://github.com/zhou-you/RxEasyHttp/blob/master/RxEasyHttp-Demo.apk?raw=true) -->

### 添加Gradle依赖

1. 先在项目根目录的 build.gradle 的 repositories 添加:

```gradle
allprojects {
        repositories {
            ...
            maven { url 'https://www.jitpack.io' }
        }
    }
```

2. 然后在dependencies添加:

```gradle
dependencies {
    implementation 'com.github.ZealP:Android_BenchmarkUI:最新版本号'
}
```
### 全局配置

一般在 Aplication，或者基类中，只需要调用一次即可。
初始化需要Application#onCreate()中初始化，记得在manifest.xml中注册Application。

Application:

```java
public class App extends Application {
    public static App application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        /**
         * 初始化UI组件
         * @param application Application对象
         */
        BenchmarkUI.init(application);
    }
}
```

## 调用实例

功能调用可参照demo代码

>具体的调用文档正在加紧撰写中,敬请期待

### 声明
本开源项目部分代码参考多个热门第三方开源项目，特别感谢相关作者的开源分享。

