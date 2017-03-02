package com.applapse.hairstylesalon;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SquareFrameLayout extends RelativeLayout {

	public SquareFrameLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SquareFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SquareFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
}
