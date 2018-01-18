package com.rynkbit.games.spaceemup

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.rynkbit.games.spaceemup.entity.Player
import com.rynkbit.games.spaceemup.entity.SpriteActor

/**
 * Created by michael on 15.01.18.
 */
class PlayerLives(player: Player): Actor() {
    private val player: Player
    val offset: Int

    init{
        x = 20.toFloat()
        y = 20.toFloat()
        width = 64.toFloat()
        height = 64.toFloat()
        this.player = player
        offset = 20
    }


    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        if(batch != null){
            for(live in 1..player.lives){
                batch.draw(
                        player.sprite.texture,
                        x + width * live + offset * live, y, width, height
                )
            }
        }
    }
}