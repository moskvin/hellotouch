package cz.netcook.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GoalView extends ImageView {
	private static String TAG = "GoalView";

	public GoalView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusable(true);
		setFocusableInTouchMode(true);

	}

	public GoalView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GoalView(Context context) {
		this(context, null);
	}

}
