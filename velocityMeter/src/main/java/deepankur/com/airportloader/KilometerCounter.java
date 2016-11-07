package deepankur.com.airportloader;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by deepankur on 11/7/16.
 */

public class KilometerCounter extends FrameLayout implements AnimationConstants {
    private static final int TEXT_COLOR = Color.WHITE, BACKGROUND_COLOR = Color.TRANSPARENT;
    private String TAG = getClass().getSimpleName();

    public KilometerCounter(Context context) {
        super(context);
        init(context);
    }

    public KilometerCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public KilometerCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    final int NUMBER_OF_TICKERS = 10;
    TextView[] textViews = new TextView[NUMBER_OF_TICKERS];

    private void init(Context context) {
        this.context = context;
        TextView textView = getTextView(getStringForStripElement(0));
        textView.setAlpha(0f);
        this.addView(textView);
        addVTO(textView);

    }


    private TextView getTextView(String text) {
        int width = getWidth();
        int height = getHeight();
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        textView.setBackgroundColor(BACKGROUND_COLOR);
        textView.setText(text);
        textView.setTextColor(TEXT_COLOR);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }

    private Context context;


    private Runnable postAnimationRunnable = new Runnable() {
        @Override
        public void run() {
            getTextView(getStringForStripElement(NUMBER_OF_TICKERS));
        }
    };


    private String getStringForStripElement(int index) {
        return String.valueOf(index);
    }

    private LinearLayout strip;

    private void addVTO(final View view) {
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();
                Log.d(TAG, "onGlobalLayout: width " + width + " height " + height);
                int totalHeightOfStrip = initAnimation();
                doAnimation(totalHeightOfStrip);
            }
        });
    }

    int currentScroll;
    int totalScroll;

    void doAnimation(int totalScroll) {
        this.totalScroll = totalScroll;
        currentScroll = 0;
        h.post(r);
    }

    Handler h = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (currentScroll < totalScroll) {
                currentScroll += totalScroll / (ANIMATION_DURATION / VIEW_UPDATE_INTERVAL);
                scrollView.smoothScrollTo(0, currentScroll);
                h.postDelayed(r, VIEW_UPDATE_INTERVAL);
            }
        }
    };

    ScrollView scrollView;

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
        strip.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height));
        scrollView.addView(strip);
        scrollView.setOverScrollMode(View.SCROLL_AXIS_NONE);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        scrollView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        this.addView(scrollView);
        Log.d(TAG, "initAnimation: total height " + totalHeight);
        return totalHeight;
    }

}
