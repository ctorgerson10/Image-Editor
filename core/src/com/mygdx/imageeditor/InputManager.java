package com.mygdx.imageeditor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.io.IOException;

public class InputManager implements InputProcessor {
    public Array<IHoverable> Hoverables = new Array<IHoverable>();
    public Array<IClickable> Clickables = new Array<IClickable>();
    public static InputManager Instance;
    private IHoverable _currentlyHovered;
    private IHoverable _lastHovered;
    private IClickable _currentlyClicked;
    private boolean _controlPressed = false;

    InputManager() {
        Instance = this;
    }
    public boolean keyDown(int keycode) {
        if(_controlPressed && keycode == Input.Keys.S)
            try {
                ImageInputOutput.Instance.saveImage("C:\\Users\\ctorg\\OneDrive\\Desktop\\output.bmp");
                System.out.println("Saved image to C:\\Users\\ctorg\\OneDrive\\Desktop\\output.bmp");
            }
            catch (IOException e) {e.printStackTrace();}
        if(keycode == Input.Keys.CONTROL_LEFT) _controlPressed = true;
        return false;
    }
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.CONTROL_LEFT) _controlPressed = false;
        return false;
    }
    public boolean keyTyped(char c) { return false; }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        IClickable collision = CollisionManager.Instance.getClicked(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        _currentlyClicked = collision;
        if (collision != null) {
            collision.onClickDown(new Vector2(screenX, screenY));
        }
        return true;
    }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (_currentlyClicked != null) _currentlyClicked.onClickUp(new Vector2(screenX, screenY));
        _currentlyClicked = null;
        return true;
    }
    public boolean touchCancelled(int i, int i1, int i2, int i3) { return false; }
    public boolean touchDragged(int screenX, int screenY, int pointer) { 
        mouseMoved(screenX, screenY);
        if(_currentlyClicked != null) {
            _currentlyClicked.onClickDragged(new Vector2(screenX, screenY));
        } 
        return true;
    }
    public boolean mouseMoved(int screenX, int screenY) { 
        _currentlyHovered = CollisionManager.Instance.getHovered(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
        if (_currentlyHovered != null) {
            _currentlyHovered.onHovered();
            _lastHovered = _currentlyHovered;
        } else {
            if (_lastHovered != null) {
                _lastHovered.onHoverEnd();
                _lastHovered = null;
            }
        }
        return true;
    }
    public boolean scrolled(float v, float v1) { return false; }
}
