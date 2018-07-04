package dbenux.test.a2bands.reminders;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class ReminderJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        RescheduleManager.rescheduleAll(this);
        jobFinished(jobParameters, false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
