package dbenux.test.a2bands.events;

import android.os.Bundle;

public class MyEventsActivity extends AbstractEventsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reloadData();
    }

    protected void reloadData() {
        // TODO: read flagged events from database
    }

}
