package com.xdkj.xd.xdtools.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/12/20.
 */

public class CircleView extends View {
    private Paint mPaint;
    private RectF oval;
    private int width;
    private int height;
    private float mStrokeWidth=80;
    private float currentDegree=0;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        oval=new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        width = getWidth();
        height = getHeight();
        Log.e("width", width +"");
        int radius= width /2;
        canvas.drawCircle(width /2, height /2, width /2,mPaint);
        canvas.drawCircle(width /2, height /2, width /2*8/10,mPaint);
        mPaint.setColor(Color.BLUE);
        float left= width / 2 - radius;
        float top= width / 2 - radius;
        float right= width / 2 + radius;
        float bottom= width / 2 + radius;
        Log.e("ssss",left+"..."+top+"..."+right+"..."+bottom);
        oval.set(left, top, right, bottom);//用于定义的圆弧的形状和大小的界限
        //canvas.drawArc(oval, 30, 120, true, mPaint);  //根据进度画圆弧
        drawBall(canvas,currentDegree);
        drawKdLine(canvas);
        drawText(canvas);
    }
    private void drawBall(Canvas canvas, float degree)
    {
        Paint ballPaint = new Paint();
        ballPaint.setColor(Color.RED);
        ballPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        int radius=width/20;
        float cx= (float) (Math.cos(Math.toRadians(degree - 90)) * (width/2-radius))+width/2;
        float cy=(float) (Math.sin(Math.toRadians(degree - 90)) * (width/2-radius))+width/2;
        canvas.drawCircle(cx,cy,radius,ballPaint);
    }
    private void drawKdLine(Canvas canvas){
        Paint mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        RectF mRcf = new RectF(0 + mStrokeWidth / 2, 0 + mStrokeWidth / 2, width - mStrokeWidth / 2,
                height - mStrokeWidth / 2);
        mLinePaint.setColor(Color.GRAY);

        for (int i = 0; i < 40; i++) {
            if (i==0||i==10||i==20||i==30){
                mLinePaint.setStrokeWidth(dpToPx(3));
                canvas.drawLine(width/2, width/10, width/2, width/6, mLinePaint);

            }else {
                mLinePaint.setStrokeWidth(dpToPx(1));
                canvas.drawLine(width/2, width/10, width/2, width / 8, mLinePaint);
            }

            canvas.rotate(9, width/2, width/2);
        }
       // canvas.drawArc(mRcf, -90, 360, false, mLinePaint);
    }
    private void drawText(Canvas canvas){
        Paint mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(50);
        float v = mTextPaint.measureText("1");
        float v2 = mTextPaint.measureText("2");
        float v3 = mTextPaint.measureText("3");
        float v4 = mTextPaint.measureText("4");
        //float v = mTextPaint.measureText("1");
        canvas.drawText("1",width/2-v/2,width/4,mTextPaint);
        canvas.drawText("2",width*3/4,width/2+v2/2,mTextPaint);
        canvas.drawText("3",width/2-v3/2,width*3/4,mTextPaint);
        canvas.drawText("4",width/4,width/2+v4/2,mTextPaint);
    }
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    public void setCurrentDegree(float degree){
        this.currentDegree=degree;
        postInvalidate();
    }

}
