package co.kahra.practice.practiceapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class PracticeFragment extends Fragment {
    protected View rootView;
    OnFragmentAction onFragmentAction;

    // Standard no-arg constructor for fragments.
    public PracticeFragment () {
    }

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onFragmentAction = (OnFragmentAction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentAction.");
        }
    }
}
