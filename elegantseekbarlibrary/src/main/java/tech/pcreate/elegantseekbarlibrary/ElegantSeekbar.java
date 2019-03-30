package tech.pcreate.elegantseekbarlibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 * Copyright (c) 2019 Yash Prakash. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under
 * the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */



public class ElegantSeekbar extends View {


    private int mWidth, mHeight;

    private int maxRange;
    private String mSmallerTextContent = "-";

    private static float GAP_BETWEEN_SEEKBAR_AND_RECTANGLE;
    private static float LINE_WIDTH;
    private static float DEFAULT_CIRCLE_BUTTON_RADIUS;
    private static float DEFAULT_CIRCLE_STROKE_WIDTH;
    private static float DEFAULT_TEXT_SIZE;

    private static int DEFAULT_CIRCLE_COLOR ;
    private static int DEFAULT_CIRCLE_BUTTON_COLOR ;
    private static int DEFAULT_FILL_COLOR;
    private static int DEFAULT_HIGHLIGHT_LINE_COLOR;
    private static int DEFAULT_UNFILLED_SEEK_COLOR;
    private static int DEFAULT_TEXT_COLOR;
    private static int SMALLER_TEXT_SIZE;
    private static int DEFAULT_MAX_RANGE = 30;

    private Paint mCirclePaint;
    private Paint mHighlightLinePaint;
    private Paint mLinePaint;
    private Paint mCircleButtonPaint;
    private Paint mBaseCirclePaint;
    private Paint mSeekBarTextPaint;
    private Paint mSmallerTextPaint;

    private float mGapBetweenCircleAndLine;
    private float mLineWidth;
    private float mCircleButtonRadius;
    private float mCircleStrokeWidth;
    private float mSeekBarTextSize;
    private float mSmallerTextSize;

    private int mCircleColor;
    private int mCircleButtonColor;
    private int mLineColor;
    private int mHighlightLineColor;
    private int mBaseCircleColor;
    private int mSeekBarTextColor;
    private int mSmallerTextColor;

    private float mX;
    private float mY;
    private float mRadius;
    private float mCurrentRadian;
    private float mCurrentRadian1;
    private float mPreRadian;
    private float mPreRadian1;
    private boolean mInCircleButton;
    private boolean mInCircleButton1;
    private boolean ismInCircleButton;
    private int mCurrentValue;
    private int mCurrentValue2;

    private OnProgressChangedListener mListener;

    public ElegantSeekbar(Context context) {
        this(context, null);
    }

