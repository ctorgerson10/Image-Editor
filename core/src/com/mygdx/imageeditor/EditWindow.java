package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class EditWindow extends Rec2D implements IClickable {

    public Texture DoodleTexture;
    private Pixmap _doodleMap;
    private Vector2 _previousPaintPosition;

    public EditWindow(Vector2 scale, Vector2 position, Color backgroundColor) {
        super(scale, position, backgroundColor);
        InputManager.Instance.Clickables.add(this);
        _doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
        _doodleMap.setColor(Color.ORANGE);
        DoodleTexture = new Texture(_doodleMap);
    }

    public void paintAtPosition(Vector2 worldPosition) {
        Vector2 paintPosition = new Vector2((int) (worldPosition.x - (ImageEditor.Instance.ScreenSize.x - Scale.x)),
                (int) (worldPosition.y - (ImageEditor.Instance.ScreenSize.y - Scale.y)));
        int startX = (int) _previousPaintPosition.x;
        int startY = (int) _previousPaintPosition.y;
        int endX = (int) paintPosition.x;
        int endY = (int) paintPosition.y;
        _doodleMap.drawLine(startX, startY, endX, endY);
        _doodleMap.drawLine(startX + 1, startY, endX + 1, endY);
        _doodleMap.drawLine(startX - 1, startY, endX - 1, endY);
        _previousPaintPosition = paintPosition;
        DoodleTexture = new Texture(_doodleMap);
    }

    public void onClickDown(Vector2 position) {
        if (_previousPaintPosition == null)
            _previousPaintPosition = new Vector2((int) (position.x - (ImageEditor.Instance.ScreenSize.x - Scale.x)),
                    (int) (position.y - (ImageEditor.Instance.ScreenSize.y - Scale.y)));
        paintAtPosition(position);
    }

    public void onClickUp(Vector2 mousePosition) {
        _previousPaintPosition = null;
    }

    public void onClickDragged(Vector2 mousePosition) {
        paintAtPosition(mousePosition);
    }
}
