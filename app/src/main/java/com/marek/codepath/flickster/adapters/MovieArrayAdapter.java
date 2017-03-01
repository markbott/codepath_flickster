package com.marek.codepath.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marek.codepath.flickster.R;
import com.marek.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import static com.marek.codepath.flickster.R.id.tvOverview;
import static com.marek.codepath.flickster.R.id.tvTitle;

/**
 * Created by marek on 2/27/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    private static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvOverview;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         Movie movie = getItem(position);

        ViewHolder viewHolder;

        // check for reuse
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);;
            viewHolder.tvOverview = (TextView) convertView.findViewById(tvOverview);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder )convertView.getTag();
        }

        // clear in case of reuse
        viewHolder.ivImage.setImageResource(0);

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        String imgPath = (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                movie.getPosterPath() : movie.getBackdropPath();
        Picasso.with(getContext())
                .load(imgPath)
                .placeholder(android.R.drawable.spinner_background)
                .error(android.R.drawable.stat_notify_error)
                .into(viewHolder.ivImage);

        return convertView;
    }
}
