package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
    public static CollisionManager Instance;

    public CollisionManager() {
        Instance = this;
    }

    public IClickable getClicked(Vector2 coordinates) {
        IClickable clickable;
        for (int i = 0; i < InputManager.Instance.Clickables.size; i++) {
            clickable = InputManager.Instance.Clickables.get(i);
            if (clickable instanceof Rec2D) {
                Rec2D rec = (Rec2D) clickable;
                if (coordinates.x > rec.Position.x && coordinates.x <= rec.Position.x + rec.Scale.x &&
                    coordinates.y > rec.Position.y && coordinates.y <= rec.Position.y + rec.Scale.y) {
                    return clickable;
                }
            }
            
        }

        return null;
    }

    public IHoverable getHovered(Vector2 coordinates) {
        IHoverable hoverable;
        for (int i = 0; i < InputManager.Instance.Hoverables.size; i++) {
            hoverable = InputManager.Instance.Hoverables.get(i);
            if (hoverable instanceof Rec2D) {
                Rec2D rec = (Rec2D) hoverable;
                if (coordinates.x > rec.Position.x && coordinates.x <= rec.Position.x + rec.Scale.x &&
                    coordinates.y > rec.Position.y && coordinates.y <= rec.Position.y + rec.Scale.y) {
                    return hoverable;
                }
            }
            
        }

        return null;
    }
}
