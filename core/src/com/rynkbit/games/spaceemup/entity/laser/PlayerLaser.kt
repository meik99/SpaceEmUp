package com.rynkbit.games.spaceemup.entity.laser

import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.Enemy

/**
 * Created by michael on 14.01.18.
 */
class PlayerLaser: Laser{
    constructor(): super(laserBlue01){
        rotateBy(270.toFloat())
    }

    override fun act(delta: Float) {
        super.act(delta)

        x += (acceleration * time).toFloat()

        val actors = stage.actors

        for (actor in actors){
            if(actor is Enemy){
                val enemy = actor as Enemy

                if(boundingRectangle.overlaps(enemy.boundingRectangle)){
                    enemy.lives--
                    stage.actors.removeValue(this, true)

                    if(enemy.alive == false){
                        MemoryStorage.instance.money += enemy.moneyValue
                    }
                }
            }
        }
    }
}