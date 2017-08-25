package kmitl.lab03.Patcharapon58070096;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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

        String posLabelString = "X: " + centerX + "\nY: " + centerY;

        TextView posLabel = (TextView)findViewById(R.id.posLabel);
        posLabel.setText(posLabelString);

    }
}
