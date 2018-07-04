package dbenux.test.a2bands.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import dbenux.test.a2bands.model.Artist;
import dbenux.test.a2bands.model.Event;
import dbenux.test.a2bands.model.Venue;

@Database(entities = {Event.class, Artist.class, Venue.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class Persistence extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract ArtistDao artistDao();
    public abstract VenueDao venueDao();
}
