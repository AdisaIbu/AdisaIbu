package mk.com.findsellgive.utills;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Damjan on 09.9.2019
 * Project: find-sell-give
 **/
public class SharedPreferencesManager {
    private static SharedPreferencesManager INSTANCE = null;
    private SharedPreferences preferences;
    private Context context;
    private final String PREFS = "shared_prefs";
    private final String LOCATION = "location_permission";
    private final String LONGITUDE = "longitude";
    private final String LATITUDE = "latitude";

    private SharedPreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPreferencesManager(context);
        }
        return INSTANCE;
    }

    public void setLocationPermissionEnabled(boolean isEnabled) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOCATION, isEnabled).apply();
    }

    public boolean isLocationPermissionEnabled() {
        return preferences.getBoolean(LOCATION, false);
    }

    public void setLocation(double lon, double lat) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LATITUDE, Double.toString(lat));
        editor.putString(LONGITUDE, Double.toString(lon)).apply();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double getLatitude(){
        if (isNumeric(preferences.getString(LATITUDE, ""))) {
            return Double.parseDouble(preferences.getString(LATITUDE, ""));
        } else {
            return 0;
        }
    }

    public double getLongitude(){
        if (isNumeric(preferences.getString(LONGITUDE, ""))) {
            return Double.parseDouble(preferences.getString(LONGITUDE, ""));
        } else {
            return 0;
        }
    }
}
