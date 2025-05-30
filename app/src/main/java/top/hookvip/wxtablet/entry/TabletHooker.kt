package top.hookvip.wxtablet.entry

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.core.view.isGone
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam
import top.hookvip.wxtablet.data.HostInfo
import top.hookvip.wxtablet.factory.toAppClass
import top.hookvip.wxtablet.utils.FastKv
import top.hookvip.wxtablet.utils.WXConfig

object TabletHooker : YukiBaseHooker() {

    override fun onHook() {
        if (isWeChat() && doHostInit(this)) {

            YLog.debug("start hook wechat tablet(${HostInfo.toVerStr()})")

            /*"com.tencent.tinker.loader.app.TinkerApplication".toAppClass()
                .constructor { paramCount(6) }
                .hook { before { args(0).set(7) } }*/

            /*"com.tencent.tinker.loader.shareutil.SharePatchFileUtil".toAppClassOrNull()
                ?.method {
                    name = "getPatchInfoFile"
                    param(String::class.java)
                }?.hook {
                    replaceAny {
                        val file = File(args(0).cast<String>(), "patch_meta.info")
                        when {
                            file.exists() -> file.delete()
                        }
                        file
                    }
                }*/

            "com.tencent.tinker.loader.TinkerLoader".toClass().method {
                name("tryLoad")
                param("com.tencent.tinker.loader.app.TinkerApplication".toClass())
            }.hook {
                after {
                    val tinkerApplication = args(0).cast<Any>()!!
                    val classloader = tinkerApplication.javaClass.method { name = "getClassLoader";superClass() }.get(tinkerApplication).invoke<ClassLoader>()!!
                    HostInfo.appClassLoader = classloader

                    YLog.warn("TinkerLoader.tryLoad -> classloader = $classloader")
                    YLog.warn("TinkerLoader.tryLoad -> instanceClassloader = ${instance.javaClass.classLoader}")

                    val intent = result<Intent>()!!
                    intent.extras?.let {
                        for (key in it.keySet()) {
                            YLog.warn("TinkerLoader.tryLoad -> ResultIntent($key = ${it.get(key)})")
                        }
                    }

                    WXConfig.apply {
                        checkPadTablet?.hook {
                            after {
                                result = !Throwable().stackTraceToString().contains("com.tencent.mm.pluginsdk.ui.chat.ChatFooter")
                            }
                        }
                        visibleLoginButton?.hook {
                            before {
                                args(0).cast<Button>()?.let { loginButton ->
                                    if (loginButton.isGone) {
                                        loginButton.visibility = View.VISIBLE
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun isWeChat(): Boolean {
        return "com.tencent.mm.app.Application".toClassOrNull(appClassLoader) != null
    }

    private fun doHostInit(packageParam: PackageParam): Boolean {
        FastKv.initialize("${appInfo.dataDir}/files/WeChatTablet/")
        HostInfo.apply {
            modulePath = packageParam.moduleAppFilePath
            appPackageName = packageParam.packageName
            appClassLoader = packageParam.appClassLoader!!
            appFilePath = packageParam.appInfo.sourceDir
            val buildConfigClass = "com.tencent.mm.boot.BuildConfig".toAppClass()
            isPlay = buildConfigClass.field { name = "BUILD_TAG" }.get().string().contains("_GP_")
            verName = buildConfigClass.field { name = "VERSION_NAME" }.get().string()
            verCode = buildConfigClass.field { name = "VERSION_CODE" }.get().int()
            clientVer = buildConfigClass.field { name = "CLIENT_VERSION_ARM64" }.get().string()
        }
        return true
    }
}