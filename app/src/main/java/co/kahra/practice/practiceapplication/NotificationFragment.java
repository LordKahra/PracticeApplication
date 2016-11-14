package co.kahra.practice.practiceapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NotificationFragment extends PracticeFragment {
    Button testButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        this.rootView = rootView;

        testButton = (Button) rootView.findViewById(R.id.notificationButtonSend);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        return rootView;
    }

    public void sendNotification () {
        // Create the builder.
        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(getActivity());
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("My notification");
        builder.setContentText("Hello, world.");

        // Create the intent.
        Intent resultIntent = new Intent(getActivity(), PracticeActivity.class);

        // Now create a pending intent. What does this stuff mean...?
        PendingIntent resultPendingIntent = PendingIntent.getActivity(getActivity(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the builder's intent.
        builder.setContentIntent(resultPendingIntent);

        // Issue the notification!
        // Set a notification ID.
        int notificationID = 001;

        // Get the NotificationManager service.
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        // Build the notification and issue it.
        notificationManager.notify(notificationID, builder.build());
    }
}
