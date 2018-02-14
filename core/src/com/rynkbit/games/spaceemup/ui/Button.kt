package com.rynkbit.games.spaceemup.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.rynkbit.games.spaceemup.data.FontMap
import com.rynkbit.games.spaceemup.entity.SpriteActor

class Button(texture: Texture): SpriteActor(texture){
    companion object {
        val buttonBlueTexture = Texture("UI/buttonBlue.png")
        val buttonGreenTexture = Texture("UI/buttonGreen.png")
        val buttonYellowTexture = Texture("UI/buttonYellow.png")
        val buttonRedTexture = Texture("UI/buttonRed.png")
    }

    var text: String = ""
        set(value) {
            glyphLayout = GlyphLayout(font, value)
            field = value
        }

    var textOffset: Int = 20

    private var font: BitmapFont
    private var glyphLayout: GlyphLayout = GlyphLayout()

    init {
        font = FontMap.blackTextFont
    }



    override fun draw(batch: Batch?, parentAlpha: Float) {
        if(batch != null){
            super.draw(batch, parentAlpha)

            font.draw(
                    batch,
                    glyphLayout,
                    x + textOffset,
                    y + height * scaleY / 2 - glyphLayout.height / 2 + 10
            )
        }
    }
}