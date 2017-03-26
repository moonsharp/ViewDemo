package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/3/16.
 */
public class DrawVolumeview extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mCWidth = 16;
    int mPices = 15;
    float mCurrentPice = 0;

    public DrawVolumeview(Context context) {
        this(context, null);
    }

    public DrawVolumeview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawVolumeview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPice == mPices) mCurrentPice = 0;
                mCurrentPice++;
                postInvalidate();
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float sweepAngle = 300 / mPices;
        float splitAngle = 60 / mPices;

        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(mCWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        int centre = getWidth() / 2;
        int radius = getWidth() / 2 - mCWidth / 2;
        RectF rectf = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);


        float bgStartAngle = -90f;
        for (int i = 0; i < mPices; i++) {
            canvas.drawArc(rectf, bgStartAngle, sweepAngle, false, mPaint);
            bgStartAngle = bgStartAngle + sweepAngle + splitAngle;
        }

        mPaint.setColor(Color.RED);
        float volumeStartAngle = -90f;
        for (int i = 0; i < mCurrentPice; i++) {
            canvas.drawArc(rectf, volumeStartAngle, sweepAngle, false, mPaint);
            volumeStartAngle = volumeStartAngle + sweepAngle + splitAngle;
        }
    }

}
