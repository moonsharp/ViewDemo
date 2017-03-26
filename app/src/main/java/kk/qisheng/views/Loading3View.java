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
public class Loading3View extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWidth;
    int mHeight;
    float mMaxOffset = 100;
    float mOffset = mMaxOffset;
    float radius = 30;
    float mDegrees = 0;
    int mTimes;
    int[] mColors = new int[]{Color.BLUE, Color.GREEN, Color.RED};


    public Loading3View(Context context) {
        this(context, null);
    }

    public Loading3View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loading3View(Context context, AttributeSet attrs, int defStyleAttr) {
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
        if (percent <= 1) {
            mOffset = mMaxOffset * (1 - percent);
        } else {
            mOffset = mMaxOffset * (percent - 1);
        }

        if (mDegrees >= 720) {
            mDegrees = 0;
//            int temp = mColors[0];
//            mColors[0] = mColors[1];
//            mColors[1] = mColors[2];
//            mColors[2] = temp;
        }

        drawCircle(canvas);
        invalidate();
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setColor(mColors[0]);
        float cx1 = mWidth / 2;
        float cy1 = mHeight / 2 - mOffset;
        canvas.drawCircle(cx1, cy1, radius, mPaint);


        mPaint.setColor(mColors[1]);
        float cx2 = mWidth / 2 - mOffset;
        float cy2 = mHeight / 2 + mOffset;
        canvas.drawCircle(cx2, cy2, radius, mPaint);


        mPaint.setColor(mColors[2]);
        float cx3 = mWidth / 2 + mOffset;
        float cy3 = mHeight / 2 + mOffset;
        canvas.drawCircle(cx3, cy3, radius, mPaint);
    }


}
