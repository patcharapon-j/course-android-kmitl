package kmitl.lab03.Patcharapon58070096.model;

import android.graphics.Color;

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;
    private int color;
    private boolean customColorEnabled;
    private int color_r;
    private int color_g;
    private int color_b;

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = Color.RED;
        customColorEnabled = false;
    }

    public Dot(int centerX, int centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    public boolean isCustomColorEnabled() {
        return customColorEnabled;
    }

    public void setCustomColorEnabled(boolean customColorEnabled) {
        this.customColorEnabled = customColorEnabled;
    }

    public int getColor_r() {
        return color_r;
    }

    public void setColor_r(int color_r) {
        this.color_r = color_r;
    }

    public int getColor_g() {
        return color_g;
    }

    public void setColor_g(int color_g) {
        this.color_g = color_g;
    }

    public int getColor_b() {
        return color_b;
    }

    public void setColor_b(int color_b) {
        this.color_b = color_b;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
