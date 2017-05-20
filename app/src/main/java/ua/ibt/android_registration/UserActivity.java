package ua.ibt.android_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by IRINA on 20.05.2017.
 */

public class UserActivity extends AppCompatActivity {
    private TextView user;

    private Singleton singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        user = (TextView) findViewById(R.id.user);
        singleton = Singleton.getInstance();
        user.setText(singleton.getCurrentUser().getEmail());
    }

    public void goLogout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
