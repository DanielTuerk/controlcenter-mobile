package wbz.net.controlcenter.mobile;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class CheckConnectionRunnable implements Runnable {

    private final Activity activity;
    private final String uri;

    CheckConnectionRunnable(Activity activity, String uri) {
        this.activity = activity;
        this.uri = uri;
    }

    public void run() {
        if (!checkOnline(uri)) {
            startBrowser();
        }
    }

    private boolean checkOnline(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                return true;
            }
        } catch (IOException e) {
            Log.e("MYAPP", "connection check error", e);
        }
        return false;
    }

    private void startBrowser() {
        Intent intent = new Intent(activity, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.getApplicationContext().startActivity(intent);
    }
}
