package io.quickcoding.pruebaarkus.holders;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import io.quickcoding.pruebaarkus.R;

public class ViewHolderPlace extends RecyclerView.ViewHolder {

    public ImageView thumbnailPlace;
    public TextView placeName;
    public RatingBar ratingBar;
    public TextView distancePlace;
    public ImageView iconPet;
    public TextView isPetFriendly;
    public TextView addressPlace;
    public LinearLayout container;



    public ViewHolderPlace(View itemView){
        super(itemView);
        thumbnailPlace = itemView.findViewById(R.id.thumbnailPlace);
        placeName = itemView.findViewById(R.id.placeName);
        ratingBar = itemView.findViewById(R.id.ratingPlace);
        distancePlace = itemView.findViewById(R.id.distancePlace);
        iconPet = itemView.findViewById(R.id.iconPet);
        isPetFriendly = itemView.findViewById(R.id.isPetFriendly);
        addressPlace = itemView.findViewById(R.id.addressPlace);
        container = itemView.findViewById(R.id.container);




    }
}