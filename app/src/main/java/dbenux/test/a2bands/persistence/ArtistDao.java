package dbenux.test.a2bands.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import dbenux.test.a2bands.model.Artist;

@Dao
public interface ArtistDao {

    @Query("SELECT * FROM artist WHERE id = :id")
    Artist getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Artist artist);

}
