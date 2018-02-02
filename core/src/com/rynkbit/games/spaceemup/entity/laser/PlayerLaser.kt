package com.rynkbit.games.spaceemup.entity.laser

import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.Enemy

class PlayerLaser() : Laser(laserBlue01) {

    init {
        rotateBy(270.toFloat())
    }

    override fun act(delta: Float) {
        super.act(delta)

        x += (acceleration * time).toFloat()

        val actors = stage.actors

        for (actor in actors){
            if(actor is Enemy){
                if(boundingRectangle.overlaps(actor.boundingRectangle)){
                    actor.shot()
                    disposable = true

                    if(actor.alive == false){
                        MemoryStorage.money += actor.moneyValue
                    }
                }
            }
        }
    }
}