package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/3/19.
 */
public class DrawPath2View extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWidth;
    int mHeight;
    float mStartX;
    float mStartY;
    float mStopX;
    float mStopY;
    float mConX;
    float mConY;

    public DrawPath2View(Context context) {
        this(context, null);
    }

    public DrawPath2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPath2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        initSize();
//        setMeasuredDimension(mWidth, mHeight);
    }

    private void initSize() {
        mStartX = 50;
        mStartY = mHeight / 2;
        mStopX = mWidth - 50;
        mStopY = mStartY;
        mConX = mWidth / 2;
        mConY = mStartY;

        Log.d("kkqisheng", "DrawPathView init: " + mWidth + " * " + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        path.moveTo(mStartX, mStartY);
        path.quadTo(mConX, mConY, mStopX, mStopY);
//        path.moveTo(50, 100);
//        path.quadTo(250, 100, 450, 100);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            mConX = event.getX();
            mConY = event.getY();
            invalidate();
        }
        return true;
    }
}
