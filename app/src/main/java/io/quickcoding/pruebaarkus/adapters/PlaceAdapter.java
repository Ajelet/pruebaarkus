package io.quickcoding.pruebaarkus.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.quickcoding.pruebaarkus.R;
import io.quickcoding.pruebaarkus.classes.Place;
import io.quickcoding.pruebaarkus.holders.ViewHolderPlace;

public class PlaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<Object> objects = new ArrayList<>();

    public PlaceAdapter(Context context)
    {
        this.context = context;
    }

    public void load(ArrayList<Object> objects)
    {
        this.objects = objects;
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new ViewHolderPlace(view);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderPlace holderPlace = (ViewHolderPlace)holder;
        Place place = (Place) objects.get(position);

        Log.d("prueba",place.toString());
        holderPlace.placeName.setText(place.getPlaceName());
        holderPlace.addressPlace.setText(place.getAddress());
        holderPlace.ratingBar.setRating(place.getRating());
        if (place.isPetFriendly())
        {
            holderPlace.isPetFriendly.setText(R.string.petFriendly);
        }else
        {
            holderPlace.isPetFriendly.setText(R.string.notPetFriendly);

        }
        Glide.with(context).load(place.getThumbnail()).placeholder(new ColorDrawable(Color.DKGRAY)).into(holderPlace.thumbnailPlace);


    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {

        return objects.size();

    }




}
