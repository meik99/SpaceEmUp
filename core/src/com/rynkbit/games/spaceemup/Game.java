package com.rynkbit.games.spaceemup;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rynkbit.games.spaceemup.entity.Player;
import com.rynkbit.games.spaceemup.stages.GameStage;
import com.rynkbit.games.spaceemup.stages.MainMenu;

import java.lang.reflect.InvocationTargetException;

public class Game extends ApplicationAdapter {
	Stage stage;
	SpriteBatch spriteBatch;
	Background background;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		stage = new MainMenu(this);
		background = new Background();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		background.draw(spriteBatch);
		spriteBatch.end();

		if(stage != null)
			stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose () {
		stage.dispose();
	}

	public void setStage(Stage newStage){
		stage.dispose();
		stage = null;
		stage = newStage;
	}
}
