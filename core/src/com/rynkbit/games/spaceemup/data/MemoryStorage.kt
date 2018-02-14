package com.rynkbit.games.spaceemup.data

import com.rynkbit.games.spaceemup.entity.laser.fire.LaserGenerator
import com.rynkbit.games.spaceemup.entity.laser.fire.SingleFire
import com.rynkbit.games.spaceemup.skin.Skin
import com.rynkbit.games.spaceemup.skin.SkinMap

/**
 * Created by michael on 17.01.18.
 */
object MemoryStorage{
    var money: Int = 0
    var selectedSkin: Skin = SkinMap.skins.first()
    var fireBought: Array<Boolean> =  arrayOf(true, false, false, false)
    var laserGenerator: LaserGenerator = SingleFire()
}