package dbenux.test.a2bands.events;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dbenux.test.a2bands.R;
import dbenux.test.a2bands.api.Api;
import dbenux.test.a2bands.events.view.EventListAdapter;
import dbenux.test.a2bands.model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AbstractEventsActivity
        implements Callback<List<Event>>, TextView.OnEditorActionListener {

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

    // region Callback<List<Event>> implementation

    @Override
    public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
        if (response.isSuccessful()) {
            List<Event> result = response.body();
            if (result!=null) {
                setAdapter(new EventListAdapter(result, this));
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
