package dbenux.test.a2bands;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dbenux.test.a2bands.persistence.Persistence;

public class TwoBandsApplication extends Application {

    public static TwoBandsApplication getInstance(@NonNull Context context) {
        return (TwoBandsApplication)context.getApplicationContext();
    }

    private Persistence db;

    @Override
    public void onCreate() {
        super.onCreate();

        // region Database init

        db = Room.databaseBuilder(getApplicationContext(), Persistence.class, "database-name")
                .build();

        // endregion
    }

    public Persistence getPersistence() {
        return db;
    }

}
