package dbenux.test.a2bands.events;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import dbenux.test.a2bands.R;

abstract class AbstractEventsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        bindSubviews();

        reloadData();
    }

    protected void bindSubviews() {
        recyclerView = findViewById(android.R.id.list);
    }

    protected abstract void reloadData();

    // TODO: populate adapter with reloadData results

}
