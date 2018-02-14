package com.rynkbit.games.spaceemup.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.rynkbit.games.spaceemup.Game
import com.rynkbit.games.spaceemup.GameParams
import com.rynkbit.games.spaceemup.data.FontMap
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.Player
import com.rynkbit.games.spaceemup.entity.laser.fire.DoubleFire
import com.rynkbit.games.spaceemup.entity.laser.fire.RapidSingleFire
import com.rynkbit.games.spaceemup.entity.laser.fire.SingleFire
import com.rynkbit.games.spaceemup.entity.laser.fire.TripleFire
import com.rynkbit.games.spaceemup.ui.Button
import com.rynkbit.games.spaceemup.ui.transform
import sun.applet.Main

class WeaponShop(val game: Game): Stage(StretchViewport(
        GameParams.VIEWPORT_WIDTH, GameParams.VIEWPORT_HEIGHT
)) {

    companion object {
        val singleFire = Player()
        val doubleFire = Player()
        val tripleFire = Player()
        val rapidFire = Player()
    }

    val buttonBuySingle: Button = Button(Button.buttonGreenTexture)
    val buttonBuyDouble: Button = Button(Button.buttonGreenTexture)
    val buttonBuyTriple: Button = Button(Button.buttonGreenTexture)
    val buttonBuyRapid: Button = Button(Button.buttonGreenTexture)

    val buttonBack: Button = Button(Button.buttonRedTexture)

    val fireOffsetX = 550.toFloat()
    val fireOffsetY = 125.toFloat()

    val buttonOffsetX = 200.toFloat()

    init{
        Gdx.input.inputProcessor = this

        singleFire.laserGenerator = SingleFire()
        doubleFire.laserGenerator = DoubleFire()
        tripleFire.laserGenerator = TripleFire()
        rapidFire.laserGenerator = RapidSingleFire()

        singleFire.mute = true
        doubleFire.mute = true
        tripleFire.mute = true
        rapidFire.mute = true

        singleFire.x = fireOffsetX.toFloat()
        singleFire.y = camera.viewportHeight - singleFire.height - fireOffsetY

        doubleFire.x = singleFire.x
        doubleFire.y = singleFire.y - singleFire.height - fireOffsetY

        tripleFire.x = singleFire.x
        tripleFire.y = doubleFire.y - doubleFire.height - fireOffsetY

        rapidFire.x = singleFire.x
        rapidFire.y = tripleFire.y - tripleFire.height - fireOffsetY

        buttonBuySingle.x = buttonOffsetX
        buttonBuyDouble.x = buttonOffsetX
        buttonBuyTriple.x = buttonOffsetX
        buttonBuyRapid.x = buttonOffsetX

        buttonBack.x = buttonOffsetX
        buttonBack.y = 100.toFloat()
        buttonBack.scaleBy(1.toFloat(), 1.5.toFloat())
        buttonBack.text = "Back"

        buttonBuySingle.y = singleFire.y + 10.toFloat()
        buttonBuyDouble.y = doubleFire.y + 10.toFloat()
        buttonBuyTriple.y = tripleFire.y + 10.toFloat()
        buttonBuyRapid.y = rapidFire.y + 10.toFloat()

        buttonBuySingle.scaleBy(1.toFloat(), 1.5.toFloat())
        buttonBuyDouble.scaleBy(1.toFloat(), 1.5.toFloat())
        buttonBuyTriple.scaleBy(1.toFloat(), 1.5.toFloat())
        buttonBuyRapid.scaleBy(1.toFloat(), 1.5.toFloat())

        buttonBuySingle.textOffset = -90
        buttonBuyDouble.textOffset = buttonBuySingle.textOffset
        buttonBuyTriple.textOffset = buttonBuySingle.textOffset
        buttonBuyRapid.textOffset = buttonBuySingle.textOffset

        addActor(singleFire)
        addActor(doubleFire)
        addActor(tripleFire)
        addActor(rapidFire)

        addActor(buttonBuySingle)
        addActor(buttonBuyDouble)
        addActor(buttonBuyTriple)
        addActor(buttonBuyRapid)

        addActor(buttonBack)
    }

    override fun act(delta: Float) {
        super.act(delta)
        buttonBuySingle.text =
                if(MemoryStorage.laserGenerator is SingleFire &&
                        MemoryStorage.laserGenerator is RapidSingleFire == false)
                    "Selected"
                else
                    "Select"

        buttonBuyDouble.text =
                if(MemoryStorage.laserGenerator is DoubleFire)
                    "Selected"
                else if(MemoryStorage.fireBought[1] == true)
                    "Select"
                else
                    "3000"

        buttonBuyTriple.text =
                if(MemoryStorage.laserGenerator is TripleFire)
                    "Selected"
                else if(MemoryStorage.fireBought[2] == true)
                    "Select"
                else
                    "5000"

        buttonBuyRapid.text =
                if(MemoryStorage.laserGenerator is RapidSingleFire)
                    "Selected"
                else if(MemoryStorage.fireBought[3] == true)
                    "Select"
                else
                    "10000"
    }

    override fun draw() {
        super.draw()

        if(batch != null){
            batch.begin()

            FontMap.whiteTextFont.draw(
                    batch,
                    "Money: ${MemoryStorage.money}",
                    20.toFloat(),
                    camera.viewportHeight - 20.toFloat()
            )

            batch.end()
        }
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val (pointX, pointY) = transform(screenX, screenY, camera)

        if(buttonBuySingle.boundingRectangle.contains(pointX, pointY)){
            MemoryStorage.laserGenerator = SingleFire()
        }
        else if(buttonBuyDouble.boundingRectangle.contains(pointX, pointY)){
            if(MemoryStorage.fireBought[1] == false && MemoryStorage.money >= 3000){
                MemoryStorage.money -= 3000
                MemoryStorage.fireBought[1] = true
                MemoryStorage.laserGenerator = DoubleFire()
            }else if(MemoryStorage.fireBought[1] == true){
                MemoryStorage.laserGenerator = DoubleFire()
            }
        }
        else if(buttonBuyTriple.boundingRectangle.contains(pointX, pointY)){
            if(MemoryStorage.fireBought[2] == false && MemoryStorage.money >= 5000){
                MemoryStorage.money -= 5000
                MemoryStorage.fireBought[2] = true
                MemoryStorage.laserGenerator = TripleFire()
            }else if(MemoryStorage.fireBought[2] == true){
                MemoryStorage.laserGenerator = TripleFire()
            }
        }
        else if(buttonBuyRapid.boundingRectangle.contains(pointX, pointY)){
            if(MemoryStorage.fireBought[3] == false && MemoryStorage.money >= 10000){
                MemoryStorage.money -= 10000
                MemoryStorage.fireBought[3] = true
                MemoryStorage.laserGenerator = RapidSingleFire()
            }else if(MemoryStorage.fireBought[3] == true){
                MemoryStorage.laserGenerator = RapidSingleFire()
            }
        }
        else if(buttonBack.boundingRectangle.contains(pointX, pointY)){
            game.setStage(MainMenu(game))
        }

        return true
    }
}