package io.quickcoding.pruebaarkus.request;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class LocationRequest extends ListenerRequest implements LocationListener {


    @Override
    public void onLocationChanged(Location location) {

        onDataLoaded("onLocationChanged",location);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d("prueba","onStatusChanged "+s);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d("prueba","onProviderEnabled "+s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d("prueba","onProviderDisabled "+s);
    }

}
