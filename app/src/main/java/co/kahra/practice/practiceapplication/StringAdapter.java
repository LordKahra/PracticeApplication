package co.kahra.practice.practiceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StringAdapter extends ArrayAdapter<String> {
    private static final int RESOURCE = R.layout.object_string_item;
    Context context;

    public StringAdapter(Context context, List<String> strings) {
        super(context, RESOURCE, strings);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(RESOURCE, parent, false);
        }

        TextView string = (TextView) row.findViewById(R.id.string_text);
        string.setText(getItem(position));



        return row;
    }
}
