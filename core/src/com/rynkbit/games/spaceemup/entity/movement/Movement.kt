package com.rynkbit.games.spaceemup.entity.movement

import com.rynkbit.games.spaceemup.entity.SpriteActor

/**
 * Created by michael on 15.01.18.
 */
interface Movement{
    fun move(spriteActor: SpriteActor, delta: Float)
}