package dbenux.test.a2bands.events.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.DateFormat;

import dbenux.test.a2bands.R;
import dbenux.test.a2bands.events.OnCellClickListener;
import dbenux.test.a2bands.model.Artist;
import dbenux.test.a2bands.model.Event;
import dbenux.test.a2bands.model.Venue;

class EventViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private static final DateFormat DATE_FORMATTER;

    static {
        DATE_FORMATTER = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    }

    private final WeakReference<OnCellClickListener> cellClickListenerWeakReference;

    private final TextView artistTextView;
    private final TextView eventTitleTextView;
    private final TextView venueTextView;
    private final TextView dateTextView;

    EventViewHolder(@NonNull View itemView, @Nullable OnCellClickListener cellClickListener) {
        super(itemView);

        artistTextView = itemView.findViewById(android.R.id.text1);
        eventTitleTextView = itemView.findViewById(android.R.id.text2);
        venueTextView = itemView.findViewById(R.id.text_venue);
        dateTextView = itemView.findViewById(R.id.text_date);

        cellClickListenerWeakReference = new WeakReference<>(cellClickListener);
        itemView.setOnClickListener(this);
    }

    void populate(Event event) {
        Artist artist = event.getPrimaryArtist();
        artistTextView.setText(artist.getName());

        eventTitleTextView.setText(event.getTitle());

        Venue venue = event.getVenue();
        venueTextView.setText(venue.getName());

        dateTextView.setText(DATE_FORMATTER.format(event.getDate()));
    }

    // region View.OnClickListener implementation

    @Override
    public void onClick(View view) {
        OnCellClickListener listener = cellClickListenerWeakReference.get();
        if (listener!=null) {
            listener.onCellClick(getAdapterPosition());
        }
    }

    // endregion

}
