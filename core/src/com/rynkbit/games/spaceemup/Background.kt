package com.rynkbit.games.spaceemup

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * Created by michael on 12.01.18.
 */
class Background{
    private val texture: Texture = Texture("Backgrounds/darkPurple.png")
    private val tilesX = Gdx.graphics.width / texture.width + 1
    private val tilesY = Gdx.graphics.height / texture.height + 1

    fun draw(spriteBatch: SpriteBatch){
        for (x in 0..tilesX){
            for (y in 0..tilesY){
                spriteBatch.draw(
                        texture,
                        x * texture.width.toFloat(),
                        y * texture.height.toFloat()
                )
            }
        }
    }
}