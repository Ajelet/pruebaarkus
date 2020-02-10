package io.quickcoding.pruebaarkus.classes;

import android.app.Activity;
import android.content.pm.PackageManager;
import java.util.ArrayList;


public class Helpers {

    public static ArrayList<Place> castObjectToArrayPlaces(Object object)
    {
        ArrayList<?> objects = (ArrayList<?>)object;
        ArrayList<Place> places = new ArrayList<>();

        for (int i = 0; i<objects.size(); i++)
        {
            if (objects.get(i) != null)
            {
                places.add((Place)objects.get(i));
            }
        }

        return places;
    }




    public static boolean installedOrNot(Activity activity, String uri) {
        PackageManager pm = activity.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return app_installed;
    }


}
