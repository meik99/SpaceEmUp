package com.rynkbit.games.spaceemup.entity.laser.fire

import com.rynkbit.games.spaceemup.entity.Player
import com.rynkbit.games.spaceemup.entity.SpriteActor
import com.rynkbit.games.spaceemup.entity.laser.EnemyLaser
import com.rynkbit.games.spaceemup.entity.laser.Laser
import com.rynkbit.games.spaceemup.entity.laser.PlayerLaser

class TripleFire: LaserGenerator{
    override val timeToShoot: Int
        get() = DEFAULT_TIME_TO_SHOOT

    override fun generate(actor: SpriteActor): Array<Laser> {
        val result = mutableListOf<Laser>()

        for (i in 0..2){
            val laser =
                    if(actor is Player)
                        PlayerLaser()
                    else
                        EnemyLaser()
            val offset = actor.height * actor.scaleY / 3 + laser.width * laser.scaleX

            laser.y = actor.y + offset * i - 25
            laser.x =
                    if(actor is Player)
                        actor.x + actor.width
                    else
                        actor.x
            result.add(laser)
        }

        return result.toTypedArray()
    }

}