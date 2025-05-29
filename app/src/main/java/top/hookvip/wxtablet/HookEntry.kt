package top.hookvip.wxtablet

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import top.hookvip.wxtablet.entry.TabletHooker

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "WeChatTablet"
            isEnable = true
        }
        isDebug = BuildConfig.DEBUG
    }

    override fun onHook() = encase {
        if (isFirstApplication) {
            loadHooker(TabletHooker)
        }
    }
}