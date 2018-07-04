package dbenux.test.a2bands.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import dbenux.test.a2bands.model.Venue;

@Dao
public interface VenueDao {

    @Query("SELECT * FROM venue WHERE latitude = :latitude AND longitude = :longitude")
    Venue getByLatitudeAndLongitude(double latitude, double longitude);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Venue venue);

}
