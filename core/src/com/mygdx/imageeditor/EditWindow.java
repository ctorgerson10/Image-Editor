package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utility.IClickable;
import com.mygdx.utility.InputManager;

public class EditWindow extends Rec2D implements IClickable {

    public Texture DoodleTexture;
    public Pixmap DoodleMap;
    private Vector2 _previousPaintPosition;
    public static EditWindow Instance;
    public Color DrawColor;

    public EditWindow(Vector2 scale, Vector2 position) {
        super(scale, position, Color.SLATE);
        Instance = this;
        InputManager.Instance.Clickables.add(this);
        DoodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
        DrawColor = Color.ORANGE;
        DoodleMap.setColor(DrawColor);
        DoodleTexture = new Texture(DoodleMap);
    }

    public void paintAtPosition(Vector2 worldPosition) {
        Vector2 paintPosition = new Vector2((int) (worldPosition.x - (ImageEditor.Instance.ScreenSize.x - Scale.x)),
                (int) (worldPosition.y - (ImageEditor.Instance.ScreenSize.y - Scale.y)));
        int startX = (int) _previousPaintPosition.x;
        int startY = (int) _previousPaintPosition.y;
        int endX = (int) paintPosition.x;
        int endY = (int) paintPosition.y;
        DoodleMap.drawLine(startX, startY, endX, endY);
        DoodleMap.drawLine(startX + 1, startY, endX + 1, endY);
        DoodleMap.drawLine(startX - 1, startY, endX - 1, endY);
        DoodleMap.drawLine(startX, startY + 1, endX, endY + 1);
        DoodleMap.drawLine(startX, startY - 1, endX, endY - 1);
        _previousPaintPosition = paintPosition;
        DoodleTexture = new Texture(DoodleMap);
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
