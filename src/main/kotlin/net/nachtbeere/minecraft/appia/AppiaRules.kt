package net.nachtbeere.minecraft.appia

import org.bukkit.block.BlockFace
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityExplodeEvent

class Modernizer(event: CreatureSpawnEvent) {
    private val currentEvent: CreatureSpawnEvent = event

    private fun isAutogenesis(): Boolean {
        return this.currentEvent.spawnReason == CreatureSpawnEvent.SpawnReason.NATURAL
    }

    private fun isHarmful(): Boolean {
        return Constants.AGGRESSIVES.contains(this.currentEvent.entityType)
    }

    private fun isOnArtificialThings(): Boolean {
        return Constants.ARTIFICIALS.contains(this.currentEvent.location.block.getRelative(BlockFace.DOWN).type)
    }

    private fun kill() {
        this.currentEvent.isCancelled = true
    }

    fun modernize() {
        if (this.isAutogenesis() &&
            this.isHarmful() &&
            this.isOnArtificialThings()) {
            this.kill()
        }
    }
}

class CreeperTNR(event: EntityExplodeEvent) {
    private val currentEvent: EntityExplodeEvent = event

    private fun isCreeper(): Boolean {
        return this.currentEvent.entityType == EntityType.CREEPER
    }

    private fun clearExplodeList() {
        this.currentEvent.blockList().clear()
    }

    fun tnr(){
        if (this.isCreeper()) {
            this.clearExplodeList()
        }
    }
}

