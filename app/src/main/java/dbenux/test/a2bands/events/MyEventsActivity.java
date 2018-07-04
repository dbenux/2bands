package dbenux.test.a2bands.events;

import android.os.Bundle;

import java.util.List;

import dbenux.test.a2bands.TwoBandsApplication;
import dbenux.test.a2bands.events.view.EventListAdapter;
import dbenux.test.a2bands.model.Artist;
import dbenux.test.a2bands.model.Event;
import dbenux.test.a2bands.model.Venue;
import dbenux.test.a2bands.persistence.Persistence;

public class MyEventsActivity extends AbstractEventsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reloadData();
    }

    protected void reloadData() {
        (new Thread() {
            @Override
            public void run() {
                Persistence persistence =TwoBandsApplication.getInstance(MyEventsActivity.this)
                        .getPersistence();

                final List<Event> events = persistence.eventDao().getFavorited();

                for (Event event : events) {
                    Artist artist = persistence.artistDao().getById(event.getArtistId());
                    event.setPrimaryArtist(artist);

                    Venue venue = persistence.venueDao().getByLatitudeAndLongitude(event.getVenueLatitude(), event.getVenueLongitude());
                    event.setVenue(venue);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter(new EventListAdapter(events, MyEventsActivity.this));
                    }
                });
            }
        }).start();
    }

}
