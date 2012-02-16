package cz.netcook.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class JumpingView extends ImageView {
	private static String TAG = "JumpingView";

	public JumpingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	public JumpingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public JumpingView(Context context) {
		this(context, null);
	}

}
