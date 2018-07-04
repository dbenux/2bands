package dbenux.test.a2bands.events.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dbenux.test.a2bands.R;
import dbenux.test.a2bands.events.OnCellClickListener;
import dbenux.test.a2bands.model.Event;

public class EventListAdapter extends RecyclerView.Adapter {

    private final List<Event> eventList;
    private final OnCellClickListener cellClickListener;

    public EventListAdapter(@NonNull List<Event> eventList, @Nullable OnCellClickListener cellClickListener) {
        this.eventList = eventList;
        this.cellClickListener = cellClickListener;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cell_event, viewGroup, false);
        return new EventViewHolder(itemView, cellClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Event item = getItem(position);
        ((EventViewHolder)viewHolder).populate(item);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public Event getItem(int position) {
        return eventList.get(position);
    }

}
