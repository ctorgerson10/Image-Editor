package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	public static ImageEditor Instance;
	public Vector2 ScreenSize;
	public Array<Rec2D> Rectangles;
	public EditWindow editWindow;

	@Override
	public void create() {

		// singleton
		Instance = this;

		// init
		new ImageInputOutput();
		Pixmap editMap = ImageInputOutput.Instance.loadImage("blackbuck.bmp");
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		InputManager inputManager = new InputManager();
		CollisionManager collisionManager = new CollisionManager();
		Gdx.input.setInputProcessor(inputManager);

		Rectangles = new Array<>();

		// screen objects
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 50);
		editWindow = new EditWindow(
			editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0), Color.GRAY
		);
		editWindow.DoodleTexture = new Texture(editMap);
		Vector2 buttonScale = new Vector2(84, 84);
		Vector2 button1Position = new Vector2(ScreenSize.x-editWindowSize.x-buttonScale.x, 0);
		Button button1 = new Button(buttonScale, button1Position, Color.RED);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		for(Rec2D rec : Rectangles) {
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x, editWindow.Scale.y);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
