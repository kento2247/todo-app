package com.todoClient;

public class Color {
    byte r;
    byte g;
    byte b;

    public Color(int r, int g, int b) {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
    }

    // public static Color parse(String json_str) {
    // Gson gson = new Gson();
    // Color obj = gson.fromJson(json_str, Color.class);
    // return obj;
    // }
}