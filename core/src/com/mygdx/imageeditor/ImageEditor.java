package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.*;
import com.mygdx.buttons.Button;
import com.mygdx.utility.CollisionManager;
import com.mygdx.utility.ImageInputOutput;
import com.mygdx.utility.InputManager;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	public static ImageEditor Instance;
	public Vector2 ScreenSize;
	public Array<Rec2D> Rectangles;
	public EditWindow editWindow;
	private BitmapFont _font;

	public void filesImported(String[] filePaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if(map == null) return;
		editWindow.RecTexture = new Texture(map);
	}

	private void initializeUtilityClasses() {
		new ImageInputOutput();
		InputManager inputManager = new InputManager();
		new CollisionManager();
		Gdx.input.setInputProcessor(inputManager);
		_font = new BitmapFont();
	}

	private void createGraphicalElements() {
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		Rectangles = new Array<>();
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 25);
		editWindow = new EditWindow(editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0));
		Vector2 buttonScale = new Vector2(42, 42);
		int buttonRightX = (int) ScreenSize.x - (int) editWindowSize.x - (int) buttonScale.x;
		new ColorButton(buttonScale, new Vector2(buttonRightX, 0), Color.BLACK);
		new ColorButton(buttonScale, new Vector2(0, 0), Color.WHITE);
		new ColorButton(buttonScale, new Vector2(buttonRightX, 42), Color.RED);
		new ColorButton(buttonScale, new Vector2(0, 42), Color.MAROON);
		new ColorButton(buttonScale, new Vector2(buttonRightX, 84), Color.PURPLE);
		new ColorButton(buttonScale, new Vector2(0, 84), Color.MAGENTA);
		new ColorButton(buttonScale, new Vector2(buttonRightX, 126), Color.BLUE);
		new ColorButton(buttonScale, new Vector2(0, 126), Color.CYAN);
		new ColorButton(buttonScale, new Vector2(buttonRightX, 168), Color.FOREST);
		new ColorButton(buttonScale, new Vector2(0, 168), Color.GREEN);
		new ColorButton(buttonScale, new Vector2(buttonRightX, 210), Color.ORANGE);
		new ColorButton(buttonScale, new Vector2(0, 210), Color.GOLD);
		Vector2 menuButtonScale = new Vector2(75, 25);
		new SaveButton(menuButtonScale, new Vector2(0, ScreenSize.y - 25), Color.GRAY);
		new ExitButton(menuButtonScale, new Vector2(75, ScreenSize.y - 25), Color.GRAY);
		new ClearDoodleButton(menuButtonScale, new Vector2(150, ScreenSize.y - 25), Color.GRAY);
	}

	@Override
	public void create() {
		Instance = this;
		initializeUtilityClasses();
		createGraphicalElements();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		for(Rec2D rec : Rectangles) {
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x, editWindow.Scale.y);
		for(Rec2D rec : Rectangles) {
			batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		Rec2D rec;
		com.mygdx.buttons.Button button;
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			if (rec instanceof com.mygdx.buttons.Button) {
				button = (Button) rec;
				if (button.ButtonText == null) continue;
				_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y * 0.75f,
						button.Scale.x, Align.center, false);
			}
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
