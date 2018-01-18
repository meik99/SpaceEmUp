package com.rynkbit.games.spaceemup.entity.movement

import com.rynkbit.games.spaceemup.entity.SpriteActor
/**
 * Created by michael on 15.01.18.
 */
class Straight: Movement{
    private val accaleration: Double = 10.toDouble()
    private var time: Double = 1.toDouble()

    override fun move(spriteActor: SpriteActor, delta:Float) {
        time += delta
        spriteActor.x -= (accaleration * time).toFloat()
    }

}