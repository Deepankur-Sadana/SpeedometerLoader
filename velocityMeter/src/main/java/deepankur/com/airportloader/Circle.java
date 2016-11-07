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


    private Paint paint, backgroundPaint;
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

    final int START_ANGLE = 160;
    final int END_ANGLE = 220;

    private void init() {
        initBackGroundPaint();
        radius = 100;
        final int strokeWidth = 20;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(Color.DKGRAY);

        rect = new RectF(strokeWidth / 2, strokeWidth / 2, (2 * radius) - strokeWidth / 2, (2 * radius) - strokeWidth / 2);

        //Initial Angle (optional, it can be zero)
        angle = 110;
    }

    private void initBackGroundPaint() {
        final int strokeWidth = 20;
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        backgroundPaint.setStrokeJoin(Paint.Join.ROUND);
        backgroundPaint.setColor(Color.BLACK);

        rect = new RectF(strokeWidth / 2, strokeWidth / 2, (2 * radius) - strokeWidth / 2, (2 * radius) - strokeWidth / 2);

        //Initial Angle (optional, it can be zero)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE, END_ANGLE, false, backgroundPaint);
        canvas.drawArc(rect, START_ANGLE, angle, false, paint);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
