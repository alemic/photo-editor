package com.kt.mikky.motto.pick.effects.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CropImageView extends ImageView {

	public CropImageView(Context context) {
		super(context);
	}
	
	public CropImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public CropImageView(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs,defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = width;
			if (heightMode == MeasureSpec.AT_MOST) {
				height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));				
			}
			setMeasuredDimension(width, height);
		}else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
		
	}

}
