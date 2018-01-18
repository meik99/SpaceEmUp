package com.rynkbit.games.spaceemup

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import java.lang.IllegalStateException

/**
 * Created by michael on 14.01.18.
 */
class Explosion{
    companion object {
        val FRAME_COLS = 8
        val FRAME_ROWS = 8
        val TEXTURE = Texture("Effects/explosion.png")
    }

    val animation: Animation<TextureRegion>
    var stateTime: Double

    init {
        val tmpTextureRegion = TextureRegion.split(
                TEXTURE,
                TEXTURE.width / FRAME_COLS,
                TEXTURE.height / FRAME_ROWS
        )

        val frames: Array<TextureRegion> = Array(FRAME_COLS* FRAME_ROWS)

        for(row in 0..(FRAME_ROWS-1)){
            for(col in 0..(FRAME_COLS-1)){
                frames.add(tmpTextureRegion.get(row).get(col))
            }
        }

        animation = Animation<TextureRegion>(1/96.toFloat(), frames)
        stateTime = 0.toDouble()
    }

    fun render(batch: Batch, x: Float, y:Float,
               width: Float = TEXTURE.width.toFloat(),
               height: Float = TEXTURE.height.toFloat()){
        stateTime += Gdx.graphics.deltaTime

        val currentFrame = animation.getKeyFrame(stateTime.toFloat(), false)

        batch.draw(currentFrame, x, y, width, height)
    }

    fun reset() {
        stateTime = 0.toDouble()
    }
}