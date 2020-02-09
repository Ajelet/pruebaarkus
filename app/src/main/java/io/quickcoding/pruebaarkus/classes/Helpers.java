package io.quickcoding.pruebaarkus.classes;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Helpers {

    public static ArrayList<Object> castObjectToArray(Object object)
    {
        ArrayList<?> objects = (ArrayList<?>)object;
        ArrayList<Object> objs = new ArrayList<>();

        for (int i = 0; i<objects.size(); i++)
        {
            if (objects.get(i) != null)
            {
                objs.add(objects.get(i));
            }
        }

        return objs;
    }


    public static HashMap<String,Object> castObjectToMap(Object object)
    {
        HashMap<?,?> objects = (HashMap<?,?>)object;
        HashMap<String,Object> objs = new HashMap<>();

        for(Map.Entry<?, ?> entry : objects.entrySet()) {
            String key = (String) entry.getKey();
            Object hObject = entry.getValue();
            objs.put(key,hObject);
        }

        return objs;
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


    public static long stringDateToMilliseconds(String date, SimpleDateFormat sdf)
    {
        try
        {
            Date mDate = sdf.parse(date);
            return  mDate.getTime();
        }
        catch (ParseException e)
        {
            Log.e("ERROR",e.toString());
        }

        return 0;
    }

    public static void hideKeyBoard(Context context, View view) {
        try {
            InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (keyboard != null)
            {
                keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
