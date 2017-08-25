package kmitl.lab03.Patcharapon58070096;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.Patcharapon58070096.model.Dot;
import kmitl.lab03.Patcharapon58070096.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener {

    private DotView dotView;
    private ArrayList<Dot> allDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allDots = new ArrayList<>();

        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void onRandomDotClicked(View view) {

        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        int size = random.nextInt(90) + 10;

        Dot dot = new Dot(centerX, centerY, size, this);
        dot.setColor_r(random.nextInt(255));
        dot.setColor_g(random.nextInt(255));
        dot.setColor_b(random.nextInt(255));

        allDots.add(dot);
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(allDots);
        dotView.invalidate();
    }

    public void onClearClicked(View view) {
        allDots.clear();
        dotView.invalidate();
    }
}
