package edu.fresnostate.mail.getthatcheckedout;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class AlarmReciever3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //Get Id and message from intent.
        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("todo");

        //When notification is tapped, call alarm activity.
        Intent mainIntent = new Intent(context, PillAlarm.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);


        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Prepare Notification
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Time to refill medication!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);

        //Notify
        /*if (Build.VERSION.SDK_INT < 16) {
            myNotificationManager.notify(notificationId, builder.getNotification());
        } else {
            myNotificationManager.notify(notificationId, builder.build());
        }*/
        myNotificationManager.notify(notificationId, builder.build());


    }

    /*@Override
    public static final String NOTIFICATION_CHANNEL_ID = "4565";
    //Notification Channel
    CharSequence channelName = NOTIFICATION_CHANNEL_NAME;
    int importance = NotificationManager.IMPORTANCE_LOW;
    NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);


    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);*/
}


