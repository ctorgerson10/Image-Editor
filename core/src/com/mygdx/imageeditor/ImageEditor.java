package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	Button button1, button2, button3, button4, button5;
	public static ImageEditor Instance;
	public Vector2 ScreenSize;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InputManager inputManager = new InputManager();
		CollisionManager collisionManager = new CollisionManager();
		Gdx.input.setInputProcessor(inputManager);
		Vector2 rectangleScale = new Vector2(100, 100);
		float distanceFactor = 1/7f;
		float lowX = ScreenSize.x * distanceFactor;
		float lowY = ScreenSize.y * distanceFactor;
		float highX = ScreenSize.x - (ScreenSize.x * distanceFactor) - rectangleScale.x;
		float highY = ScreenSize.y - (ScreenSize.y * distanceFactor) - rectangleScale.y;
		button1 = new Button(
				rectangleScale,
				new Vector2(ScreenSize.x / 2f - rectangleScale.x/2f, ScreenSize.y / 2f - rectangleScale.y/2f),
				Color.WHITE);
		button2 = new Button(
				rectangleScale,
				new Vector2(lowX, lowY),
				Color.YELLOW);
		button3 = new Button(
				rectangleScale,
				new Vector2(highX, lowY),
				Color.GREEN);
		button4 = new Button(
				rectangleScale,
				new Vector2(lowX, highY),
				Color.RED);
		button5 = new Button(
				rectangleScale,
				new Vector2(highX, highY),
				Color.BLUE);
		Instance = this;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		for (Button button : InputManager.Instance.Buttons) {
			batch.draw(button.RecTexture, button.Position.x, button.Position.y);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Button button : InputManager.Instance.Buttons) {
			button.RecTexture.dispose();
		}
	}
}
