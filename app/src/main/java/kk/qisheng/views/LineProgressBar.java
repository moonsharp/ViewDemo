package kk.qisheng.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2017/3/18.
 */
public class LineProgressBar extends ProgressBar {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float mSpace = 10;

    public LineProgressBar(Context context) {
        this(context, null);
    }

    public LineProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("kkqisheng","LineProgressBar init");
        mPaint.setTextSize(20);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        //画笔平移到指定paddingLeft， getHeight() / 2位置，注意以后坐标都为以此为0，0
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        mPaint.setColor(Color.BLUE);
        float radio = getProgress() * 1.0f / getMax();
        float progressPosX = (int) ((getWidth() - mSpace * 2) * radio);
        String text = getProgress() + "%";
        //拿到字体的宽度和高度
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent()) / 2;

        boolean noNeedBg = false;

        //如果到达最后，则未到达的进度条不需要绘制
        if (progressPosX + mSpace + textWidth >= getWidth()) {
            progressPosX = getWidth() - textWidth - mSpace;
            noNeedBg = true;
        }

        canvas.drawLine(0, 0, progressPosX, 0, mPaint);
        canvas.drawText(text, progressPosX + mSpace, -textHeight, mPaint);

        if (!noNeedBg) {
            mPaint.setColor(Color.GRAY);
            float startX = progressPosX + textWidth + mSpace * 2;
            canvas.drawLine(startX, 0, getWidth(), 0, mPaint);
        }

        canvas.restore();

    }

}
