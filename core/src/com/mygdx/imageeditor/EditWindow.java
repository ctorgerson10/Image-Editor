package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable {

    public Texture DoodleTexture;
    private Pixmap _doodleMap;
    
    public EditWindow(Vector2 scale, Vector2 position, Color backgroundColor) {
        super(scale, position, backgroundColor);
        InputManager.Instance.Clickables.add(this);
        _doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
        _doodleMap.setColor(Color.ORANGE);
        DoodleTexture = new Texture(_doodleMap);
    }

    public void paintAtPosition(Vector2 worldPosition) {
        _doodleMap.drawPixel((int) (worldPosition.x - (ImageEditor.Instance.ScreenSize.x - Scale.x)), (int) (worldPosition.y - (ImageEditor.Instance.ScreenSize.y - Scale.y)));
        DoodleTexture = new Texture(_doodleMap);
    }

    public void onClickDown(Vector2 position) {
        paintAtPosition(position);
    }

    public void onClickUp(Vector2 mousePosition) {
        return;
    }

    public void onClickDragged(Vector2 mousePosition) {
        paintAtPosition(mousePosition);
    }
}
