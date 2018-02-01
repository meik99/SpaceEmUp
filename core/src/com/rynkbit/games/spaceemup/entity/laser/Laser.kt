package com.rynkbit.games.spaceemup.entity.laser

import com.badlogic.gdx.graphics.Texture
import com.rynkbit.games.spaceemup.Disposable
import com.rynkbit.games.spaceemup.entity.SpriteActor

abstract class Laser(texture: Texture) : SpriteActor(texture), Disposable {
    companion object {
        val laserBlue01 = Texture("Lasers/laserBlue01.png")
        val laserGreen10 = Texture("Lasers/laserGreen10.png")
        val laserRed01 = Texture("Lasers/laserRed01.png")
    }

    override var disposable: Boolean = false

    var acceleration: Double = 100.toDouble()

    var speed: Double
    var time: Double

    init {
        speed = 100.toDouble()
        time = 0.5
        scaleX = 2.toFloat()
        scaleY = 2.toFloat()
    }

    override fun act(delta: Float) {
        super.act(delta)

        time += delta
        speed = acceleration * time
    }

}
