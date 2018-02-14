package com.rynkbit.games.spaceemup.entity.laser.fire

import com.rynkbit.games.spaceemup.entity.SpriteActor
import com.rynkbit.games.spaceemup.entity.laser.Laser

val DEFAULT_TIME_TO_SHOOT = 300

interface LaserGenerator{
    fun generate(actor: SpriteActor): Array<Laser>
    val timeToShoot: Int
}