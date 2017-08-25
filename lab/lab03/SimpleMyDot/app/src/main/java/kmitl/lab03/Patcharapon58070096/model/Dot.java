package kmitl.lab03.Patcharapon58070096.model;

public class Dot {

    private int centerX;
    private int centerY;
    private int radius;

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
