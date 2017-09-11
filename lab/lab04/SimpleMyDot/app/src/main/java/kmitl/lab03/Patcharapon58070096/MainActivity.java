package kmitl.lab03.Patcharapon58070096;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

import kmitl.lab03.Patcharapon58070096.model.Dot;
import kmitl.lab03.Patcharapon58070096.model.DotColor;
import kmitl.lab03.Patcharapon58070096.model.Dots;
import kmitl.lab03.Patcharapon58070096.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotChangeListener, DotView.OnDotViewTouchedListener {

    private DotView dotView;
    private Dots dots;
    private DotColor dotColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dots = new Dots();
        dots.setListener(this);
        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setListener(this);
        dotColor = new DotColor();
    }

    public void onRandomDotClicked(View view) {

        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        int size = random.nextInt(90) + 10;

        Dot newDot = new Dot(centerX, centerY, size, dotColor.getRandomColor());
        this.dots.addDot(newDot);
    }

    public void onClearClicked(View view) {
        dots.clearDot();
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    @Override
    public void onDotViewTouched(int x, int y) {
        if(checkForDotTouched(x, y) == null) {
            Random random = new Random();
            int size = random.nextInt(90) + 10;

            Dot newDot = new Dot(x, y, size, dotColor.getRandomColor());
            this.dots.addDot(newDot);
        } else {
            Dot selectedDot = checkForDotTouched(x, y);
            dots.removeDot(selectedDot);
        }
    }

    private Dot checkForDotTouched(int x, int y) {

        for(Dot dot: this.dots.getAllDots()) {
            if (isInRange(dot, x, y)) {
                return dot;
            }
        }
        return null;
    }

    private boolean isInRange(Dot dot, int x, int y) {
        double distance = Math.pow(Math.pow((dot.getCenterX() - x), 2) + Math.pow((dot.getCenterY() - y), 2), 0.5);
        if(distance <= dot.getRadius()) {
            return true;
        } else {
            return false;
        }
    }
}
