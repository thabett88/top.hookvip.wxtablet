<div align="center">
<h1>WeChatTablet</h1>

<a href="https://github.com/Xposed-Modules-Repo/top.hookvip.wxtablet/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/Xposed-Modules-Repo/top.hookvip.wxtablet?label=stars"></a>
<a href="https://github.com/Xposed-Modules-Repo/top.hookvip.wxtablet/releases"><img alt="GitHub all releases" src="https://img.shields.io/github/downloads/Xposed-Modules-Repo/top.hookvip.wxtablet/total?label=Downloads"></a>
<a href="https://github.com/Xposed-Modules-Repo/top.hookvip.wxtablet/releases/latest"><img alt="GitHub lastest release" src="https://img.shields.io/github/v/release/Xposed-Modules-Repo/top.hookvip.wxtablet"></a>

<a href="https://t.me/HookVipCl"><img alt="Telegram Channel" src="https://img.shields.io/badge/Telegram-频道-blue.svg?logo=telegram"></a>
<a href="https://t.me/HookVipChat"><img alt="Telegram Channel" src="https://img.shields.io/badge/Telegram-群组-blue.svg?logo=telegram"></a>

<p>用于开启微信平板模式的Xposed模块</p>

</div>  

## 模块功能
+ ~禁用 **TencentTinker** 热补丁~(1.0.1版本之后废弃)
+ 强制开启微信平板模式
+ 强制显示平板登录按钮(高版本)

## 适配范围
+ 理论支持`8.0.40`以上的**所有版本**

## 测试版本
- `8.0.40`  `8.0.45` `8.0.50` `8.0.53` `8.0.56` `8.0.58`

## 模块特色
1. 自动搜索各微信版本的方法配置
2. 使用本地持久化存储配置文件(各版本配置隔离)
3. 每次重启微信 如果存在配置 则使用缓存的方法 不再每次都调用Dexkit查找配置(减少启动页卡顿时长)
4. 每更新/降级微信 模块会自动判断是否存在缓存 否则 将会重新加载Dexkit并搜索当前版本的方法配置并存储本地缓存
5. 后续随意升/降级微信 如果存在缓存 不会再次查找配置(耗时操作)

## 致谢

| Name        | Link                                                      |
|-------------|-----------------------------------------------------------|
| YukiHookAPI | [YukiHookAPI](https://github.com/HighCapable/YukiHookAPI) |
| DexKit      | [DexKit](https://github.com/LuckyPray/DexKit)             |
| FastKV      | [FastKV](https://github.com/BillyWei01/FastKV)            |
