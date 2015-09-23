package com.help.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

public class expLiner extends ProgressBar{

	
	private String text_progress = "";
	private Paint mPaint;
	
	public expLiner(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initPaint();

		Log.d("expLiner:1", "expLiner:1");
	}

	
	public expLiner(Context context, AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		initPaint();
		Log.d("expLiner:2", "expLiner:2");
	}
	
	public expLiner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs,defStyle);
		// TODO Auto-generated constructor stub
		initPaint();
		Log.d("expLiner:3", "expLiner:3");
	}
	
	
	
	 @Override  
	    public synchronized void setProgress(int progress) {
		 setTextProgress(progress);
		 super.setProgress(progress);
		 super.setSecondaryProgress((progress/this.getMax())*100);
		 Log.d("progress_set", ""+progress);
	    }  
	 @Override  
	 public synchronized void onDraw(Canvas canvas) {
	        // TODO Auto-generated method stub  
	        super.onDraw(canvas);  
	        Rect rect=new Rect();
	        this.mPaint.getTextBounds(this.text_progress, 0, this.text_progress.length(), rect);  
	        int x = (getWidth() / 2) - rect.centerX();  
	        int y = (getHeight() / 2) - rect.centerY();  
	        canvas.drawText(this.text_progress, x, y, this.mPaint);
		 Log.d("draw_painting_success","");
	    }  
	private void initPaint(){
		this.mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(12f);
		this.mPaint.setColor(Color.WHITE);
		Log.d("init_painting_success", "");
		setWillNotDraw(false);
		
	}
	
	private void setTextProgress(int progress){
		this.text_progress = progress+"/"+this.getMax();
	}
	
	
	
}
