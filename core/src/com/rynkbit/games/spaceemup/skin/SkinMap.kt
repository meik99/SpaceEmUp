package com.rynkbit.games.spaceemup.skin

import com.badlogic.gdx.Gdx

/**
 * Created by michael on 19.01.18.
 */
class SkinMap private constructor(){
    companion object {
        val instance: SkinMap by lazy { SkinMap() }
    }

    val skins: MutableList<Skin>

    init {
        skins = mutableListOf()

        val files = Gdx.files.internal("").list()
        var counter = 0


        for(file in files){
            if(file != null){
                if (file.isDirectory == false &&
                        file.name().contains("playerShip")){
                    val filename = file.name()

                    if(filename != null){
                        val skinename = filename.removeSuffix(".png").removePrefix("/")
                        val skinBuilder = SkinBuilder()
                        val skin = skinBuilder
                                .setName(skinename)
                                .setBought(counter == 0)
                                .setTexture(file.path().removePrefix("/"))
                                .setValue(500*counter++)
                                .build()

                        skins.add(skin)
                    }
                }
            }
        }
    }
}