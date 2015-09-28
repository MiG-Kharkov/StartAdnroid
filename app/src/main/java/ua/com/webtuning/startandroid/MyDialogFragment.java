package ua.com.webtuning.startandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by mig on 28.09.15.
 */
public class MyDialogFragment extends DialogFragment {

    public static MyDialogFragment newInstance() {
        String title = "My Dialog Test Title";

        MyDialogFragment f = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");

        Dialog myDialog = new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(title)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity) getActivity()).okClicked();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity) getActivity()).cancelClicked();
                    }
                }).create();

        return myDialog;
    }
}
