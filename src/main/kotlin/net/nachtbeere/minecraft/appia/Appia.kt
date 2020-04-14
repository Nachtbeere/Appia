package net.nachtbeere.minecraft.appia

import java.util.logging.Logger
import org.bukkit.plugin.java.JavaPlugin

class Appia : JavaPlugin() {
    val consolLogger = Logger.getLogger("Minecraft")
    var listener: AppiaListener? = null

    override fun onEnable() {
        listener = AppiaListener(this)
    }

    override fun onDisable() {
        listener = null
    }

    fun log(msg: String) {
        consolLogger.info("[${description.name}] $msg")
    }
}
