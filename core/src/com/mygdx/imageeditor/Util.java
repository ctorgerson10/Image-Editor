package com.mygdx.imageeditor;

public class Util {

    public static int bytesToInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result += (int) bytes[i] << (8 * i);
        }
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

}
