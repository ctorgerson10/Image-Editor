package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.EditWindow;

public class ColorButton extends Button {

    public ColorButton(Vector2 scale, Vector2 position, Color recColor) {
        super(scale, position, recColor);
    }

    public void onClickUp(Vector2 position) {
        super.onClickUp(position);
        EditWindow.Instance.DrawColor = _startColor;
        EditWindow.Instance.DoodleMap.setColor(_startColor);
    }
}
