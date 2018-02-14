package com.rynkbit.games.spaceemup.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.rynkbit.games.spaceemup.animation.Explosion
import com.rynkbit.games.spaceemup.entity.laser.EnemyLaser
import com.rynkbit.games.spaceemup.entity.laser.Laser
import com.rynkbit.games.spaceemup.entity.laser.fire.DoubleFire
import com.rynkbit.games.spaceemup.entity.laser.fire.LaserGenerator
import com.rynkbit.games.spaceemup.entity.laser.fire.SingleFire
import com.rynkbit.games.spaceemup.entity.laser.fire.TripleFire
import com.rynkbit.games.spaceemup.entity.movement.Movement
import com.rynkbit.games.spaceemup.entity.movement.Straight
import java.util.*

open class Enemy(texture: String = "Enemies/enemyRed1.png") : ShootableEntity(Texture(texture), Explosion()) {
    val laser: MutableList<Laser> = mutableListOf()
    val laserSound: Sound
    val explosion: Explosion

    var moneyValue: Int
    var date: Date = Date()
    var time: Double
    var movement: Movement
    var laserGenerator: LaserGenerator = SingleFire()

    init {
        alive = true
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

        if(rotation == 0.toFloat()){
            rotateBy(270.toFloat())
        }

        time += delta

        if(dateNow.time - date.time >= 800 && alive == true){

            val shots = laserGenerator.generate(this)
            laser.addAll(shots)

            for(shot in shots){
                shot.time += time
                stage.addActor(shot)
            }

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
            disposable = true
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }
}