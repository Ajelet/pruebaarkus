package io.quickcoding.pruebaarkus.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

import io.quickcoding.pruebaarkus.R;
import io.quickcoding.pruebaarkus.classes.Place;
import io.quickcoding.pruebaarkus.holders.ViewHolderPlace;

public class PlaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<Place> places = new ArrayList<>();
    private Listener listener;

    public PlaceAdapter(Context context)
    {
        this.context = context;
    }

    public void load(ArrayList<Place> places)
    {
        this.places = places;
        super.notifyDataSetChanged();
    }



    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    public interface Listener
    {
        public void onClick(Place place);
    }

    public void onClickListener(Place place)
    {
        if (listener !=null)
        {
            listener.onClick(place);
        }
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
        Place place = places.get(position);
        holderPlace.container.setOnClickListener(this);
        holderPlace.container.setTag(place);

        Log.d("prueba",place.toString());
        holderPlace.placeName.setText(place.getPlaceName());
        holderPlace.addressPlace.setText(place.getAddress());
        holderPlace.ratingBar.setRating(place.getRating());
        ColorMatrix colorMatrix = new ColorMatrix();

        if (place.isPetFriendly())
        {
            holderPlace.isPetFriendly.setText(R.string.petFriendly);
            Glide.with(context).load(R.drawable.dog_friendly_active).into(holderPlace.iconPet);

            colorMatrix.setSaturation(1);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
            holderPlace.iconPet.setColorFilter(filter);

        }else
        {
            holderPlace.isPetFriendly.setText(R.string.notPetFriendly);

            Glide.with(context).load(R.drawable.dog_friendly_active).into(holderPlace.iconPet);

            colorMatrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
            holderPlace.iconPet.setColorFilter(filter);

        }
        Glide.with(context).load(place.getThumbnail()).placeholder(new ColorDrawable(Color.DKGRAY)).into(holderPlace.thumbnailPlace);


        holderPlace.distancePlace.setText(place.getDistanceString());

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {

        return places.size();

    }


    public void distance(Location location)
    {
        for (int i=0; i<places.size(); i++)
        {
            places.get(i).setDistanceTo(location);
        }

        Collections.sort(places);
        super.notifyDataSetChanged();

    }


    @Override
    public void onClick(View view) {

        Place place =(Place)view.getTag();
        onClickListener(place);

    }
}
