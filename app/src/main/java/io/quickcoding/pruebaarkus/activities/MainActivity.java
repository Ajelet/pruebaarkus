package io.quickcoding.pruebaarkus.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.google.gson.Gson;

import java.util.ArrayList;

import io.quickcoding.pruebaarkus.R;
import io.quickcoding.pruebaarkus.adapters.PlaceAdapter;
import io.quickcoding.pruebaarkus.classes.Helpers;
import io.quickcoding.pruebaarkus.classes.Place;
import io.quickcoding.pruebaarkus.request.ListenerRequest;
import io.quickcoding.pruebaarkus.request.LocationRequest;
import io.quickcoding.pruebaarkus.request.PlaceRequest;

public class MainActivity extends AppCompatActivity{


    private PlaceAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PlaceRequest placeRequest;
    private LocationRequest locationRequest;
    private long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpViews();
        setUpRequest();


    }


    public void setUpViews()
    {

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setRefreshing(true);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter  = new PlaceAdapter(this);

        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                placeRequest.load();
            }
        });

        adapter.setListener(new PlaceAdapter.Listener() {
            @Override
            public void onClick(Place place) {

                Log.d("Prueba",place.toString());
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Intent intent = new Intent(MainActivity.this,PlaceActivity.class);
                Gson gS = new Gson();
                String gsonPlace= gS.toJson(place);
                intent.putExtra("place",gsonPlace);
                startActivity(intent);



            }
        });


    }


    public void setUpRequest()
    {
        placeRequest = new PlaceRequest(this);

        placeRequest.setListener(new ListenerRequest.Listener() {
            @Override
            public void onObjectError(final String message) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onDataLoaded(String message, final Object object) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        swipeRefreshLayout.setRefreshing(false);
                        ArrayList<Place> objects = Helpers.castObjectToArrayPlaces(object);
                        adapter.load(objects);

                    }
                });
            }
        });

        placeRequest.load();



        locationRequest = new LocationRequest();


        locationRequest.setListener(new ListenerRequest.Listener() {
            @Override
            public void onObjectError(String message) {

            }

            @Override
            public void onDataLoaded(String message, final Object object) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Location location = (Location)object;

                        adapter.distance(location);


                    }
                });
            }
        });


        locationStart(locationRequest);

    }



    private void locationStart(LocationRequest locationRequest) {

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1010);
            checkSelfPermissionLocation(locationRequest);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationRequest);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationRequest);

    }


    public void checkSelfPermissionLocation(LocationRequest locationRequest)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1010);
        } else {
            locationStart(locationRequest);
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1010 && grantResults.length>0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (locationRequest!=null)
                {
                    locationStart(locationRequest);
                }

            }
        }
    }




}
