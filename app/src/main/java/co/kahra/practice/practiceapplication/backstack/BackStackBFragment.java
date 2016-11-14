package co.kahra.practice.practiceapplication.backstack;

import co.kahra.practice.practiceapplication.R;

public class BackStackBFragment extends BackStackFragment {
    int getLayout() {
        return R.layout.fragment_backstack_b;
    }

    @Override
    protected void setOnClickListeners() {
        setOnClickA();
        setOnClickC();
    }
}
