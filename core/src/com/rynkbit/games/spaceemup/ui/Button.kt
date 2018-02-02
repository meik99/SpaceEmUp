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
    var text: String = ""
        set(value) {
            glyphLayout.setText(font, value)
            field = value
        }

//    var parameterSize
//        set(value) {
//            parameter.size = value
//            font = generator.generateFont(parameter)
//            glyphLayout.setText(font, text)
//        }
//        get() = parameter.size
//
//    var parameterColor: Color
//        set(value) {
//            parameter.color = value
//            font = generator.generateFont(parameter)
//        }
//        get() = parameter.color

    var textOffset: Int = 20

    private var font: BitmapFont
    private var glyphLayout: GlyphLayout = GlyphLayout()

    private val parameter: FreeTypeFontGenerator.FreeTypeFontParameter =
            FreeTypeFontGenerator.FreeTypeFontParameter()

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
                    y + height * scaleY / 2 + glyphLayout.height / 2
            )
        }
    }
}