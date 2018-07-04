package dbenux.test.a2bands.reminders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import java.text.DateFormat;

import dbenux.test.a2bands.R;
import dbenux.test.a2bands.TwoBandsApplication;
import dbenux.test.a2bands.model.Artist;
import dbenux.test.a2bands.model.Event;
import dbenux.test.a2bands.model.Venue;
import dbenux.test.a2bands.persistence.Persistence;

public class ReminderReceiver extends BroadcastReceiver {

    static final String EXTRA_EVENT_ID = "dbenux.test.a2bands.reminders.ReminderReceiver.Extras.EVENT_ID";

    private static final DateFormat TIME_FORMATTER;

    private static final String NOTIFICATION_CHANNEL_ID = "concerts_channel";

    static {
        TIME_FORMATTER = DateFormat.getTimeInstance(DateFormat.SHORT);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final long eventId = intent.getLongExtra(EXTRA_EVENT_ID, -1);
        if (eventId>=0) {
            (new Thread() {
                @Override
                public void run() {
                    Persistence persistence = TwoBandsApplication.getInstance(context)
                            .getPersistence();

                    Event event = persistence
                            .eventDao()
                            .getById(eventId);

                    if (event!=null) {
                        Artist artist = persistence.artistDao().getById(event.getArtistId());
                        event.setPrimaryArtist(artist);

                        Venue venue = persistence.venueDao().getByLatitudeAndLongitude(event.getVenueLatitude(), event.getVenueLongitude());
                        event.setVenue(venue);

                        showNotification(context, event);
                    }
                }
            }).start();
        }
    }

    private void showNotification(@NonNull Context context, @NonNull Event event) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, context.getString(R.string.notification_channel), NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }

            String artistName = event.getPrimaryArtist().getName();
            String venueName = event.getVenue().getName();
            String dateString = TIME_FORMATTER.format(event.getDate());

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setContentTitle(context.getString(R.string.notification_title, artistName))
                    .setContentText(context.getString(R.string.notification_text, venueName, dateString));

            notificationManager.notify((int)event.getId(), builder.build());
        }
    }

}
