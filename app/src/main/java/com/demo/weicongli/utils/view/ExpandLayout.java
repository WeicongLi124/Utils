package com.demo.weicongli.utils.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.weicongli.utils.R;

/**
 * @author: WeicongLi
 * @time: 2018/8/7 10:44
 * @email: 912220261@qq.com
 * @Function:
 */
public class ExpandLayout extends RelativeLayout {
    private final View layoutView = this;
    private TextView keepTv;
    private TextView problemTv;
    private TextView todoTv;
    private TextView commentTv;
    private int mEnd = 0;
    private int mStart = 0;
    private boolean isExpand;
    private long animationDuration;
    private boolean isFirst = true;

    public ExpandLayout(Context context) {
        this(context, null);
    }

    public ExpandLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        isExpand = false;
        animationDuration = 200;
        View v = LayoutInflater.from(getContext()).inflate(R.layout.weekly_expand_layout, this);
        keepTv = v.findViewById(R.id.weekly_keep_tv);
        problemTv = v.findViewById(R.id.weekly_problem_tv);
        todoTv = v.findViewById(R.id.weekly_todo_tv);
        commentTv = v.findViewById(R.id.weekly_comment_tv);
        setViewDimensions();
    }

    /**
     * @param isExpand 初始状态是否折叠
     */
    public void initExpand(boolean isExpand) {
        this.isExpand = isExpand;
        isFirst = true;
        if (mEnd!=0 && isExpand){
            setViewHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }else if (mStart != 0 && !isExpand){
            setViewHeight(mStart);
        }
        //setViewDimensions();
    }

    /**
     * 获取subView的总高度
     * View.post()的runnable对象中的方法会在View的measure、layout等事件后触发
     */
    private void setViewDimensions() {
        if (isFirst) {
            isFirst = false;
            layoutView.post(new Runnable() {
                @Override
                public void run() {
                    //layoutView.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                    //layoutView.measure(0,0);
                    mStart = keepTv.getLineHeight() * 8;
                    mEnd = layoutView.getMeasuredHeight();
                    setViewHeight(isExpand ? mEnd : mStart);
                }
            });
        }
    }

    /**
     * 设置高度
     * @param height
     */
    public void setViewHeight(int height) {
        ViewGroup.LayoutParams params = layoutView.getLayoutParams();
        params.height = height;
        layoutView.requestLayout();
    }

    public void setKeepStr(String keepStr) {
        keepTv.setText(keepStr);
    }

    public void setProblemStr(String problemStr) {
        problemTv.setText(problemStr);
    }

    public void setTodoStr(String todoStr) {
        todoTv.setText(todoStr);
    }

    public void setCommentStr(String commentStr) {
        commentTv.setText(commentStr);
    }

    /**
     * 切换动画实现
     */
    private void animateToggle(long animationDuration) {
        ValueAnimator heightAnimation = isExpand ?
                ValueAnimator.ofFloat(mStart, mEnd) : ValueAnimator.ofFloat(mEnd, mStart);
        heightAnimation.setDuration(animationDuration / 2);
        heightAnimation.setStartDelay(animationDuration / 2);

        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) (float) animation.getAnimatedValue();
                if (value == mEnd){
                    setViewHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }else {
                    setViewHeight(value);
                }
            }
        });
        heightAnimation.start();
    }

    public boolean isExpand() {
        return isExpand;
    }

    /**
     * 折叠view
     */
    public void collapse() {
        isExpand = false;
        animateToggle(animationDuration);
    }

    /**
     * 展开view
     */
    public void expand() {
        isExpand = true;
        animateToggle(animationDuration);
    }

    public void toggleExpand() {
        if (isExpand) {
            collapse();
        } else {
            expand();
        }
    }
}