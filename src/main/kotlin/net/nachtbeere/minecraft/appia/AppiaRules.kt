package net.nachtbeere.minecraft.appia

import org.bukkit.block.BlockFace
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent

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

    fun tnr() {
        if (this.isCreeper()) {
            this.clearExplodeList()
        }
    }
}

class ExplodeProof(event: HangingBreakByEntityEvent) {
    private val currentEvent: HangingBreakByEntityEvent = event

    private fun isCreeper(): Boolean {
        return if (this.currentEvent.remover != null) {
            this.currentEvent.remover!!.type == EntityType.CREEPER
        } else {
            false
        }
    }

    private fun isHanging(): Boolean {
        return this.currentEvent.entity.type == EntityType.PAINTING ||
                this.currentEvent.entity.type == EntityType.ITEM_FRAME
    }

    fun proof() {
        if (this.isCreeper()) {
            if (this.isHanging()) {
                currentEvent.isCancelled = true
            }
        }
    }
}

