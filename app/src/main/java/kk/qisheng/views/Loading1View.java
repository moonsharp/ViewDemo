package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/18.
 */
public class Loading1View extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWidth;
    int mHeight;
    float mMaxOffset = 120;
    float mOffset;
    float radius = 30;
    float mDegrees = 0;


    public Loading1View(Context context) {
        this(context, null);
    }

    public Loading1View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loading1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mWidth = getWidth();
        mHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
//        Log.d("kkqisheng", "mDegrees: " + mDegrees);
        canvas.rotate(mDegrees += 3, mWidth / 2, mHeight / 2);
        float percent = mDegrees / 360;
        if (percent < 0.5f) {
            mOffset = mMaxOffset * percent;
        } else {
            mOffset = mMaxOffset * (1 - percent);
        }

        mPaint.setColor(Color.BLUE);
        float cx1 = mWidth / 2;
        float cy1 = mHeight / 2 - mOffset;
        canvas.drawCircle(cx1, cy1, radius, mPaint);

        mPaint.setColor(Color.GREEN);
        float cx2 = mWidth / 2;
        float cy2 = mHeight / 2 + mOffset;
        canvas.drawCircle(cx2, cy2, radius, mPaint);

        if (mDegrees >= 360) {
            mDegrees = 0;
        }
        invalidate();

    }


}
