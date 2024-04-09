package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Rec2D {
    public Vector2 Scale;
    public Vector2 Position;
    public Texture RecTexture;
    private Pixmap _pixelMap;
    protected Color _recColor;

    Rec2D(Vector2 scale, Vector2 position, Color color) {
        Scale = scale;
        Position = position;
        _recColor = color;
        generateTexture();
        ImageEditor.Instance.Rectangles.add(this);
    }

    protected void generateTexture() {
        if (RecTexture != null) RecTexture.dispose(); // dispose of old texture to save memory 
        _pixelMap = new Pixmap((int) Scale.x, (int) Scale.y, Pixmap.Format.RGBA8888);
        _pixelMap.setColor(_recColor);
        for(int x = 0; x < _pixelMap.getWidth(); x++) {
            for(int y = 0; y < _pixelMap.getHeight(); y++) {
                _pixelMap.drawPixel(x, y);
            }
        }
        RecTexture = new Texture(_pixelMap);
    }

    public void changeColor(Color newColor) {
        _recColor = newColor;
        generateTexture();
    }
}
