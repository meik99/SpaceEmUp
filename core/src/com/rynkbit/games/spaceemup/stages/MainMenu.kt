package com.rynkbit.games.spaceemup.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.rynkbit.games.spaceemup.Game
import com.rynkbit.games.spaceemup.GameParams
import com.rynkbit.games.spaceemup.data.FontMap
import com.rynkbit.games.spaceemup.data.MemoryStorage

/**
 * Created by michael on 15.01.18.
 */
class MainMenu(val game: Game) : Stage(StretchViewport(
        GameParams.VIEWPORT_WIDTH, GameParams.VIEWPORT_HEIGHT)) {

//    val generator: FreeTypeFontGenerator
//    val parameter: FreeTypeFontGenerator.FreeTypeFontParameter
    val headerFont: BitmapFont
    val blackTextFont: BitmapFont
    val whiteTextFont: BitmapFont

    val buttonBlueTexture: Texture
    val buttonGreenTexture: Texture
    val buttonYellowTexture: Texture
    val buttonRedTexture: Texture

    val buttonBlue: Sprite
    val buttonRed: Sprite

    val titleText: GlyphLayout

    init {
        Gdx.input.inputProcessor = this

        buttonBlueTexture = Texture("UI/buttonBlue.png")
        buttonGreenTexture = Texture("UI/buttonGreen.png")
        buttonYellowTexture = Texture("UI/buttonYellow.png")
        buttonRedTexture = Texture("UI/buttonRed.png")

        buttonBlue = Sprite(buttonBlueTexture)
        buttonRed = Sprite(buttonRedTexture)

        buttonBlue.x = 250.toFloat()
        buttonBlue.y = camera.viewportHeight - 300.toFloat() - buttonBlueTexture.height * 3.toFloat()
        buttonBlue.scale(2.toFloat())

        buttonRed.x = 250.toFloat()
        buttonRed.y =
                camera.viewportHeight -
                        300.toFloat() -
                        buttonBlueTexture.height * 3.toFloat() -
                        buttonBlue.height * buttonBlue.scaleY -
                        20
        buttonRed.scale(2.toFloat())



//        generator = FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"))
//        parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
//
//        parameter.size = 120

        headerFont = FontMap.whiteHeadlineFont

//        parameter.size = 60
//        parameter.color = Color.BLACK

        blackTextFont = FontMap.blackTextFont
//        parameter.color = Color.WHITE

        whiteTextFont = FontMap.whiteTextFont

//        generator.dispose()

        titleText = GlyphLayout(headerFont, "Space Em Up!")
    }



    override fun act(delta: Float) {
        super.act(delta)
    }

    override fun draw() {
        super.draw()

        if(batch != null){
            batch.begin()

            buttonBlue.draw(batch)
            buttonRed.draw(batch)

            headerFont.draw(
                    batch,
                    titleText,
                    (viewport.camera.viewportWidth).toFloat() - titleText.width,
                    (viewport.camera.viewportHeight - titleText.height).toFloat()
            )

            blackTextFont.draw(
                    batch,
                    "Start Game",
                    buttonBlue.x - buttonBlue.width / 2,
                    buttonBlue.y + buttonBlue.height * buttonBlue.scaleY / 4
            )

            blackTextFont.draw(
                    batch,
                    "Skins",
                    buttonRed.x - buttonRed.width / 2,
                    buttonRed.y + buttonRed.height * buttonRed.scaleY / 4
            )

            whiteTextFont.draw(
                    batch,
                    "Money: ${MemoryStorage.money}",
                    buttonBlue.x - buttonBlue.width,
                    60.toFloat()
            )

            batch.end()
        }
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val pointX = camera.viewportWidth / Gdx.graphics.width * screenX
        val pointY = camera.viewportHeight / Gdx.graphics.height * (Gdx.graphics.height - screenY)

        if(buttonBlue.boundingRectangle.contains(pointX, pointY)){
            game.setStage(GameStage(game))
        }

        else if(buttonRed.boundingRectangle.contains(pointX, pointY)){
            game.setStage(SkinStage(game))
        }

        return super.touchDown(screenX, screenY, pointer, button)
    }
}