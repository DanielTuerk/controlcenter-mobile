package wbz.net.controlcenter.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

final class BrowserUriPref {

    static String read(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(context.getString(R.string.browser_uri), "http://");
    }

    static void save(Context context, String value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.browser_uri), value);
        editor.apply();
    }
}
