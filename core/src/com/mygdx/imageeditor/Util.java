package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {

    public static int bytesToInt(byte[] bytes) {
        int[] unsignedBytes = unsignBytes(bytes);
        int result = 0;
        for (int i = 0; i < unsignedBytes.length; i++) {
            result += unsignedBytes[i] << (8 * i);
        }
        return result;
    }

    public static byte[] intToSignedBytes(int value) {
        byte[] result = new byte[4];
        result[0] = (byte) ((value >> 24) & 0xFF);
        result[1] = (byte) ((value >> 16) & 0xFF);
        result[2] = (byte) ((value >> 8) & 0xFF);
        result[3] = (byte) (value & 0xFF);
        return result;
    }

    public static int[] unsignBytes(byte[] bytes) {
        int[] result = new int[bytes.length];
        int distance;
        int currentByte;
        for (int i = 0; i < bytes.length; i++) {
            currentByte = bytes[i];
            if (currentByte < 0) {
                distance = currentByte + 129;
                result[i] = 127 + distance;
            } else {
                result[i] = currentByte;
            }
        }
        return result;
    }

    public static Pixmap scalePixmap(Pixmap map, Vector2 desiredSize) {
        Pixmap newMap = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
        for(int x = 0; x < newMap.getWidth(); x++) {
            for(int y = 0; y < newMap.getHeight(); y++) {
                int sourceX = Math.min((int) ((float) x / newMap.getWidth() * map.getWidth()), map.getWidth() - 1);
                int sourceY = Math.min((int) ((float) y / newMap.getHeight() * map.getHeight()), map.getHeight() - 1);
                newMap.setColor(map.getPixel(sourceX, sourceY));
                newMap.drawPixel(x, y);
            }
        }
        return newMap;
    }

}
