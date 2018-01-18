package com.rynkbit.games.spaceemup.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.rynkbit.games.spaceemup.Explosion
import com.rynkbit.games.spaceemup.entity.laser.EnemyLaser
import com.rynkbit.games.spaceemup.entity.laser.Laser
import com.rynkbit.games.spaceemup.entity.movement.Movement
import com.rynkbit.games.spaceemup.entity.movement.Straight
import java.util.*

/**
 * Created by michael on 13.01.18.
 */
open class Enemy(texture: String = "Enemies/enemyRed1.png") : SpriteActor(Texture(texture)) {
    val laser: MutableList<Laser> = mutableListOf()
    val laserSound: Sound
    val explosion: Explosion

    var moneyValue: Int

    var date: Date = Date()
    var alive: Boolean
        get() = lives > 0
        private set

    var lives: Int
    var time: Double
    var disposeable: Boolean

    var movement: Movement

    init {
        rotateBy(270.toFloat())
        alive = true
        disposeable = false
        time = 0.toDouble()
        explosion = Explosion()
        lives = 2
        movement = Straight()
        moneyValue = 10

        laserSound = Gdx.audio.newSound(Gdx.files.internal("Sound/sfx_laser2.ogg"))
    }

    override fun act(delta: Float) {
        super.act(delta)
        val dateNow = Date()

        time += delta

        if(dateNow.time - date.time >= 800 && alive == true){
            val shot = EnemyLaser()

            shot.x = x
            shot.y = y + height / 3
            shot.time = time

            laser.add(shot)
            stage.addActor(shot)

            date = Date()

            laserSound.play(1.toFloat())
        }

        laser.removeAll { element ->
            if(element.x > stage.camera.viewportWidth){
                stage.actors.removeAll({ it.equals(element) })
                true
            }else false
        }

        if(alive == true)
            movement.move(this, delta)

        if((!alive && explosion.animation.isAnimationFinished(
                explosion.stateTime.toFloat()
        )) || x > stage.camera.viewportWidth || x <= 0){
            disposeable = true
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if(alive == true)
            super.draw(batch, parentAlpha)
        else{
            if(disposeable == false) {
                if(batch != null)
                    explosion.render(batch, x, y, width, height)
            }
        }

    }
}