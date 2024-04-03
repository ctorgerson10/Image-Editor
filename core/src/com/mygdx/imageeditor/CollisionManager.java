package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
    public static CollisionManager Instance;

    public CollisionManager() {
        Instance = this;
    }

    public Button getCollision(Vector2 coordinates) {
        Button button;
        for (int i = 0; i < InputManager.Instance.Buttons.size; i++) {
            button = InputManager.Instance.Buttons.get(i);
            if (coordinates.x > button.Position.x && coordinates.x <= button.Position.x + button.Scale.x &&
                    coordinates.y > button.Position.y && coordinates.y <= button.Position.y + button.Scale.y) {
                return button;
            }
        }
        return null;
    }
}
