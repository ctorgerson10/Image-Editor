package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Outline {
    public Texture OutlineTex;

    public Outline(Vector2 recSize, Color color, int thickness) {
        Pixmap map = new Pixmap((int) recSize.x, (int) recSize.y, Pixmap.Format.RGBA8888);
        map.setColor(color);
        //Top
        for(int x = 0; x < map.getWidth(); x++) {
            for(int y = 0; y < thickness; y++) {
                map.drawPixel(x, y);
            }
        }
        //Left
        for (int y = thickness; y < map.getHeight() - thickness; y++) {
            for (int x = 0; x < thickness; x++) {
                map.drawPixel(x, y);
            }
        }
        //Right
        for (int y = thickness; y < map.getHeight() - thickness; y++) {
            for (int x = map.getWidth() - thickness; x < map.getWidth(); x++) {
                map.drawPixel(x, y);
            }
        }
        //Bottom
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = map.getHeight() - thickness; y < map.getHeight(); y++) {
                map.drawPixel(x, y);
            }
        }
        OutlineTex = new Texture(map);
    }
}
