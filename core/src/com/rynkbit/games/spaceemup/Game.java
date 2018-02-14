package com.rynkbit.games.spaceemup;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rynkbit.games.spaceemup.data.MemoryStorage;
import com.rynkbit.games.spaceemup.entity.Player;
import com.rynkbit.games.spaceemup.skin.Skin;
import com.rynkbit.games.spaceemup.skin.SkinMap;
import com.rynkbit.games.spaceemup.stages.GameStage;
import com.rynkbit.games.spaceemup.stages.MainMenu;
import com.rynkbit.games.spaceemup.stages.SkinStage;
import com.rynkbit.games.spaceemup.stages.WeaponShop;

import java.lang.reflect.InvocationTargetException;

public class Game extends ApplicationAdapter {
	Stage stage;
	SpriteBatch spriteBatch;
	Background background;

	@Override
	public void create () {
		Preferences preferences = Gdx.app.getPreferences("savegame");
		MemoryStorage.INSTANCE.setMoney(
				preferences.getInteger("money")
		);

		for (Skin skin : SkinMap.INSTANCE.getSkins()){
			skin.setBought(
					preferences.getBoolean(skin.getSkinName())
			);

			if(preferences.getString("selectedSkin").equals(skin.getSkinName())){
				MemoryStorage.INSTANCE.setSelectedSkin(skin);
			}
		}

		for(int i = 0; i < MemoryStorage.INSTANCE.getFireBought().length; i++){
			MemoryStorage.INSTANCE.getFireBought()[i] =
					preferences.getBoolean("laser" + i);
		}

//		MemoryStorage.INSTANCE.setMoney(10000);
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

		if(stage != null) {
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}


	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		Preferences preferences = Gdx.app.getPreferences("savegame");
		preferences.putInteger("money", MemoryStorage.INSTANCE.getMoney());
		preferences.putString("selectedSkin", MemoryStorage.INSTANCE.getSelectedSkin().getSkinName());

		for (Skin skin : SkinMap.INSTANCE.getSkins()){
			preferences.putBoolean(skin.getSkinName(), skin.getBought());
		}
		for(int i = 0; i < MemoryStorage.INSTANCE.getFireBought().length; i++){
			preferences.putBoolean("laser" + i, MemoryStorage.INSTANCE.getFireBought()[i]);
		}

		preferences.flush();
		super.pause();

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
