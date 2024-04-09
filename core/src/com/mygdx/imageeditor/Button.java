package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D implements IClickable, IHoverable {
    private final Color _startColor;
    private final Color _hoveredColor;
    public enum ButtonState {Clicked, Hovered, None};
    private ButtonState _state;

    public Button (Vector2 scale, Vector2 position, Color recColor) {
        super(scale, position, recColor);
        _state = ButtonState.None;
        _startColor = _recColor;
        _hoveredColor = new Color(_startColor.r / 2f, _startColor.g / 2f , _startColor.b / 2f, 1);
        InputManager.Instance.Clickables.add(this);
        InputManager.Instance.Hoverables.add(this);
    }

    public void onClickDown(Vector2 position) {
        if (_state == ButtonState.Clicked) return;
        _state = ButtonState.Clicked;
        _recColor = new Color(_startColor.r / 4f, _startColor.g / 4f , _startColor.b / 4f, 1);
        generateTexture();
    }

    public void onClickUp(Vector2 position) {
        _state = ButtonState.Hovered;
        _recColor = _hoveredColor;
        generateTexture();
    }

    public void onHovered() {
        if (_state == ButtonState.Clicked) return;
        if (_state == ButtonState.Hovered) return;
        _state = ButtonState.Hovered;
        _recColor = _hoveredColor;
        generateTexture();
    }

    public void onHoverEnd() {
        _state = ButtonState.None;
        _recColor = _startColor;
        generateTexture();
    }

    public void onClickDragged(Vector2 worldPosition) {
        return;
    }
}
