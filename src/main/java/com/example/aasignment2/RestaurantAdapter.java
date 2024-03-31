package com.example.aasignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Restaurant> restaurantList;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurantrow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.bind(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView locationTextView;
        private TextView phoneTextView;
        private TextView descriptionTextView;

        private TextView ratingstxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.resNametxt);
            locationTextView = itemView.findViewById(R.id.locationtxt);
            phoneTextView = itemView.findViewById(R.id.phonetxt);
            descriptionTextView = itemView.findViewById(R.id.desctxt);
            ratingstxt=itemView.findViewById(R.id.ratingsTxt);
        }

        public void bind(Restaurant restaurant) {
            nameTextView.setText(restaurant.getName());
            locationTextView.setText(restaurant.getLoc());
            phoneTextView.setText(restaurant.getPhone());
            descriptionTextView.setText(restaurant.getDesc());
            String str= restaurant.getRatings()+"/5";
            ratingstxt.setText(str);
        }



    }
    public void filterByRating(float minRating) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getRatings() >= minRating) {
                filteredList.add(restaurant);
            }
        }
        setData(filteredList); // Update adapter data with filtered list
        notifyDataSetChanged(); // Notify RecyclerView about the data change
    }

    public void setData(ArrayList<Restaurant> newData) {
        restaurantList = newData;
    }
}
