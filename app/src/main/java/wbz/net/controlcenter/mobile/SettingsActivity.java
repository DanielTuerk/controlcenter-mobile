package wbz.net.controlcenter.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        final EditText txtUrl = findViewById(R.id.txtUrl);

        txtUrl.setText(BrowserUriPref.read(this));

        Button btnApply = findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowserUriPref.save(SettingsActivity.this, txtUrl.getText().toString());

                Intent intent = new Intent(SettingsActivity.this, BrowserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SettingsActivity.this.getApplicationContext().startActivity(intent);
            }
        });
    }

}
