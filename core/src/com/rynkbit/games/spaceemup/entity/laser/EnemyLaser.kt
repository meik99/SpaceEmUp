package com.rynkbit.games.spaceemup.entity.laser

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.Player

/**
 * Created by michael on 14.01.18.
 */
class EnemyLaser: Laser{
    companion object {
        val looseLifeSound: Sound = Gdx.audio.newSound(Gdx.files.internal("Sound/sfx_shieldDown.ogg"))
    }

    constructor() :super(laserRed01){
        rotateBy(90.toFloat())
        acceleration = 20.toDouble()
    }

    override fun act(delta: Float) {
        super.act(delta)

        x -= (acceleration * time).toFloat()

        for (actor in stage.actors){
            if(actor is Player){
                val player = actor as Player

                if(player.boundingRectangle.overlaps(boundingRectangle)){
                    player.lives--
                    looseLifeSound.play(1.0f)
                    stage.actors.removeValue(this, true)
                }
            }
        }
    }
}