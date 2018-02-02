package com.rynkbit.games.spaceemup.data

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

object FontMap{
    val generator: FreeTypeFontGenerator =
            FreeTypeFontGenerator(
                    Gdx.files.internal("kenvector_future.ttf")
            )
    val parameter: FreeTypeFontGenerator.FreeTypeFontParameter =
            FreeTypeFontGenerator.FreeTypeFontParameter()

    val blackTextFont: BitmapFont
    val whiteTextFont: BitmapFont
    val whiteHeadlineFont: BitmapFont

    init {
        parameter.size = 60
        parameter.color = Color.WHITE

        whiteTextFont = generator.generateFont(parameter)

        parameter.color = Color.BLACK

        blackTextFont = generator.generateFont(parameter)

        parameter.color = Color.WHITE
        parameter.size = 120

        whiteHeadlineFont = generator.generateFont(parameter)

        generator.dispose()
    }
}