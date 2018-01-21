package com.rynkbit.games.spaceemup.skin

import com.badlogic.gdx.graphics.Texture
import com.rynkbit.games.spaceemup.entity.SpriteActor

/**
 * Created by michael on 19.01.18.
 */
class Skin(): SpriteActor(Texture("playerShip1_blue.png")){
    var bought: Boolean
    var value: Int
    var skinName: String

    init{
        bought = false
        value = 0
        skinName = ""
    }
}