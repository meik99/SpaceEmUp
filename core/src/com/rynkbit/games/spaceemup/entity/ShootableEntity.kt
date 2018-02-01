package com.rynkbit.games.spaceemup.entity

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.rynkbit.games.spaceemup.Disposable
import com.rynkbit.games.spaceemup.animation.Animation2D

open class ShootableEntity(texture: Texture, private val deathAnimation: Animation2D): SpriteActor(texture), Disposable {
    private val shotTimer:Float = 0.1.toFloat()

    private var isShot: Boolean = false
    private var shotTime: Float = 0.toFloat()

    override var disposable: Boolean = false

    var lives: Int = 0
        protected set
    var alive: Boolean = false
        get() = lives > 0

    override fun act(delta: Float) {
        super.act(delta)

        if(isShot == true && shotTime >= shotTimer){
            isShot = false
            shotTime = 0.toFloat()
            color = Color.WHITE
        }
        else if(isShot == true)
            shotTime += delta

        if(disposable == false && alive == false && deathAnimation.animation.isAnimationFinished(deathAnimation.stateTime.toFloat())){
            disposable = true
        }
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        if(alive == true)
            super.draw(batch, parentAlpha)
        else if(batch != null) {
            if(disposable == false && alive == false){
                deathAnimation.render(batch, x, y, width, height)
            }
        }
    }

    fun shot(){
        isShot = true
        lives--
        color = Color.RED
    }

}