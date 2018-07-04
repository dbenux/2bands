package dbenux.test.a2bands.events;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dbenux.test.a2bands.R;
import dbenux.test.a2bands.TwoBandsApplication;
import dbenux.test.a2bands.api.Api;
import dbenux.test.a2bands.events.view.EventListAdapter;
import dbenux.test.a2bands.model.Event;
import dbenux.test.a2bands.persistence.Persistence;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AbstractEventsActivity
        implements Callback<List<Event>>, TextView.OnEditorActionListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_events, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorites:
                openFavoritesActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // region AbstractEventsActivity implementation

    @Override
    @LayoutRes
    protected int getLayoutResourceId() {
        return R.layout.activity_events_search;
    }

    @Override
    protected void bindSubviews() {
        super.bindSubviews();

        EditText searchEditText = findViewById(R.id.field_search);
        searchEditText.setOnEditorActionListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // endregion

    private void search(String searchQuery) {
        Api.getInstance()
                .getEventsByArtist(searchQuery)
                .enqueue(this);
    }

    private void openFavoritesActivity() {
        Intent favoritesIntent = new Intent(this, MyEventsActivity.class);
        startActivity(favoritesIntent);
    }

    // region Callback<List<Event>> implementation

    @Override
    public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
        if (response.isSuccessful()) {
            final List<Event> result = response.body();

            if (result!=null) {
                (new Thread() {
                    @Override
                    public void run() {
                        Persistence persistence = TwoBandsApplication.getInstance(EventsActivity.this)
                                .getPersistence();

                        for (Event event: result) {
                            persistence.artistDao().insert(event.getPrimaryArtist());
                            persistence.venueDao().insert(event.getVenue());
                        }

                        persistence.eventDao()
                                .insertAll(result.toArray(new Event[0]));

                        for (Event event : result) {
                            event.setFavorited(persistence.eventDao().isFavorited(event.getId()));
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setAdapter(new EventListAdapter(result, EventsActivity.this));
                            }
                        });
                    }
                }).start();
            }

        } else {
            showErrorMessage(response.message());
        }
    }

    @Override
    public void onFailure(Call<List<Event>> call, Throwable t) {
        showErrorMessage(t.getLocalizedMessage());
    }

    // endregion

    // region TextView.OnEditorActionListener implementation

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId== EditorInfo.IME_ACTION_SEARCH) {
            search(textView.getText().toString());
            return true;
        }
        return false;
    }

    // endregion

}
