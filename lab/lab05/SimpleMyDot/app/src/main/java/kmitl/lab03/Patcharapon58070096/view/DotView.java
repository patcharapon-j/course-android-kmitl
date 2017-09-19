package kmitl.lab03.Patcharapon58070096.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab03.Patcharapon58070096.model.Dot;
import kmitl.lab03.Patcharapon58070096.model.Dots;

public class DotView extends View implements View.OnLongClickListener{

    private boolean isLongPressed = false;
    private int touchxBuffer;
    private int touchyBuffer;

    @Override
    public boolean onLongClick(View view) {
        isLongPressed = true;
        this.listener.onDotViewLongTouched(touchxBuffer, touchyBuffer);
        return false;
    }

    public interface OnDotViewTouchedListener {
        void onDotViewTouched(int x, int y);
        void onDotViewLongTouched(int x, int y);
    }

    private Paint paint;
    private Dots dots;
    private OnDotViewTouchedListener listener;

    public void setListener(OnDotViewTouchedListener listener) {
        this.listener = listener;
    }

    public void setDots(Dots dots) {
        this.dots = dots;
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
        this.setOnLongClickListener(this);
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        this.setOnLongClickListener(this);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        this.setOnLongClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.dots != null) {
            for(Dot dot: this.dots.getAllDots()){
                paint.setColor(dot.getColor());
                canvas.drawCircle(
                        dot.getCenterX(),
                        dot.getCenterY(),
                        dot.getRadius(),
                        paint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if(!isLongPressed) {
                    this.listener.onDotViewTouched(
                            (int)event.getX(),
                            (int)event.getY());
                }
                break;
            case MotionEvent.ACTION_DOWN:
                touchxBuffer = (int)event.getX();
                touchyBuffer = (int)event.getY();
                break;
        }
        isLongPressed = false;
        return super.onTouchEvent(event);
    }
}
