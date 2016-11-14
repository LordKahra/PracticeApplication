package co.kahra.practice.practiceapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CheckRelativeLayout extends RelativeLayout implements Checkable {
    boolean checked = false;
    ImageButton imageButton;

    public CheckRelativeLayout(Context context) {
        super(context);


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


    }

    public CheckRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        this.checked = !checked;
    }
}
