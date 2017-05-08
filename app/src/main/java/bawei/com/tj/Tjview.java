package bawei.com.tj;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * date:2017/5/7
 * author:周道(leovo)
 * funcation:
 */

public class Tjview extends View implements View.OnClickListener {
    private RotateAnimation rotateAnimation;
    private int width = 360;
    private int height = 360;
    private int padding = 5;
    private Paint mPaint;
    private RectF mRectf;
    private RectF blackHalfRect;
    private RectF whiteHalfRect;
    public Tjview(Context context) {
        this(context,null);
    }
    public Tjview(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public Tjview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initAnim();
        setOnClickListener(this);
    }
    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
    }
    private void initAnim() {
        //以view的中心点为旋转参考点
        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width,height);
        mRectf = new RectF(0,0,width,width);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCirCle(canvas);
        drawHalfCirCle(canvas);
        drawSmallCircle(canvas);
    }

    /**
     * 绘制二个小圆点
     * @param canvas
     */
    private void drawSmallCircle(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width/2,width/4,20,mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width/2,width/4*3,20,mPaint);
    }

    /**
     * 绘制二个半圆 一个黑色 一个白色
     * @param canvas
     */
    private void drawHalfCirCle(Canvas canvas) {
        //画上面黑色半圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        blackHalfRect = new RectF(width/4,0,width/2+width/4,width/2);
        canvas.drawArc(blackHalfRect,270,180,true,mPaint);


        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        whiteHalfRect = new RectF(width/4,width/2,width/2+width/4,width);
        canvas.drawArc(whiteHalfRect,270,-180,true,mPaint);
    }
    /**
     * 画一个简单的圆
     * @param canvas
     */
    private void drawCirCle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(mRectf,270,180,true,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(mRectf,270,-180,true,mPaint);
    }
    @Override
    public void onClick(View view) {
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());//不停顿
        startAnimation(rotateAnimation);
    }
}
