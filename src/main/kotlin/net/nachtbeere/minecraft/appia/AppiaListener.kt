package net.nachtbeere.minecraft.appia

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent

class AppiaListener(pluginInstance: Appia) : Listener {
    init {
        pluginInstance.server.pluginManager.registerEvents(this, pluginInstance)
    }
    val instance = pluginInstance

    @EventHandler(priority = EventPriority.NORMAL)
    fun onMobSpawn(event: CreatureSpawnEvent) {
        val modernizer = Modernizer(event)
        modernizer.modernize()
    }

    @EventHandler(priority = EventPriority.NORMAL)
    fun onCreeperExplosion(event: EntityExplodeEvent) {
        val creeperTnr = CreeperTNR(event)
        creeperTnr.tnr()
    }

    @EventHandler(priority = EventPriority.NORMAL)
    fun onCreeperExplosionWithHangingThing(event: HangingBreakByEntityEvent) {
        val explodeProof = ExplodeProof(event)
        explodeProof.proof()
    }
}