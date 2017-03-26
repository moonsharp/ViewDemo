package kk.qisheng.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import kk.qisheng.R;

/**
 * Created by Administrator on 2017/3/16.
 */
public class DrawTextView extends View {
    /**
     * 绘制时控制文本绘制的范围
     */
    Rect mBound = new Rect();
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    String mTitle;
    int mColor;
    float mSize;


    public DrawTextView(Context context) {
        this(context, null);

    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.draw_text_view, defStyleAttr, 0);
        mTitle = ta.getString(R.styleable.draw_text_view_draw_title);
        mColor = ta.getColor(R.styleable.draw_text_view_draw_color, Color.BLUE);
        mSize = ta.getDimension(R.styleable.draw_text_view_draw_size, 20.0f);
        ta.recycle();

        mPaint.setTextSize(mSize);
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
        Log.d("kkqisheng", mTitle);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitle = randomText();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }


        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mColor);
        float x = getWidth() / 2 - mBound.width() / 2;
        float y = getHeight() / 2 + mBound.height() / 2;
        canvas.drawText(mTitle, x, y, mPaint);
    }

    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }

}
