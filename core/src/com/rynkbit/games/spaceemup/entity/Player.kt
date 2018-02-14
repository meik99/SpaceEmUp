package com.rynkbit.games.spaceemup.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.rynkbit.games.spaceemup.animation.Explosion
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.laser.Laser
import com.rynkbit.games.spaceemup.entity.laser.PlayerLaser
import com.rynkbit.games.spaceemup.entity.laser.fire.*
import java.util.*

class Player() : ShootableEntity(Texture("playerShip1_green.png"), Explosion()) {
    private val laser: MutableList<Laser>
    private val laserSound: Sound
    var mute: Boolean = false
    var laserGenerator: LaserGenerator = MemoryStorage.laserGenerator

    private var date: Date = Date()

    init {
        y = -1.toFloat()
        lives = 3
        laser = mutableListOf()
        rotateBy(270.toFloat())
        laserSound = Gdx.audio.newSound(Gdx.files.internal("Sound/sfx_laser1.ogg"))
        sprite.texture = MemoryStorage.selectedSkin.sprite.texture
    }

    override fun act(delta: Float) {
        super.act(delta)

        if(y <= 0){
            y = (stage.camera.viewportHeight / 3)
        }

        if(Date().time - date.time >= laserGenerator.timeToShoot && alive == true){
            val lasers = laserGenerator.generate(this)
            laser.addAll(lasers)

            for(laser in lasers){
                stage.addActor(laser)
            }

            date = Date()

            if(mute == false)
                laserSound.play(0.2.toFloat())
        }

        laser.removeAll { element ->
            if(element.x > stage.camera.viewportWidth){
                stage.actors.removeAll({ it.equals(element) })
                true
            }else false
        }

    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }
}