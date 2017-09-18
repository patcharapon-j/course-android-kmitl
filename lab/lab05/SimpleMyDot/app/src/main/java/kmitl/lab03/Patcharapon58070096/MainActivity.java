package kmitl.lab03.Patcharapon58070096;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;
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
        dotView.setBackgroundColor(Color.WHITE);
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

    public void onUndoClicked(View view) {
        dots.undoDot();
    }

    public void onShareClicked(View view) {
        this.attemptShare();
    }

    private Bitmap getScreenshot() {
        View root = this.dotView.getRootView();
        Bitmap screenshot = Bitmap.createBitmap(root.getWidth(), root.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenshot);
        root.draw(canvas);
        return screenshot;
    }

    private void attemptShare() {
        if(requestExternalStoragePermission()) {
            Bitmap screenshot = this.getScreenshot();
            Uri screenshotUri = getImageUri(this.getApplicationContext(), screenshot);
            presentShareScreen(screenshotUri);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void presentShareScreen(Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share Dot Screenshot"));
    }

    private boolean requestExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.attemptShare();
                }
                return;
            }
        }
    }
}
