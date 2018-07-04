package dbenux.test.a2bands.reminders;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent!=null && Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            JobInfo.Builder builder = new JobInfo.Builder(0, new ComponentName(context, ReminderJobService.class))
                .setRequiresDeviceIdle(true); // device should be idle

            JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
            if (jobScheduler!=null) {
                jobScheduler.schedule(builder.build());
            }
        }
    }
}
