package com.rynkbit.games.spaceemup.entity.movement

import com.badlogic.gdx.Gdx
import com.rynkbit.games.spaceemup.entity.SpriteActor

/**
 * Created by michael on 15.01.18.
 */
class ZickZack: Movement{
    private val accaleration: Double = 10.toDouble()
    private var time: Double = 1.toDouble()

    private var up: Boolean = true

    override fun move(spriteActor: SpriteActor, delta: Float) {
        time += delta

        val velocity = (accaleration * time) / 2

        spriteActor.x -= velocity.toFloat()

        if(up) spriteActor.y += velocity.toFloat()
        else spriteActor.y -= velocity.toFloat()

        if(spriteActor.y + spriteActor.height >= spriteActor.stage.camera.viewportHeight)
            up = false
        else if(spriteActor.y <= 0)
            up = true
    }

}