    public ElegantSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
    }

    public ElegantSeekbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setAttributes(attrs);
    }


    private void setAttributes(AttributeSet attrs) {
        setDefaults();

        //Fixed Attrs for the custom view
        mGapBetweenCircleAndLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, GAP_BETWEEN_SEEKBAR_AND_RECTANGLE,
                getContext().getResources().getDisplayMetrics());

        mLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, LINE_WIDTH, getContext().getResources()
                .getDisplayMetrics());
        mCircleButtonRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_BUTTON_RADIUS, getContext()
                .getResources().getDisplayMetrics());
        mCircleStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CIRCLE_STROKE_WIDTH, getContext()
                .getResources().getDisplayMetrics());

        //Changeable Attrs for the custom view
        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElegantSeekbar);

        maxRange = typedArray.getInteger(R.styleable.ElegantSeekbar_maxRange, DEFAULT_MAX_RANGE);

        mSeekBarTextSize = typedArray.getDimension(R.styleable.ElegantSeekbar_text_size, DEFAULT_TEXT_SIZE);
        mSmallerTextSize = typedArray.getDimension(R.styleable.ElegantSeekbar_smaller_text_size, SMALLER_TEXT_SIZE);
        mSeekBarTextColor = typedArray.getColor(R.styleable.ElegantSeekbar_text_color, DEFAULT_TEXT_COLOR);
        mSmallerTextColor = typedArray.getColor(R.styleable.ElegantSeekbar_smaller_text_color, DEFAULT_TEXT_COLOR);
        mBaseCircleColor = typedArray.getColor(R.styleable.ElegantSeekbar_base_circle_paint, DEFAULT_UNFILLED_SEEK_COLOR);
        mLineColor = typedArray.getColor(R.styleable.ElegantSeekbar_line_color, DEFAULT_FILL_COLOR);
        mCircleColor = typedArray.getColor(R.styleable.ElegantSeekbar_circle_button_1_color, DEFAULT_CIRCLE_COLOR);
        mCircleButtonColor =  typedArray.getColor(R.styleable.ElegantSeekbar_circle_button_2_color, DEFAULT_CIRCLE_BUTTON_COLOR);
        mSmallerTextContent = typedArray.getString(R.styleable.ElegantSeekbar_smallerTextContent);
        mCircleButtonRadius = typedArray.getDimension(R.styleable.ElegantSeekbar_circle_thickness, DEFAULT_CIRCLE_BUTTON_RADIUS);

        typedArray.recycle();

        setAllPaints();
    }

    private void setDefaults() {

        mHeight = 1075;
        mWidth = 1075;

        maxRange = DEFAULT_MAX_RANGE;

        DEFAULT_FILL_COLOR = getContext().getResources().getColor(R.color.default_fill_color);
        DEFAULT_TEXT_COLOR = getContext().getResources().getColor(R.color.default_text_color);
        DEFAULT_UNFILLED_SEEK_COLOR = getContext().getResources().getColor(R.color.default_number_color);
        DEFAULT_HIGHLIGHT_LINE_COLOR = getContext().getResources().getColor(R.color.defaul_highlight_line_color);
        DEFAULT_CIRCLE_BUTTON_COLOR = getContext().getResources().getColor(R.color.default_circle_1_color);
        DEFAULT_CIRCLE_COLOR = getContext().getResources().getColor(R.color.default_circle_2_color);

        mCircleColor = DEFAULT_CIRCLE_COLOR;
        mCircleButtonColor = DEFAULT_CIRCLE_BUTTON_COLOR;
        mLineColor = DEFAULT_FILL_COLOR;
        mHighlightLineColor = DEFAULT_HIGHLIGHT_LINE_COLOR;
        mBaseCircleColor = DEFAULT_UNFILLED_SEEK_COLOR;
        mSeekBarTextColor = DEFAULT_TEXT_COLOR;
        mSmallerTextColor = DEFAULT_TEXT_COLOR;

        GAP_BETWEEN_SEEKBAR_AND_RECTANGLE = 30;
        LINE_WIDTH = 2.5f;
        DEFAULT_CIRCLE_BUTTON_RADIUS = 19;
        DEFAULT_CIRCLE_STROKE_WIDTH = 5;
        DEFAULT_TEXT_SIZE = 27;
        SMALLER_TEXT_SIZE = 15;

    }

    private void setAllPaints() {

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighlightLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBaseCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSeekBarTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSmallerTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);

        mCircleButtonPaint.setColor(mCircleButtonColor);
        mCircleButtonPaint.setAntiAlias(true);
        mCircleButtonPaint.setStyle(Paint.Style.FILL);

        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mCircleButtonRadius * 2 + 7);
        mLinePaint.setStyle(Paint.Style.STROKE);

        mHighlightLinePaint.setColor(mHighlightLineColor);
        mHighlightLinePaint.setStrokeWidth(mLineWidth);

        mBaseCirclePaint.setColor(mBaseCircleColor);
        mBaseCirclePaint.setTextAlign(Paint.Align.CENTER);
        mBaseCirclePaint.setStyle(Paint.Style.STROKE);
        mBaseCirclePaint.setStrokeWidth(mCircleButtonRadius * 2 + 7);

        mSmallerTextPaint.setColor(mSmallerTextColor);
        mSmallerTextPaint.setTextSize(mSmallerTextSize);
        mSmallerTextPaint.setTextAlign(Paint.Align.CENTER);

        mSeekBarTextPaint.setColor(mSeekBarTextColor);
        mSeekBarTextPaint.setTextSize(mSeekBarTextSize);
        mSeekBarTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public ElegantSeekbar setSize(int dp){
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int size = Math.round(dp * (displayMetrics.xdpi) / DisplayMetrics.DENSITY_DEFAULT);
        mWidth = size;
        mHeight = size;

        return this;
    }

    public ElegantSeekbar setTextSizes(int textSize, int smallerTextSize){
        mSeekBarTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, textSize, getContext()
                .getResources().getDisplayMetrics());
        mSmallerTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, smallerTextSize, getContext()
                .getResources().getDisplayMetrics());

        setAllPaints();
        return this;
    }

    public ElegantSeekbar setTextColors(int textColor, int smallerTextColor){
        mSeekBarTextColor = textColor;
        mSmallerTextColor = smallerTextColor;

        setAllPaints();
        return this;
    }

    public ElegantSeekbar setLineColor(int color){
        mLineColor = color;
        setAllPaints();

        return this;
    }

    public ElegantSeekbar setBaseCircleColor(int color){
        mBaseCircleColor = color;
        setAllPaints();

        return this;
    }

    public ElegantSeekbar setCircleButtonColors(int color1, int color2){
        mCircleColor = color1;
        mCircleButtonColor = color2;

        return this;
    }

    public ElegantSeekbar setMaxRange(int maxRange){
        this.maxRange = maxRange;

        return this;
    }

    public ElegantSeekbar setSmallerTextContent(String text){
        this.mSmallerTextContent = text;

        return this;
    }

    public ElegantSeekbar setCircleThickness(int thickness){
        mCircleButtonRadius = thickness;

        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        canvas.drawCircle(mX, mY, mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine, mBaseCirclePaint);
        canvas.save();
        canvas.rotate(-90, mX, mY);

        @SuppressLint("DrawAllocation")
        RectF rect = new RectF(mX - (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine),
                mY - (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine),
                mX + (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine),
                mY + (mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine));

        if (mCurrentRadian1 > mCurrentRadian) {
            canvas.drawArc(rect, (float) Math.toDegrees(mCurrentRadian1),
                    (float) Math.toDegrees(2 * (float) Math.PI) - (float) Math.toDegrees(mCurrentRadian1) +
                            (float) Math.toDegrees(mCurrentRadian), false, mLinePaint);
        } else {
            canvas.drawArc(rect, (float) Math.toDegrees(mCurrentRadian1),
                    (float) Math.toDegrees(mCurrentRadian) -
                            (float) Math.toDegrees(mCurrentRadian1), false, mLinePaint);
        }

        canvas.restore();
        canvas.save();

        canvas.rotate((float) Math.toDegrees(mCurrentRadian), mX, mY);
        canvas.drawCircle(mX, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine
                , 0.01f, mLinePaint);
        canvas.restore();
        canvas.save();

        canvas.rotate((float) Math.toDegrees(mCurrentRadian1), mX, mY);
        canvas.drawCircle(mX, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine,
                0.01f, mLinePaint);
        canvas.restore();
        canvas.save();


        if (ismInCircleButton) {
            canvas.rotate((float) Math.toDegrees(mCurrentRadian), mX, mY);
            canvas.drawCircle(mX, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 + mGapBetweenCircleAndLine
                    , mCircleButtonRadius, mCircleButtonPaint);
            canvas.restore();
            canvas.save();

            canvas.rotate((float) Math.toDegrees(mCurrentRadian1), mX, mY);
            canvas.drawCircle(mX, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 +
                    mGapBetweenCircleAndLine, mCircleButtonRadius, mHighlightLinePaint);
            canvas.restore();
            canvas.save();

        }else {
            canvas.rotate((float) Math.toDegrees(mCurrentRadian1), mX, mY);
            canvas.drawCircle(mX, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 +
                    mGapBetweenCircleAndLine, mCircleButtonRadius, mHighlightLinePaint);
            canvas.restore();
            canvas.save();

            canvas.rotate((float) Math.toDegrees(mCurrentRadian), mX, mY);
            canvas.drawCircle(mX, getMeasuredHeight() / 2 - mRadius + mCircleStrokeWidth / 2 +
                    mGapBetweenCircleAndLine, mCircleButtonRadius, mCircleButtonPaint);
            canvas.restore();
            canvas.save();
        }
        canvas.drawText( String.valueOf(mCurrentValue2) + " - " + String.valueOf(mCurrentValue),
                mX, mY + getTextHeight(mSeekBarTextPaint) / 2, mSeekBarTextPaint);

        if (mSmallerTextContent != null)
            canvas.drawText(mSmallerTextContent, mX + 5, mY + 103 + getTextHeight(mSeekBarTextPaint) , mSmallerTextPaint);

        canvas.restore();
        canvas.save();
        canvas.restore();

        super.onDraw(canvas);
    }

    private float getTextHeight(Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds("1", 0, 1, rect);
        return rect.height();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // If the point in the circle button
                if (circleButton(event.getX(), event.getY()) && isEnabled()) {
                    mInCircleButton = true;
                    ismInCircleButton = false;
                    mPreRadian = getRadian(event.getX(), event.getY());
                } else if (circleButton1(event.getX(), event.getY()) && isEnabled()) {
                    mInCircleButton1 = true;
                    ismInCircleButton = true;
                    mPreRadian1 = getRadian(event.getX(), event.getY());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mInCircleButton && isEnabled()) {
                    float temp = getRadian(event.getX(), event.getY());
                    if (mPreRadian > Math.toRadians(270) && temp < Math.toRadians(90)) {
                        mPreRadian -= 2 * Math.PI;
                    } else if (mPreRadian < Math.toRadians(90) && temp > Math.toRadians(270)) {
                        mPreRadian = (float) (temp + (temp - 2 * Math.PI) - mPreRadian);
                    }
                    mCurrentRadian += (temp - mPreRadian);
                    mPreRadian = temp;
                    if (mCurrentRadian > 2 * Math.PI) {
                        mCurrentRadian -= (float) (2 * Math.PI);
                    }
                    if (mCurrentRadian < 0) {
                        mCurrentRadian += (float) (2 * Math.PI);
                    }
                    mCurrentValue = (int) ((Math.toDegrees(mCurrentRadian) * maxRange) / 360.0);
                    if (String.valueOf(mCurrentValue) != null) mListener.endValue(String.valueOf(mCurrentValue));
                    invalidate();
                } else if (mInCircleButton1 && isEnabled()) {
                    float temp = getRadian(event.getX(), event.getY());
                    if (mPreRadian1 > Math.toRadians(270) && temp < Math.toRadians(90)) {
                        mPreRadian1 -= 2 * Math.PI;
                    } else if (mPreRadian1 < Math.toRadians(90) && temp > Math.toRadians(270)) {
                        mPreRadian1 = (float) (temp + (temp - 2 * Math.PI) - mPreRadian1);
                    }
                    mCurrentRadian1 += (temp - mPreRadian1);
                    mPreRadian1 = temp;
                    if (mCurrentRadian1 > 2 * Math.PI) {
                        mCurrentRadian1 -= (float) (2 * Math.PI);
                    }
                    if (mCurrentRadian1 < 0) {
                        mCurrentRadian1 += (float) (2 * Math.PI);
                    }

                    mCurrentValue2 = (int) ( (Math.toDegrees(mCurrentRadian1) * maxRange) / 360.0) ;
                    mListener.startValue(String.valueOf(mCurrentValue2));
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mInCircleButton && isEnabled()) {
                    mInCircleButton = false;
                } else if (mInCircleButton1 && isEnabled()) {
                    mInCircleButton1 = false;
                }
                break;
        }
        return true;
    }


    private boolean circleButton1(float x, float y) {
        float r = mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine;
        float x2 = (float) (mX + r * Math.sin(mCurrentRadian1));
        float y2 = (float) (mY - r * Math.cos(mCurrentRadian1));
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < mCircleButtonRadius) {
            return true;
        }
        return false;
    }

    private boolean circleButton(float x, float y) {
        float r = mRadius - mCircleStrokeWidth / 2 - mGapBetweenCircleAndLine;
        float x2 = (float) (mX + r * Math.sin(mCurrentRadian));
        float y2 = (float) (mY - r * Math.cos(mCurrentRadian));
        if (Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) < mCircleButtonRadius) {
            return true;
        }
        return false;
    }

    private float getRadian(float x, float y) {
        float alpha = (float) Math.atan((x - mX) / (mY - y));
        if (x > mX && y > mY) {
            // 2
            alpha += Math.PI;
        } else if (x < mX && y > mY) {
            // 3
            alpha += Math.PI;
        } else if (x < mX && y < mY) {
            // 4
            alpha = (float) (2 * Math.PI + alpha);
        }
        return alpha;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.mX = mWidth / 2;
        this.mY = mHeight / 2;
        // Radius
        if (mGapBetweenCircleAndLine + mCircleStrokeWidth >= mCircleButtonRadius) {
            this.mRadius = mWidth / 2 - mCircleStrokeWidth / 2;
        } else {
            this.mRadius = mWidth / 2 - (mCircleButtonRadius - mGapBetweenCircleAndLine -
                    mCircleStrokeWidth / 2);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setOnProgressChangedListener(OnProgressChangedListener listener) {
        if (null != listener) {
            this.mListener = listener;
        }
    }

    public interface OnProgressChangedListener {
        void startValue(String start);

        void endValue(String end);
    }
}