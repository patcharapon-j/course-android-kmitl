package kmitl.lab03.Patcharapon58070096.model;

import android.graphics.Color;

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;
    private int color_r;
    private int color_g;
    private int color_b;

    private DotChangedListener dotChangedListener;

    public interface DotChangedListener {
        void onDotChanged(Dot dot);
    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public Dot(int centerX, int centerY, int radius, DotChangedListener dotChangedListener) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.dotChangedListener = dotChangedListener;

        this.dotChangedListener.onDotChanged(this);
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

    public void setDotChangedListener(DotChangedListener dotChangedListener) {
        this.dotChangedListener = dotChangedListener;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        dotChangedListener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        dotChangedListener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        dotChangedListener.onDotChanged(this);
    }
}
