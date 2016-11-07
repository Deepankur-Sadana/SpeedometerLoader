package deepankur.com.airportloader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by deepankur on 11/7/16.
 */

public class Circle extends View {

    private static final int START_ANGLE_POINT = 0;

    private Paint paint;
    private RectF rect;

    private float angle;

    public Circle(Context context) {
        super(context);
        init();
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int radius = 50;

    public void setRadius(int radius) {
        this.radius = radius;
        init();
        invalidate();
    }

    private void init() {
        radius = 100;
        final int strokeWidth = 20;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(Color.RED);

        rect = new RectF(strokeWidth / 2, strokeWidth / 2, (2 * radius) - strokeWidth/2, (2 * radius) - strokeWidth/2);

        //Initial Angle (optional, it can be zero)
        angle = 340;
    }

    private void initBackGroundPaint(){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
