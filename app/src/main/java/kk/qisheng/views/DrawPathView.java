package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/3/19.
 */
public class DrawPathView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWaveCount = 26;

    public DrawPathView(Context context) {
        this(context, null);
    }

    public DrawPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
//        Log.d("kkqisheng", "DrawPathView init");
        mPaint.setColor(Color.BLUE);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawJuxing(canvas);
    }

    private void drawJuxing(Canvas canvas) {
        RectF rectf = new RectF(0, 0, getWidth(), getHeight() - 100);
        canvas.drawRect(rectf, mPaint);

        float waveHeight = rectf.width() / mWaveCount;

        for (int i = 0; i < mWaveCount; i++) {
            Path path = new Path();
            float startX = i * waveHeight;
            float startY = rectf.height();
            path.moveTo(startX, startY);

//            Log.d("kkqisheng", startX + " * " + startY);

            float midleX = startX + waveHeight / 2;
            float midleY = rectf.height() + waveHeight;
            path.lineTo(midleX, midleY);

            float endX = startX + waveHeight;
            float endY = rectf.height();
            path.lineTo(endX, endY);

            canvas.drawPath(path,mPaint);

//            invalidate();
        }

    }

    private void drawSanjiao(Canvas canvas) {
        Path path = new Path();
        path.moveTo(300, 50);
        path.lineTo(200, 150);
        path.lineTo(400, 150);
        path.close();
        canvas.drawPath(path, mPaint);
    }

}
