package com.demo.weicongli.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.demo.weicongli.utils.R;


/**
 * @author: WeicongLi
 * @time: 2019/1/18 15:37
 * @email: 912220261@qq.com
 * @Function:
 */
public class MyLinearLayout extends LinearLayout {
    private Paint paint;
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setWillNotDraw(false);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(100,100,20,paint);
        canvas.drawCircle(500,600,20,paint);
        canvas.drawCircle(1000,400,20,paint);
    }
}
