package com.rynkbit.games.spaceemup.animation

import com.badlogic.gdx.graphics.Texture

class Explosion: Animation2D(TEXTURE, FRAME_COLS, FRAME_ROWS) {
    companion object {
        val FRAME_COLS = 8
        val FRAME_ROWS = 8
        val TEXTURE = Texture("Effects/explosion.png")
    }
}