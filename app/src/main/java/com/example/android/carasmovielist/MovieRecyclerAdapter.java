package com.example.android.carasmovielist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private List<MovieFact> movieFacts;

    public MovieRecyclerAdapter(List<ListItem> listItems,List<MovieFact> movieFacts, Context context){
        this.listItems = listItems;
        this.movieFacts = movieFacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ListItem listItem = listItems.get(position);

        holder.titleText.setText(listItem.getTheTitle());
        holder.dateText.setText(listItem.getTheDate());
        holder.locationText.setText(listItem.getTheLocation());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on item");

                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("title", listItems.get(position).getTheTitle());
                intent.putExtra("date", listItems.get(position).getTheDate());
                intent.putExtra("location", listItems.get(position).getTheLocation());
                intent.putExtra("actor1", movieFacts.get(position).getMovieActor1());
                intent.putExtra("actor2", movieFacts.get(position).getMovieActor2());
                intent.putExtra("actor3", movieFacts.get(position).getMovieActor3());
                intent.putExtra("director", movieFacts.get(position).getMovieDirector());
                intent.putExtra("writer", movieFacts.get(position).getMovieWriter());
                intent.putExtra("prod_company", movieFacts.get(position).getMovieProd_company());
                intent.putExtra("distributor", movieFacts.get(position).getMovieDistributor());
                intent.putExtra("fun_fact", movieFacts.get(position).getMovieFun_fact());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleText;
        public TextView dateText;
        public TextView locationText;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.titleText);
            dateText =  itemView.findViewById(R.id.dateText);
            locationText = itemView.findViewById(R.id.locationText);
            card = itemView.findViewById(R.id.card);
        }
    }
}
