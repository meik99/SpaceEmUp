package com.rynkbit.games.spaceemup.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor

/**
 * Created by michael on 12.01.18.
 */
open class SpriteActor: Actor {

    private val texture: Texture

    val sprite: Sprite

    open val boundingRectangle: Rectangle
        get() = Rectangle(x, y, width, height)


    constructor(texture: Texture):
            super(){
        this.texture = texture
        this.sprite = Sprite(texture)

        x = 0.toFloat()
        y = 0.toFloat()
        width = sprite.width
        height = sprite.height
        originX = sprite.originX
        originY = sprite.originY
        scaleX = sprite.scaleX
        scaleY = sprite.scaleY
        rotation = sprite.rotation
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        if(batch != null) {
            batch.draw(
                    sprite.texture,
                    x,
                    y,
                    originX,
                    originY,
                    width,
                    height,
                    scaleX,
                    scaleY,
                    rotation,
                    sprite.regionX,
                    sprite.regionY,
                    sprite.regionWidth,
                    sprite.regionHeight,
                    sprite.isFlipX,
                    sprite.isFlipY
            )
        }
    }
}