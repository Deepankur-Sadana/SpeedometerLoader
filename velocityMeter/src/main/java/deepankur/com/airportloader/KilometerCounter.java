package deepankur.com.airportloader;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by deepankur on 11/7/16.
 */

public class KilometerCounter extends FrameLayout {
    private static final int TEXT_COLOR = Color.WHITE, BACKGROUND_COLOR = Color.TRANSPARENT;
    private static final int ANIMATION_DURATION = 1000;

    public KilometerCounter(Context context) {
        super(context);
    }

    public KilometerCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KilometerCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    final int NUMBER_OF_TICKERS = 5;
    TextView[] textViews = new TextView[NUMBER_OF_TICKERS];

    TextView initialTextView;

    private void init(Context context) {
        TextView textView = getTextView(getStringForStripElement(0));
        textView.setLayoutParams(new ViewGroup.LayoutParams(getWidth(), getHeight()));
        this.addView(textView);

    }

    TextView getTextView(String text) {
        int width = getWidth();
        int height = getHeight();
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        textView.setBackgroundColor(BACKGROUND_COLOR);
        textView.setText(text);
        return textView;
    }

    private Context context;

    private void doAnimation() {
        int totalHeightOfStrip = initAnimation();
        strip.animate().translationY(totalHeightOfStrip).setDuration(ANIMATION_DURATION).setInterpolator(new DecelerateInterpolator()).withEndAction(postAnimationRunnable).start();
    }

    Runnable postAnimationRunnable = new Runnable() {
        @Override
        public void run() {
            getTextView(getStringForStripElement(NUMBER_OF_TICKERS));
        }
    };


    String getStringForStripElement(int index) {
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        return String.valueOf(n);
    }

    private LinearLayout strip;

    private int initAnimation() {
        strip = new LinearLayout(context);
        strip.setOrientation(LinearLayout.VERTICAL);
        int width = getWidth();
        int height = getHeight();
        int totalHeight = 0;
        for (int i = 0; i < NUMBER_OF_TICKERS; i++) {
            strip.addView(getTextView(getStringForStripElement(i)));
            totalHeight += height;
        }
        strip.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        this.addView(strip);
        return totalHeight;
    }
}
