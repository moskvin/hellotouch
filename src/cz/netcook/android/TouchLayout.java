package cz.netcook.android;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class TouchLayout extends FrameLayout {
	private static String TAG = "TouchLayout";
	private Toast currentToast = null;
			
	public TouchLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TouchLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TouchLayout(Context context) {
		this(context, null);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		final int count = getChildCount();
		for(int index = 0; index < count; index++){
			View view = getChildAt(index);
			if (view instanceof JumpingView){
				view.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams(); 
						switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							Log.d(TAG, "ACTION_DOWN");
							params.height = 60; 
							params.width = 60; 
							v.setLayoutParams(params);
							v.setBackgroundColor(Color.RED);
							break;
						case MotionEvent.ACTION_UP:
							Log.d(TAG, "ACTION_UP");
							params.height = 40; 
							params.width = 40; 
							params.topMargin = (int)event.getRawY() - (v.getHeight()); 
							params.leftMargin = (int)event.getRawX() - (v.getWidth()/2); 
							v.setLayoutParams(params);
							v.setBackgroundColor(Color.WHITE);
							onDragEnd(v);
							break;
						case MotionEvent.ACTION_MOVE:
							Log.d(TAG, "ACTION_MOVE");
							params.topMargin = (int)event.getRawY() - (v.getHeight()); 
							params.leftMargin = (int)event.getRawX() - (v.getWidth()/2);
							v.setLayoutParams(params);
							break;
						}
						return true;
					}
				});
			}
		}
	}

	private void onDragEnd(final View v){
		Log.d(TAG, "DRAG END " + v.getLeft() + "<=" + v.getBottom() + "=>" + v.getRight());
		final int count = getChildCount();
		for(int index = 0; index < count; index++){
			View center = getChildAt(index);
			if (center instanceof GoalView){
				Log.d(TAG, "AREA => " + center.getLeft() + "<=" + center.getTop() + "=>" + center.getRight());
				if ( 	center.getLeft() <= v.getRight()
						&& center.getRight() >= v.getLeft()
						&& center.getTop() <= v.getBottom()
						&& center.getBottom() >= v.getTop()
						){
					notify("Yeah!");
					v.setBackgroundColor(Color.GREEN);
				}
				else {
					notify("No. Please again");
					v.setBackgroundColor(Color.WHITE);
				}
			}
		}
	}

	private void notify(final String msg){
		if (null == currentToast){
			currentToast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
		} else {
			currentToast.cancel();
		}
		currentToast.setText(msg);
		currentToast.show();
	}
}
