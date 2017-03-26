package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/3/19.
 */
public class Loading5View extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWidth;
    int mHeight;
    float mCircleStartY;
    float mCircleEndY;
    float mCircleX;
    float mCircleY;
    float mCircleRadius = 30;
    boolean isUp;
    float mSpeed = 20;

    float mLineStartX;
    float mLineStartY;
    float mLineEndX;
    float mLineEndy;
    float mLineConX;
    float mLineConY;
    boolean isContact;
    float a = 1.0f;


    public Loading5View(Context context) {
        this(context, null);
    }

    public Loading5View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loading5View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        start();

    }

    private void start() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }.start();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        initPosition();
//        setMeasuredDimension(mWidth, mHeight);
    }

    private void initPosition() {
        mCircleStartY = mHeight / 3;
        mCircleEndY = mHeight - mCircleRadius * 2 - 20;
        mCircleX = mWidth / 2;
        mCircleY = mCircleStartY;

        mLineStartX = 50;
        mLineEndX = mWidth - 50;
        mLineStartY = mHeight / 3 * 2;
        mLineEndy = mLineStartY;
        mLineConX = mWidth / 2;
        mLineConY = mLineStartY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mCircleY < mCircleStartY) {
            isUp = false;
        } else if (mCircleY > mCircleEndY) {
            isUp = true;
        }

        isContact = mCircleY + mCircleRadius >= mLineStartY;
        a = isContact ? a + 0.5f : 1.0f;

        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);


        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);

        mLineConY = isContact ? mCircleY + mCircleRadius + 20 * a : mLineStartY;
        Path path = new Path();
        path.moveTo(mLineStartX, mLineStartY);
        path.quadTo(mLineConX, mLineConY, mLineEndX, mLineEndy);
        canvas.drawPath(path, mPaint);

        Log.d("kkqisheng", isContact + " * " + mCircleY + " * " + mLineConY);

        mCircleY = isUp ? mCircleY - mSpeed : mCircleY + mSpeed;
    }

}
