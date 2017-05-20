package ua.ibt.android_registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;

    private Singleton singleton;
    private daoSQLite dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        singleton = Singleton.getInstance();
        dao = new daoSQLite(getApplicationContext());

        SharedPreferences sPref = getSharedPreferences("SessionData", MODE_PRIVATE);
        if (!"".equals(sPref.getString("currentUser", ""))) {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        }
    }

    public void goRegistration(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void goLogin(View view) {
        if (login()) {
            Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Login or password is incorrect", Toast.LENGTH_LONG).show();
    }

    private boolean login() {
        String str_email = email.getText().toString();
        BigInteger hash_pass = singleton.digest(password.getText().toString());
        //------SQLite------
        User user = dao.findUser(str_email);
        if (user == null)
            return false;
        if (str_email.equals(user.getEmail())) {
            if (hash_pass.compareTo(user.getPassword()) == 0) {
                saveSession(user.getEmail());
                return true;
            } else
                return false;
        }
        return false;
    }

    private void saveSession(String email) {
        SharedPreferences sPref = getSharedPreferences("SessionData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("currentUser", email);
        editor.commit();
    }

}
