package com.example.azureapp;

import android.annotation.SuppressLint;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * @author 刘非凡
 * @projectName AzureAPP
 * @date 2021-07-08 20:58
 **/
@SuppressLint("AppCompatCustomView")
public class GradientColorTextView extends TextView {
        //线性渐变
        private LinearGradient mLinearGradient;
        //画笔
        private Paint mPaint;
        //宽度
        private int mViewWidth = 0;
        //文本边框
        private Rect mTextBound = new Rect();

    /**
     * 渐变色文本框
     * @param context
     * @param attrs
     */
        public GradientColorTextView (Context context, AttributeSet attrs) {
            super(context, attrs);
        }

    /**
     * 绘制表格
     * @param canvas
     */
    @Override
        protected void onDraw(Canvas canvas) {
            mViewWidth = getMeasuredWidth();
            mPaint = getPaint();
            String mTipText = getText().toString();
            mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
            //设置颜色
            mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                    new int[]{0xFFFFEABA, 0xFFBE8B49},
                    null, Shader.TileMode.REPEAT);
            mPaint.setShader(mLinearGradient);
            //设置文字位置
            canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 + mTextBound.height() / 2, mPaint);
        }
}
