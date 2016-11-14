package co.kahra.practice.practiceapplication.backstack;

import co.kahra.practice.practiceapplication.R;

public class BackStackAFragment extends BackStackFragment {

    int getLayout() {
        return R.layout.fragment_backstack_a;
    }

    @Override
    protected void setOnClickListeners() {
        setOnClickB();
        setOnClickC();
    }
}
