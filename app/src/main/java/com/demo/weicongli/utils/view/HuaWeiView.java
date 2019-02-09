package com.demo.weicongli.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


/**
 * @author: WeicongLi
 * @time: 2019/1/22 15:32
 * @email: 912220261@qq.com
 * @Function:
 */
public class HuaWeiView extends View {

    private Paint paint;
    private RectF oval;
    private int len;
    private int radius;
    private float sweepAngle = 300;
    private float startAngle = 120;
    private float endAngle = 300;
    private float targetAngle = 300;
    private int red;
    private int green;
    private int score;
    private OnAngleColorListener onAngleColorListener;


    public HuaWeiView(Context context) {
        super(context);
    }

    public HuaWeiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HuaWeiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        score = (int) (targetAngle / sweepAngle * 100);
    }

    public interface OnAngleColorListener {
        void colorListener(int red, int green);
    }

    public void setOnAngleColorListener(OnAngleColorListener onAngleColorListener) {
        this.onAngleColorListener = onAngleColorListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HuaWeiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //以最小值为正方形的长
        len = Math.min(width, height);
        radius = len / 2;
        oval = new RectF(0, 0, len, len);
        //设置测量高度和宽度
        setMeasuredDimension(len, len);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(oval, startAngle, endAngle, false, paint);
        drawViewLine(canvas);
        drawText(canvas);
    }

    /**
     * 画刻度线
     *
     * @param canvas
     */
    private void drawViewLine(Canvas canvas) {
        //保存之前的内容
        canvas.save();
        //移动canvas
        canvas.translate(radius, radius);
        //旋转坐标系
        canvas.rotate(30);
        Paint linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(2);
        linePaint.setAntiAlias(true);
        //确定每次旋转的角度
        float rotateAngle = sweepAngle / 99;
        //绘制有色部分的画笔
        Paint targetLinePaint = new Paint();
        targetLinePaint.setColor(Color.GREEN);
        targetLinePaint.setStrokeWidth(2);
        targetLinePaint.setAntiAlias(true);
        float hasDraw = 0;
        for (int i = 0; i < 100; i++) {
            if (hasDraw <= targetAngle && targetAngle != 0) {//需要绘制有色部分的时候
                //计算已经绘制的比例
                float percent = hasDraw / sweepAngle;
                red = 255 - (int) (255 * percent);
                green = (int) (255 * percent);
                targetLinePaint.setARGB(255, red, green, 0);
                if (onAngleColorListener != null) onAngleColorListener.colorListener(red, green);
                //画一条刻度线
                canvas.drawLine(0, radius, 0, radius - 40, targetLinePaint);
            } else {//不需要绘制有色部分
                //画一条刻度线
                canvas.drawLine(0, radius, 0, radius - 40, linePaint);
            }
            //累计绘制过的部分
            hasDraw += rotateAngle;
            //旋转
            canvas.rotate(rotateAngle);
        }
        //操作完成后恢复之前的内容
        canvas.restore();
    }

    /**
     * 文本绘制
     */
    private void drawText(Canvas canvas) {
        //画小圆
        Paint smallPaint = new Paint();
        smallPaint.setARGB(100, red, green, 0);
        smallPaint.setAntiAlias(true);
        //画小圆指定圆心坐标，半径，画笔
        int smallRadius = radius - 60;
        canvas.drawCircle(radius, radius, smallRadius, smallPaint);
        //绘制文本
        Paint textPaint = new Paint();
        //设置文本居中
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(smallRadius / 2);
        //绘制
        canvas.drawText("" + score, radius, radius, textPaint);
        //绘制分，在分数右上方
        textPaint.setTextSize(smallRadius / 6);
        canvas.drawText("分", radius + smallRadius / 2, radius - smallRadius / 4, textPaint);
        //绘制点击优化在分数的下方
        textPaint.setTextSize(smallRadius / 6);
        canvas.drawText("点击优化", radius, radius + smallRadius / 2, textPaint);
    }

    private boolean isRunning;
    private int state = 1;

    public void changeAngle(final float trueAngle) {
        if (isRunning) {//如果在动直接返回
            return;
        }
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (state) {
                    case 1://后退状态
                        isRunning = true;
                        targetAngle -= 3;
                        if (targetAngle <= 0) {//如果回退到0
                            targetAngle = 0;
                            //改为前进
                            state = 2;
                        }
                        break;
                    case 2://前进状态
                        targetAngle += 3;
                        if (targetAngle >= trueAngle) {//如果增加到指定角度
                            targetAngle = trueAngle;
                            //改为回退状态
                            state = 1;
                            isRunning = false;
                            //结束本次运动
                            timer.cancel();
                        }
                        break;
                }
                score = (int) (targetAngle / sweepAngle * 100);
                postInvalidate();
            }
        }, 500, 30);
    }
}
