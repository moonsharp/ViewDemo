package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/20.
 */
public class Loading6View extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWidth;
    int mHeight;
    float mBigX;
    float mY;
    float mBigInitR = 40;
    float mBigR = mBigInitR;
    float mSmallR = 30;
    float mContX;
    float mConY;
    float mMaxDistance = 150;
    float mDistance = mMaxDistance;

    boolean mIsAdd;
    int mSpeed = 3;

    public Loading6View(Context context) {
        this(context, null);
    }

    public Loading6View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loading6View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        start();
    }

    private void start() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        initPosition();
        setMeasuredDimension(mWidth, mHeight);
    }

    private void initPosition() {
        mPaint.setColor(Color.YELLOW);
        mBigX = mWidth / 2;
        mY = mHeight / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBigR = mBigInitR + (1f - (Math.abs(mDistance / mMaxDistance))) * 16;
        canvas.drawCircle(mBigX, mY, mBigR, mPaint);

        float mSmallX = mBigX + mDistance;
        canvas.drawCircle(mSmallX, mY, mSmallR, mPaint);

        float uStartX = mBigX;
        float uStartY = mY - mBigR;

        float uEndX = mSmallX;
        float uEndY = mY - mSmallR;


        float dStartX = mSmallX;
        float dStartY = mY + mSmallR;

        float dEndX = mBigX;
        float dEndY = mY + mBigR;

        mContX = mBigX + mDistance / 2;
        mConY = mY;

        Path path = new Path();
        path.moveTo(uStartX, uStartY);
        path.quadTo(mContX, mConY, uEndX, uEndY);
//        canvas.drawPath(path, mPaint);

        path.lineTo(dStartX, dStartY);
        path.quadTo(mContX, mConY, dEndX, dEndY);
        canvas.drawPath(path, mPaint);

        if (mDistance > mMaxDistance) {
            mIsAdd = false;
        } else if (mDistance < -mMaxDistance) {
            mIsAdd = true;
        }

        mDistance = mIsAdd ? mDistance + mSpeed : mDistance - mSpeed;
        postInvalidate();
    }

}
