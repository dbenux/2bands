package dbenux.test.a2bands.reminders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.List;

import dbenux.test.a2bands.TwoBandsApplication;
import dbenux.test.a2bands.model.Event;

public class RescheduleManager {

    public static void scheduleReminder(@NonNull Context context, @NonNull Event event) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager!=null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 0);

            PendingIntent pendingIntent = createPendingIntent(context, event);
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    public static void removeReminder(@NonNull Context context, @NonNull Event event) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager!=null) {
            alarmManager.cancel(createPendingIntent(context, event));
        }
    }

    public static void rescheduleAll(@NonNull final Context context) {
        (new Thread() {
            @Override
            public void run() {
                List<Event> eventList = TwoBandsApplication.getInstance(context)
                        .getPersistence()
                        .eventDao()
                        .getAll();

                for (Event event : eventList) {
                    scheduleReminder(context, event);
                }
            }
        }).start();
    }

    private static PendingIntent createPendingIntent(@NonNull Context context, @NonNull Event event) {
        long id = event.getId();

        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(ReminderReceiver.EXTRA_EVENT_ID, id);

        return PendingIntent.getBroadcast(context, (int)id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
