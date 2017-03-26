package kk.qisheng.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import kk.qisheng.R;

/**
 * Created by Administrator on 2017/3/18.
 */
public class DrawLineView extends View {
    int mMaxLines = 10;
    int mLineW = 18;
    int currentLine = 0;
    int mPicW = 250;
    Bitmap mVolumeBitmap;

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DrawLineView(Context context) {
        this(context, null);
    }

    public DrawLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.draw_line_view, defStyleAttr, 0);
        int resourceId = ta.getResourceId(R.styleable.draw_line_view_volume_pic, 0);
        mVolumeBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        ta.recycle();


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLine == mMaxLines) currentLine = 0;
                currentLine++;
                postInvalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();

        drawVolumePic(canvas, mPaint);

        float linePiceH = getHeight() / mMaxLines - 5;
        float lineSpace = (getWidth() - 100 - mLineW * mMaxLines) / mMaxLines;

        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(mLineW);
        float startX = mPicW;
        float startY = getHeight();
        for (int i = 0; i < mMaxLines; i++) {
            float stopY = getHeight() - linePiceH * (i + 1);
            canvas.drawLine(startX, startY, startX, stopY, mPaint);
            startX = startX + lineSpace;
        }

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(mLineW);
        float volumeStartX = mPicW;
        for (int i = 0; i < currentLine; i++) {
            float volumeStopY = getHeight() - linePiceH * (i + 1);
            canvas.drawLine(volumeStartX, startY, volumeStartX, volumeStopY, mPaint);
            volumeStartX = volumeStartX + lineSpace;
        }

    }

    private void drawVolumePic(Canvas canvas, Paint mPaint) {
        Rect rect = new Rect(0, getHeight() - 150, 150, getHeight());
        canvas.drawBitmap(mVolumeBitmap, null, rect, mPaint);
    }

}
