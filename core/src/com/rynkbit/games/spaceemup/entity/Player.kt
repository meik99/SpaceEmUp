package com.rynkbit.games.spaceemup.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.rynkbit.games.spaceemup.animation.Explosion
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.laser.Laser
import com.rynkbit.games.spaceemup.entity.laser.PlayerLaser
import java.util.*

class Player() : ShootableEntity(Texture("playerShip1_green.png"), Explosion()) {
    private val laser: MutableList<Laser>
    private val laserSound: Sound

    private var date: Date = Date()

    init {
        y = -1.toFloat()
        lives = 3
        laser = mutableListOf()
        rotateBy(270.toFloat())
        laserSound = Gdx.audio.newSound(Gdx.files.internal("Sound/sfx_laser1.ogg"))
        sprite.texture = MemoryStorage.instance.selectedSkin.sprite.texture
    }

    override fun act(delta: Float) {
        super.act(delta)

        if(y <= 0){
            y = (stage.camera.viewportHeight / 3)
        }

        if(Date().time - date.time >= 300 && alive == true){
            val laserUp = PlayerLaser()
            val laserDown = PlayerLaser()

            laserUp.x = x + width
            laserDown.x = x + width

            laserUp.y = y - 40
            laserDown.y = y + height - 20

            laser.addAll(arrayOf(laserDown, laserUp))

            stage.addActor(laserUp)
            stage.addActor(laserDown)

            date = Date()

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