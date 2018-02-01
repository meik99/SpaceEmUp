package com.rynkbit.games.spaceemup.entity.laser

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.Player

class EnemyLaser() : Laser(laserRed01) {
    companion object {
        val looseLifeSound: Sound = Gdx.audio.newSound(Gdx.files.internal("Sound/sfx_shieldDown.ogg"))
    }

    init {
        rotateBy(90.toFloat())
        acceleration = 20.toDouble()
    }

    override fun act(delta: Float) {
        super.act(delta)

        x -= (acceleration * time).toFloat()

        for (actor in stage.actors){
            if(actor is Player){
                if(actor.boundingRectangle.overlaps(boundingRectangle)){
                    actor.shot()
                    disposable = true
                    looseLifeSound.play(1.0f)
                }
            }
        }
    }
}