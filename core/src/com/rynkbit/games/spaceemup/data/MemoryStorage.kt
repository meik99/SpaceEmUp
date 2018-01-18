package com.rynkbit.games.spaceemup.data

/**
 * Created by michael on 17.01.18.
 */
class MemoryStorage{
    companion object {
        @JvmStatic val instance = MemoryStorage()
    }

    var money: Int = 0
}