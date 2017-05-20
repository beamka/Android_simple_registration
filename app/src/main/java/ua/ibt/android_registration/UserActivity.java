package ua.ibt.android_registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by IRINA on 20.05.2017.
 */

public class UserActivity extends AppCompatActivity {
    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        user = (TextView) findViewById(R.id.user);
        user.setText(getSession());
    }

    public void goLogout(View view){
        delSession();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private String getSession(){
        SharedPreferences sPref = getSharedPreferences("SessionData", MODE_PRIVATE);
        return sPref.getString("currentUser", "");
    }

    private void delSession(){
        SharedPreferences sPref = getSharedPreferences("SessionData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("currentUser" , "");
        editor.commit();
    }
}
