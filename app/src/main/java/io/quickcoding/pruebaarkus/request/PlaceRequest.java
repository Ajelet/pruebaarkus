package io.quickcoding.pruebaarkus.request;

import android.app.Activity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.quickcoding.pruebaarkus.classes.Place;

public class PlaceRequest extends ListenerRequest {

    private Activity activity;

    public PlaceRequest(Activity activity)
    {
        this.activity = activity;
    }



    public void load()
    {
        RequestQueue queue = Volley.newRequestQueue(activity);

        String url = "http://www.mocky.io/v2/5bf3ce193100008900619966";



        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET,url ,null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try
                        {
                                ArrayList<Object> objects = new ArrayList<>();
                                for (int i = 0; i<response.length(); i++)
                                {

                                    JSONObject object = response.getJSONObject(i);
                                    Place place = new Place(object);
                                    objects.add(place);

                                }

                                onDataLoaded("Todo Ok",objects);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            onObjectError("Error en API");

                        } }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        onObjectError("Error Internet");

                    }
                });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonRequest);

    }



}