package dbenux.test.a2bands.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import dbenux.test.a2bands.model.Event;

@Dao
public interface EventDao {

    @Query("SELECT * FROM event "
            + "INNER JOIN artist ON event.artist_id = artist.id "
            + "INNER JOIN venue ON venue.latitude = event.venue_latitude AND venue.longitude = event.venue_longitude")
    List<Event> getAll();

    @Query("SELECT * FROM event "
            + "INNER JOIN artist ON event.artist_id = artist.id "
            + "INNER JOIN venue ON venue.latitude = event.venue_latitude AND venue.longitude = event.venue_longitude "
            + "WHERE event.favorited == 1")
    List<Event> getFavorited();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Event... events);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

}
