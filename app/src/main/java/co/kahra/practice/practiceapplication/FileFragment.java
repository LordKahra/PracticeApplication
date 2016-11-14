package co.kahra.practice.practiceapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class FileFragment extends PracticeFragment {
    // PERMISSIONS
    // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    public static final String DIRECTORY_NAME = "cokahrapractice";
    public static final String EXTENSION = ".trn";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_file, container, false);
        this.rootView = rootView;

        if (isExternalStorageWritable()) {
            writeToFile(getUniqueFileName(getActivity().getApplicationContext()), "blargh?");
        }

        return rootView;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir (Context context) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), DIRECTORY_NAME);
        if (!file.mkdirs()) {
            // UH OH. TODO. Throw an error?
        }
        return file;
    }

    public File getUniqueFileName (Context context) {
        File directory = getAlbumStorageDir(context);
        int num = 1;
        String filename = Calendar.MONTH + "_" + Calendar.DAY_OF_MONTH + "_" + Calendar.YEAR + "_" + num + EXTENSION;
        File file = new File(directory, filename);
        while (file.exists()) {
            num++;
            filename = Calendar.MONTH + "_" + Calendar.DAY_OF_MONTH + "_" + Calendar.YEAR + "_" + num + EXTENSION;
            file = new File(directory, filename);
        }

        return file;
    }

    public void writeToFile (File file, String string) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            System.out.println("Writing to " + file.toString());
            fos.write(string.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
