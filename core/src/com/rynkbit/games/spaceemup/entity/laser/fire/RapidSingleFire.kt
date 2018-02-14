package com.rynkbit.games.spaceemup.entity.laser.fire

class RapidSingleFire: SingleFire() {
    override val timeToShoot: Int
        get() = DEFAULT_TIME_TO_SHOOT / 3
}