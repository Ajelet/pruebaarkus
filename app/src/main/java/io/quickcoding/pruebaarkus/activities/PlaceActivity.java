package io.quickcoding.pruebaarkus.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import io.quickcoding.pruebaarkus.R;
import io.quickcoding.pruebaarkus.classes.Place;

public class PlaceActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleMap map;
    private Place place;
    private TextView placeName;
    private RatingBar ratingPlace;
    private TextView addressPlace;
    private TextView distancePlace;
    private ImageView iconPet;
    private TextView isPetFriendly;
    private TextView timePlace;
    private TextView numberPhonePlace;
    private TextView webSitePlace;

    private RelativeLayout containerWebPlace;
    private RelativeLayout containerCallPlace;
    private RelativeLayout containerDirectionsPlace;

    static Integer ACTION_WEB = 1;
    static Integer ACTION_CALL = 2;
    static Integer ACTION_NAVIGATION = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        setUpViews();
        setUpData();
        setUpMap();
    }

    public void setUpViews()
    {
        placeName = findViewById(R.id.placeName);
        ratingPlace = findViewById(R.id.ratingPlace);
        addressPlace = findViewById(R.id.addressPlace);
        distancePlace = findViewById(R.id.distancePlace);
        iconPet = findViewById(R.id.iconPet);
        isPetFriendly = findViewById(R.id.isPetFriendly);
        timePlace = findViewById(R.id.timePlace);
        numberPhonePlace = findViewById(R.id.numberPhonePlace);
        webSitePlace = findViewById(R.id.webSitePlace);

        containerCallPlace = findViewById(R.id.containerCallPlace);
        containerDirectionsPlace = findViewById(R.id.containerDirectionsPlace);
        containerWebPlace = findViewById(R.id.containerWebPlace);

        containerWebPlace.setOnClickListener(this);
        containerWebPlace.setTag(ACTION_WEB);

        containerDirectionsPlace.setOnClickListener(this);
        containerDirectionsPlace.setTag(ACTION_NAVIGATION);

        containerCallPlace.setOnClickListener(this);
        containerCallPlace.setTag(ACTION_CALL);
    }


    public void setUpData()
    {
        Gson gS = new Gson();
        String gsonPlace = getIntent().getStringExtra("place");
        place = gS.fromJson(gsonPlace, Place.class);

        placeName.setText(place.getPlaceName());
        ratingPlace.setRating(place.getRating());
        addressPlace.setText(place.getAddress());
        distancePlace.setText(place.getDistanceString());

        ColorMatrix colorMatrix = new ColorMatrix();

        if (place.isPetFriendly())
        {
            isPetFriendly.setText(R.string.petFriendly);
            Glide.with(this).load(R.drawable.dog_friendly_active).into(iconPet);

            colorMatrix.setSaturation(1);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
            iconPet.setColorFilter(filter);

        }else
        {
            isPetFriendly.setText(R.string.notPetFriendly);

            Glide.with(this).load(R.drawable.dog_friendly_active).into(iconPet);

            colorMatrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
            iconPet.setColorFilter(filter);
        }

        if (place.getDistance()>0.0f)
        {
            float time = 60.0f*place.getDistance()/40.0f;

            String timeString = String.format(Locale.getDefault(),"%.0f", time)+" min drive";

            timePlace.setText(timeString);
        }


        numberPhonePlace.setText(place.getPhoneNumber());

        webSitePlace.setText(place.getSite());



    }

    public void setUpMap()
    {
        SupportMapFragment mapFragment  = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment!=null)
        {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    map = googleMap;


                    LatLng latlng= new LatLng(place.getLatitude(), place.getLongitude());
                    map.addMarker(new MarkerOptions().position(latlng).title(place.getPlaceName()).
                            icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,17));



                }
            });
        }

    }


    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void navigation(double latitud, double longitud, String place)
    {
        String uri ="geo:0,0?q=" + android.net.Uri.encode(String.format(Locale.ENGLISH,"%s@%f,%f", "Go", latitud, longitud), "UTF-8");

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        try
        {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(this, "Por favor instala Google Maps", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onClick(View view) {


        switch ()
    }
}
