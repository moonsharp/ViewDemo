package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/16.
 */
public class DrawCircleview extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mCWidth = 30;
    float mProgress = 0;
    int firstColor = Color.YELLOW;
    int secondColor = Color.RED;

    public DrawCircleview(Context context) {
        this(context, null);
    }

    public DrawCircleview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCircleview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        start();
    }

    private void start() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    if (mProgress >= 360) {
                        mProgress = 0;
                        if(firstColor == Color.YELLOW){
                            firstColor = Color.RED;
                            secondColor = Color.YELLOW;
                        } else {
                            firstColor = Color.YELLOW;
                            secondColor = Color.RED;
                        }
                    }
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mProgress += 12;
                    postInvalidate();
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(firstColor);
        mPaint.setStrokeWidth(mCWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        int centre = getWidth() / 2;
        int radius = getWidth() / 2 - mCWidth / 2;
        RectF rectf = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        canvas.drawCircle(centre, centre, radius, mPaint);

        mPaint.setColor(secondColor);
        canvas.drawArc(rectf, -90f, mProgress, false, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(mCWidth/2);
        mPaint.setStyle(Paint.Style.FILL);
        String text = (int) ((mProgress / 360) * 100) + " %";
        mPaint.setTextSize(50);
        Rect bounds = new Rect(centre - radius, centre - radius, centre + radius, centre + radius);
        mPaint.getTextBounds(text,0,text.length(),bounds);
        float x = getWidth() / 2 - bounds.width() / 2;
        float y = getHeight() / 2 + bounds.height() / 2;
        canvas.drawText(text, x,y, mPaint);
    }

}
