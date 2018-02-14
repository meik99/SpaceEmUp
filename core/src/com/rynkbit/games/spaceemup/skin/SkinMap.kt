package com.rynkbit.games.spaceemup.skin

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture

/**
 * Created by michael on 19.01.18.
 */
object SkinMap{
    private val baseName: String = "playerShip{0}_{1}.png"
    private val colors: Array<String> = arrayOf(
            "blue", "green", "orange", "red"
    )


    private val _skins: MutableList<Skin> = mutableListOf()

    val skins: MutableList<Skin>
        get() {
            if(_skins.size == 0){
                init()
            }
            return _skins
        }


    private fun init() {

        for(i in 1..3){
            for((index, color) in colors.withIndex()){
                val skinBuilder = SkinBuilder()
                _skins.add(skinBuilder
                        .setName("${color.capitalize()} $i")
                        .setValue(i*(index+1)*500)
                        .setTexture(getFileName(i, color))
                        .setBought(false)
                        .build())
            }
        }

        _skins.first().bought = true
        _skins.first().value = 0
    }

    private fun getFileName(index: Int, color: String): String{
        return baseName
                .replace("{0}", index.toString())
                .replace("{1}", color)

    }
}