package com.rynkbit.games.spaceemup.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera

/**
 * Created by michael on 19.01.18.
 */

fun transform(screenX: Int, screenY:Int, camera: Camera): Pair<Float, Float> {
    val pointX = camera.viewportWidth / Gdx.graphics.width * screenX
    val pointY = camera.viewportHeight / Gdx.graphics.height * (Gdx.graphics.height - screenY)

    return Pair(pointX, pointY)
}