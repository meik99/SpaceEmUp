package com.rynkbit.games.spaceemup.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rynkbit.games.spaceemup.Explosion
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.laser.Laser
import com.rynkbit.games.spaceemup.entity.laser.PlayerLaser
import java.util.*

/**
 * Created by michael on 12.01.18.
 */
class Player: SpriteActor {
    private val laser: MutableList<Laser>
    private val laserSound: Sound

    private var date: Date = Date()

    val explosion: Explosion
    val alive: Boolean
        get() = lives > 0

    val disposeable: Boolean
        get() = alive == false && explosion.animation.isAnimationFinished(
                explosion.stateTime.toFloat())
    var lives: Int

    constructor(): super(Texture("playerShip1_green.png")){
        y = -1.toFloat()
        lives = 3
        laser = mutableListOf()
        explosion = Explosion()

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
        if(alive == true) {
            super.draw(batch, parentAlpha)
        }
        if(batch != null) {
            if(disposeable == false && alive == false)
                explosion.render(batch, x, y, width, height)
        }
    }

    fun reset() {
        x = 20.toFloat()
        y = (stage.camera.viewportHeight / 3).toFloat()
        explosion.reset()
        lives = 3
        laser.clear()
    }
}