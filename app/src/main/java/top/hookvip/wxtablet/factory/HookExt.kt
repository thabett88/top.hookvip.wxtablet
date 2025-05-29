package top.hookvip.wxtablet.factory

import com.highcapable.yukihookapi.hook.factory.toClass
import org.luckypray.dexkit.wrap.DexMethod
import top.hookvip.wxtablet.data.HostInfo

fun String.toAppClass(loader: ClassLoader = HostInfo.appClassLoader) = toClass(loader)

fun String.toDexMethod(loader: ClassLoader = HostInfo.appClassLoader) = DexMethod(this).getMethodInstance(loader)