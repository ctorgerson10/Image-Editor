package com.mygdx.imageeditor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
    public Array<Button> Buttons = new Array<Button>();
    public static InputManager Instance;

    InputManager() {
        Instance = this;
    }
    public boolean keyDown(int i) { return false; }
    public boolean keyUp(int i) { return false; }
    public boolean keyTyped(char c) { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Button collision = CollisionManager.Instance.getCollision(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        if (collision != null) {
            collision.onPressed();
        }
        return true;
    }
    public boolean touchUp(int i, int i1, int i2, int i3) { return false; }
    public boolean touchCancelled(int i, int i1, int i2, int i3) { return false; }
    public boolean touchDragged(int i, int i1, int i2) { return false; }
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    public boolean scrolled(float v, float v1) { return false; }
}
