package com.rynkbit.games.spaceemup.entity.laser

import com.badlogic.gdx.graphics.Texture
import com.rynkbit.games.spaceemup.entity.SpriteActor

open abstract class Laser : SpriteActor {
    companion object {
        val laserBlue01 = Texture("Lasers/laserBlue01.png")
        val laserGreen10 = Texture("Lasers/laserGreen10.png")
        val laserRed01 = Texture("Lasers/laserRed01.png")
    }

    var acceleration: Double = 100.toDouble()

    var speed: Double
    var time: Double

    constructor(texture: Texture): super(texture){
        speed = 100.toDouble()
        time = 0.toDouble()
    }


    override fun act(delta: Float) {
        super.act(delta)

        time += delta
        speed = acceleration * time
    }

}
