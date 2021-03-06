package com.example.madi.workhard2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madi.workhard2.Models.Movies;
import com.example.madi.workhard2.R;
import com.example.madi.workhard2.interfaces.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MovieViewHolder>  {
    private List<Movies> mData;
    public Adapter(List<Movies> dataset){
        mData = dataset;
    }
    ItemClickListener itemClickListener;

    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView mName;
        private TextView mGenre;
        private ImageView mPoster;
        private TextView mDate;
        private Context context;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.film_name);
            mGenre = itemView.findViewById(R.id.film_genre);
            mPoster = itemView.findViewById(R.id.movie_image);
            mDate = itemView.findViewById(R.id.film_date);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(mData.get(getAdapterPosition()));
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =  LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);
        MovieViewHolder vh = new MovieViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i) {
        onBindMovieHolder(movieViewHolder, i);
    }

    void onBindMovieHolder(@NonNull final MovieViewHolder movieViewHolder, int i){
        movieViewHolder.mName.setText(mData.get(i).getTitle());
        String year[] = mData.get(i).getReleaseDate().split("-");
        movieViewHolder.mDate.setText(year[0]);
        String id = mData.get(i).getId().toString();
        String url = "http://image.tmdb.org/t/p/w780" + mData.get(i).getPosterPath();
        Picasso.
                with(movieViewHolder.context).
                load(url).
                into(movieViewHolder.mPoster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
