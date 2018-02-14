package com.rynkbit.games.spaceemup.entity.laser.fire

import com.rynkbit.games.spaceemup.entity.Player
import com.rynkbit.games.spaceemup.entity.SpriteActor
import com.rynkbit.games.spaceemup.entity.laser.EnemyLaser
import com.rynkbit.games.spaceemup.entity.laser.Laser
import com.rynkbit.games.spaceemup.entity.laser.PlayerLaser

class DoubleFire: LaserGenerator{
    override val timeToShoot: Int
        get() = DEFAULT_TIME_TO_SHOOT

    override fun generate(actor: SpriteActor): Array<Laser> {
        val generatedLasers = mutableListOf<Laser>()

        for(i in 0..1){
            val laser: Laser =
                    if(actor is Player) PlayerLaser()
                    else EnemyLaser()

            laser.x =
                    if(actor is Player)
                        actor.x + actor.width * actor.scaleX
                    else
                        actor.x - laser.width * actor.scaleX

            laser.y = actor.y + (actor.height * actor.scaleY) / 2 - (laser.width * laser.scaleX) / 2 + 3 - (40*i)

            generatedLasers.add(laser)
        }




        return generatedLasers.toTypedArray()
    }

}