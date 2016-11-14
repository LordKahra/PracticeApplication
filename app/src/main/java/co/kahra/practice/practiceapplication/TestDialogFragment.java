package co.kahra.practice.practiceapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the builder class.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_testdialog, null);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edittext);

        builder.setView(view);

        builder.setNegativeButton(R.id.dialog_button_one, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel clicked.", Toast.LENGTH_SHORT).show();
                TestDialogFragment.this.dismiss();
            }
        });

        builder.setPositiveButton(R.id.dialog_button_two, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Okay clicked. Key: " + editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
}
