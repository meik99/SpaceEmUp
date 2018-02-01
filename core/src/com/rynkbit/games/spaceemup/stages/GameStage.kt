package com.rynkbit.games.spaceemup.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.rynkbit.games.spaceemup.Disposable
import com.rynkbit.games.spaceemup.Game
import com.rynkbit.games.spaceemup.GameParams
import com.rynkbit.games.spaceemup.PlayerLives
import com.rynkbit.games.spaceemup.data.MemoryStorage
import com.rynkbit.games.spaceemup.entity.Enemy
import com.rynkbit.games.spaceemup.entity.Player
import com.rynkbit.games.spaceemup.entity.ShootableEntity
import com.rynkbit.games.spaceemup.entity.movement.UTurn
import com.rynkbit.games.spaceemup.entity.movement.ZickZack
import java.util.*

class GameStage(val game: Game) : Stage(StretchViewport(
        GameParams.VIEWPORT_WIDTH, GameParams.VIEWPORT_HEIGHT)) {
    private val player: Player
    private val random: Random
    private val playerLives: PlayerLives

    private val generator: FreeTypeFontGenerator
    private val parameter: FreeTypeFontGenerator.FreeTypeFontParameter
    private val uiFont: BitmapFont

    private var date: Date
    private var timeToEnemy: Int

    init {
        random = Random()
        date = Date()
        timeToEnemy = 1000
        player = Player()
        playerLives = PlayerLives(player)
        addActor(player)
        addActor(playerLives)
        Gdx.input.inputProcessor = this

        generator = FreeTypeFontGenerator(
                Gdx.files.internal("kenvector_future.ttf")
        )
        parameter = FreeTypeFontGenerator.FreeTypeFontParameter()

        parameter.size = 60

        uiFont = generator.generateFont(parameter)
    }

    override fun act(delta: Float) {
        super.act(delta)

        actors.removeAll {it is Disposable && it.disposable}

        if(player.disposable){
            game.setStage(MainMenu(game))
        }

        if(Date().time - date.time > timeToEnemy){
            val enemyCount = random.nextInt(2)

            for (i in 0..enemyCount){
                val newEnemy = Enemy()

                when (random.nextInt(3)){
                    1 -> {
                        newEnemy.movement = ZickZack()
                        newEnemy.sprite.texture = Texture("Enemies/enemyRed2.png")
                        newEnemy.moneyValue = 20
                    }
                    2 -> {
                        newEnemy.movement = UTurn()
                        newEnemy.sprite.texture = Texture("Enemies/enemyRed3.png")
                        newEnemy.moneyValue = 30
                    }
                }

                newEnemy.x = camera.viewportWidth
                newEnemy.y = random.nextInt(
                        (camera.viewportHeight - newEnemy.height * 2).toInt()) + newEnemy.height

                addActor(newEnemy)
            }

            date = Date()
        }
    }

    override fun draw(){
        super.draw()

        if(batch != null){
            batch.begin()
            uiFont.draw(
                    batch,
                    "Money: ${MemoryStorage.instance.money}",
                    playerLives.x +
                            playerLives.width * (player.lives+1) +
                            playerLives.offset * (player.lives+1) + 20,
                    playerLives.y + 50
            )
            batch.end()
        }
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val pointX = camera.viewportWidth / Gdx.graphics.width * screenX
        val pointY = camera.viewportHeight / Gdx.graphics.height * (Gdx.graphics.height - screenY)

        player.x = pointX
        player.y = pointY

        return super.touchDragged(screenX, screenY, pointer)
    }

}