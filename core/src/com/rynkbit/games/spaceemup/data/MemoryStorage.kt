package com.rynkbit.games.spaceemup.data

import com.rynkbit.games.spaceemup.skin.Skin
import com.rynkbit.games.spaceemup.skin.SkinMap

/**
 * Created by michael on 17.01.18.
 */
class MemoryStorage{
    companion object {
        @JvmStatic val instance = MemoryStorage()
    }

    var money: Int = 0
    var selectedSkin: Skin

    init {
        selectedSkin = SkinMap.instance.skins.first()
    }
}