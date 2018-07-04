package dbenux.test.a2bands.events;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import dbenux.test.a2bands.R;
import dbenux.test.a2bands.events.view.EventListAdapter;
import dbenux.test.a2bands.model.Event;

abstract class AbstractEventsActivity extends AppCompatActivity
        implements OnCellClickListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        bindSubviews();
    }

    @LayoutRes
    protected int getLayoutResourceId() {
        return R.layout.activity_events;
    }

    protected void bindSubviews() {
        recyclerView = findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected void setAdapter(@NonNull EventListAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    protected EventListAdapter getAdapter() {
        return (EventListAdapter)recyclerView.getAdapter();
    }

    protected void showErrorMessage(@Nullable String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
                .show();
    }

    // region OnCellClickListener implementation

    @Override
    public void onCellClick(int position) {
        Event event = getAdapter().getItem(position);

        String ticketUrl = event.getTicketUrl();
        if (!TextUtils.isEmpty(ticketUrl)) {
            Uri uri = Uri.parse(ticketUrl);

            try {
                Intent ticketIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(ticketIntent);

            } catch (ActivityNotFoundException e) {
                showErrorMessage(getString(R.string.toast_no_activity));
            }
        }
    }

    // endregion

}
