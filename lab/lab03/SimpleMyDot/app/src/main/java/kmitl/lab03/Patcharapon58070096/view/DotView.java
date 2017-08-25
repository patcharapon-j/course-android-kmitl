package kmitl.lab03.Patcharapon58070096.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;

import kmitl.lab03.Patcharapon58070096.model.Dot;

public class DotView extends View {

    private Paint paint;
    private ArrayList<Dot> allDots;

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(allDots != null) {
            Iterator<Dot> iter = allDots.iterator();
            while (iter.hasNext()) {
                Dot dot = iter.next();
                paint.setColor(Color.RED);
                canvas.drawCircle(
                        dot.getCenterX(),
                        dot.getCenterY(),
                        dot.getRadius(),
                        paint);
            }
        }

    }

    public void setDot(ArrayList<Dot> allDots) {
        this.allDots = allDots;
    }
}
