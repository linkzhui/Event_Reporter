package com.example.raymon.eventreporter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raymon on 9/21/17.
 */


public class EventListAdapter extends BaseAdapter {
    private Context context;
    private List<Event> eventList;
    private DatabaseReference databaseReference;
    private LayoutInflater inflater;

    public EventListAdapter(Context context) {
        this.context = context;
        eventList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public EventListAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Event getItem(int position) {
        return eventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView title;
        TextView location;
        TextView description;
        TextView time;
        ImageView imgview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            ViewHolder viewHolder = new ViewHolder();
            rowView = inflater.inflate(R.layout.event_list_item, parent, false);
            viewHolder.title = (TextView) rowView.findViewById(R.id.event_item_title);
            viewHolder.location = (TextView) rowView.findViewById(R.id.event_item_location);
            viewHolder.description = (TextView) rowView.findViewById(R.id.event_item_description);
            viewHolder.time = (TextView) rowView.findViewById(R.id.event_item_time);
            viewHolder.imgview = (ImageView) rowView.findViewById(R.id.event_item_img);
            rowView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        final Event event = eventList.get(position);

        holder.title.setText(event.getTitle());
        String[] locations = event.getLocation().split(",");
        holder.location.setText(locations[1] + "," + locations[2]);
        holder.description.setText(event.getDescription());
        holder.time.setText(Utilities.timeTransformer(event.getTime()));
        if (event.getImgUri() != null) {
            final String url = event.getImgUri();
            holder.imgview.setVisibility(View.VISIBLE);
            new AsyncTask<Void, Void, Bitmap>(){
                @Override
                protected Bitmap doInBackground(Void... params) {
                    return Utilities.getBitmapFromURL(url);
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    holder.imgview.setImageBitmap(bitmap);
                }
            }.execute();
        }

        return rowView;
    }
}
