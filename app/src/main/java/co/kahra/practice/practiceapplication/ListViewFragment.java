package co.kahra.practice.practiceapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListViewFragment extends PracticeFragment {
    ListView listView;
    StringAdapter adapter;
    List<String> strings = new ArrayList<>();

    boolean checkMode = false;
    Set<String> selected = new HashSet<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        strings.add("Test");
        strings.add("String");
        strings.add("Number");
        strings.add("One!");

        this.adapter = new StringAdapter(getActivity().getApplicationContext(), strings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listview, container, false);
        this.rootView = rootView;

        this.listView = (ListView) rootView.findViewById(R.id.listView);
        this.listView.setAdapter(adapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!checkMode) {
                    handleSelectMode(parent, view, position, id);
                } else {
                    handleCheckMode(parent, view, position, id);
                }
            }
        });


        return rootView;
    }

    private void handleSelectMode (AdapterView<?> parent, View view, int position, long id) {

    }

    private void handleCheckMode (AdapterView<?> parent, View view, int position, long id) {
        if(isChecked(position)) {
            selected.remove(listView.getItemAtPosition(position));
        } else {
            selected.add((String) listView.getItemAtPosition(position));
        }
    }

    private boolean isChecked(int position) {
        return selected.contains(listView.getItemAtPosition(position));
    }
}
