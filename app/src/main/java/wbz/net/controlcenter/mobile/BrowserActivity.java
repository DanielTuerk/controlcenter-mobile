package wbz.net.controlcenter.mobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.webkit.WebView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * An full-screen activity that shows a {@link WebView} for the control center web app.
 */
public class BrowserActivity extends Activity {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_browser);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportMultipleWindows(false);
        final String uri = BrowserUriPref.read(this);
        webView.loadUrl(uri);

        scheduler.scheduleAtFixedRate(new CheckConnectionRunnable(this, uri), 0, 2, TimeUnit.MINUTES);
    }

}
