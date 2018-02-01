package com.rynkbit.games.spaceemup.animation

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array


open class Animation2D(val texture: Texture, frameCols: Int, frameRows: Int){
    val animation: Animation<TextureRegion>
    var stateTime: Double

    init {
        val tmpTextureRegion = TextureRegion.split(
                texture,
                texture.width / frameCols,
                texture.height / frameRows
        )

        val frames: Array<TextureRegion> = Array(frameCols * frameRows)

        for(row in 0..(frameRows -1)){
            for(col in 0..(frameCols -1)){
                frames.add(tmpTextureRegion.get(row).get(col))
            }
        }

        animation = Animation<TextureRegion>(1/96.toFloat(), frames)
        stateTime = 0.toDouble()
    }

    fun render(batch: Batch, x: Float, y:Float,
               width: Float = texture.width.toFloat(),
               height: Float = texture.height.toFloat()){
        stateTime += Gdx.graphics.deltaTime

        val currentFrame = animation.getKeyFrame(stateTime.toFloat(), false)

        batch.draw(currentFrame, x, y, width, height)
    }

    fun reset() {
        stateTime = 0.toDouble()
    }
}