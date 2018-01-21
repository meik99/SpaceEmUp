package com.rynkbit.games.spaceemup.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.rynkbit.games.spaceemup.Game
import com.rynkbit.games.spaceemup.GameParams
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.Player
import com.rynkbit.games.spaceemup.skin.SkinMap
import com.rynkbit.games.spaceemup.ui.Button
import com.rynkbit.games.spaceemup.ui.transform
import sun.applet.Main
import java.util.*

/**
 * Created by michael on 19.01.18.
 */
class SkinStage(val game: Game) : Stage(StretchViewport(GameParams.VIEWPORT_WIDTH, GameParams.VIEWPORT_HEIGHT)){
    private val skinBuyButtons: MutableList<Button> = mutableListOf()

    private var oldPointX: Float = 0.toFloat()
    private var oldPointY: Float = 0.toFloat()

    private val btnBack: Button

    private val generator: FreeTypeFontGenerator
    private val parameter: FreeTypeFontGenerator.FreeTypeFontParameter
    private val font: BitmapFont

    init {
        Gdx.input.inputProcessor = this

        generator = FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"))
        parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 60
        parameter.color = Color.WHITE
        font = generator.generateFont(parameter)

        for ((index, skin) in SkinMap.instance.skins.withIndex()){
            val button: Button = Button(Texture("UI/buttonBlue.png"))

            button.parameterSize  = 60
            button.parameterColor = Color.BLACK

            button.text =
                    if (skin.bought == true)
                        if(skin != MemoryStorage.instance.selectedSkin )
                            "Select"
                        else
                            "Selected"
                    else
                        String.format(Locale.ENGLISH, "%d", skin.value)
            button.y = camera.viewportHeight / 3
            button.width = button.sprite.width * 2.toFloat()
            button.height = button.sprite.height * 2.toFloat()
            button.x = 20 + 20*index + button.width * index

            if(skin.scaleX == 1.toFloat() && skin.scaleY == 1.toFloat())
                skin.scaleBy(1.5.toFloat())

            skin.x = button.x + 110
            skin.y = button.y +
                    button.height * button.scaleY +
                    skin.height / 2 * skin.scaleY

            skinBuyButtons.add(button)
            addActor(button)
            addActor(skin)
        }

        btnBack = Button(Texture("UI/buttonRed.png"))
        btnBack.text = "Back"
        btnBack.x = 20.toFloat()
        btnBack.y = 20.toFloat()
        btnBack.width = btnBack.sprite.width * 2
        btnBack.height = btnBack.sprite.height * 2
        btnBack.parameterSize  = 60
        btnBack.parameterColor = Color.BLACK

        addActor(btnBack)
    }

    override fun act(delta: Float) {
        for ((index, button) in skinBuyButtons.withIndex()){
            val skin = SkinMap.instance.skins[index]

            button.text =
                    if (skin.bought == true)
                        if(skin != MemoryStorage.instance.selectedSkin )
                            "Select"
                        else
                            "Selected"
                    else
                        String.format(Locale.ENGLISH, "%d", skin.value)
        }
        super.act(delta)
    }

    override fun draw() {
        super.draw()

        if(batch != null){
            batch.begin()
            font.draw(
                    batch,
                    "Money: ${MemoryStorage.instance.money}",
                    20.toFloat(),
                    camera.viewportHeight - 20.toFloat()
            )
            batch.end()
        }
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val (pointX, pointY) = transform(screenX, screenY, camera)

        for((index, button) in skinBuyButtons.withIndex()){
            if(button.boundingRectangle.contains(pointX, pointY)){
                if(MemoryStorage.instance.money >= SkinMap.instance.skins[index].value &&
                    SkinMap.instance.skins[index].bought == false) {

                    MemoryStorage.instance.money -= SkinMap.instance.skins[index].value
                    SkinMap.instance.skins[index].bought = true
                    MemoryStorage.instance.selectedSkin =
                            SkinMap.instance.skins[index]

//                    game.setStage(MainMenu(game))
                }
                else if(SkinMap.instance.skins[index].bought == true){
                    MemoryStorage.instance.selectedSkin =
                            SkinMap.instance.skins[index]

//                    game.setStage(MainMenu(game))
                }
            }
        }

        if(btnBack.boundingRectangle.contains(pointX, pointY)){
            game.setStage(MainMenu(game))
        }

        oldPointX = pointX
        oldPointY = pointY

        return super.touchDown(screenX, screenY, pointer, button)
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val (pointX, pointY) = transform(screenX, screenY, camera)
        val (deltaX, deltaY) = Pair(
                (oldPointX - pointX),
                (oldPointY - pointY)
        )

        if(skinBuyButtons.first().x + skinBuyButtons.first().width - deltaX <= camera.viewportWidth &&
                skinBuyButtons.last().x + skinBuyButtons.last().width - deltaX > 0){
            for((index, button) in skinBuyButtons.withIndex()) {
                button.x -= deltaX
                SkinMap.instance.skins[index].x -= deltaX

            }
        }

        oldPointX = pointX
        oldPointY = pointY
        return super.touchDragged(screenX, screenY, pointer)
    }
}