package kmitl.lab03.Patcharapon58070096;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import kmitl.lab03.Patcharapon58070096.model.Dot;

public class MainActivity extends AppCompatActivity implements Dot.DotChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onRandomDotClicked(View view) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Random random = new Random();
        int centerX = random.nextInt(screenWidth);
        int centerY = random.nextInt(screenHeight);

        Dot dot = new Dot(centerX, centerY, 10, this);
    }

    @Override
    public void onDotChanged(Dot dot) {
        String posLabelString = "X: " + dot.getCenterX() + "\nY: " + dot.getCenterY();

        TextView posLabel = (TextView)findViewById(R.id.posLabel);
        posLabel.setText(posLabelString);
    }
}
