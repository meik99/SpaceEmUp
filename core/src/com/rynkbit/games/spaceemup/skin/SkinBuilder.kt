package com.rynkbit.games.spaceemup.skin

import com.badlogic.gdx.graphics.Texture

/**
 * Created by michael on 19.01.18.
 */
class SkinBuilder{
    val skin: Skin = Skin()

    fun setName(name: String): SkinBuilder{
        skin.skinName = name
        return this
    }

    fun setTexture(path: String): SkinBuilder{
        skin.sprite.texture = Texture(path)
        return this
    }

    fun setValue(value: Int): SkinBuilder{
        skin.value = value
        return this
    }

    fun setBought(bought: Boolean): SkinBuilder{
        skin.bought = bought
        return this
    }

    fun build(): Skin{
        return skin
    }
}