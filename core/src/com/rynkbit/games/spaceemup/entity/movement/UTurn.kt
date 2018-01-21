package com.rynkbit.games.spaceemup.entity.movement

import com.badlogic.gdx.Gdx
import com.rynkbit.games.spaceemup.entity.SpriteActor

/**
 * Created by michael on 15.01.18.
 */
class UTurn: Movement{
    private val accaleration: Double = 10.toDouble()
    private val radius: Double = 200.toDouble()

    private var time: Double = 1.toDouble()
    private var x0: Double = 0.toDouble()
    private var prefix: Int = 1

    private var doCircle: Boolean = false
    private var reverse: Boolean = false

    override fun move(spriteActor: SpriteActor, delta: Float) {
        time += delta

        if(reverse == false && doCircle == false){
            spriteActor.x -= (accaleration * time).toFloat()
        }else if(reverse == true && doCircle == false){
            spriteActor.x += (accaleration * time).toFloat()
        }else if(doCircle == true){
            x0 += ((accaleration * time))

            if(x0 < radius)
                spriteActor.x -= ((accaleration / 2 * time)).toFloat()
            else
                spriteActor.x += ((accaleration / 2 * time)).toFloat()

            if(spriteActor.x > spriteActor.stage.camera.viewportWidth / 2){
                doCircle = false
                reverse = true
            }


            spriteActor.y -= ((accaleration * time)).toFloat() * prefix
        }

        if(reverse == false && doCircle == false && spriteActor.x <= spriteActor.stage.camera.viewportWidth / 2){
            doCircle = true
            if(spriteActor.y < spriteActor.stage.camera.viewportHeight / 2){
                prefix = -1
            }
        }

    }

}