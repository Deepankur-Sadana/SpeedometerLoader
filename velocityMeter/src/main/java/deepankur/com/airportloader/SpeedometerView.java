package deepankur.com.airportloader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by deepankur on 11/7/16.
 */

public class SpeedometerView extends FrameLayout {
    public SpeedometerView(Context context) {
        super(context);
        init(context);
    }

    public SpeedometerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SpeedometerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;

    private void init(Context context) {
        int width = getWidth();
        int height = getHeight();
        Circle circle = new Circle(context);
        circle.setRadius(width);
        this.addView(circle);
    }
}