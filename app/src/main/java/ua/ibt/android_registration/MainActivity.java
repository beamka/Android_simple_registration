package ua.ibt.android_registration;

import android.content.Intent;
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
    }

    public void goRegistration(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void goLogin(View view){
        if (login()) {
            Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Login or password is incorrect", Toast.LENGTH_LONG).show();
    }

    private boolean login(){
        String str_email = email.getText().toString();
        BigInteger hash_pass = singleton.digest(password.getText().toString());
        //------SQLite------
        User user = dao.findUser(str_email);
            if (str_email.equals(user.getEmail())) {
                if(hash_pass.compareTo(user.getPassword()) == 0){
                    singleton.setCurrentUser(user);
                    return true;
                } else
                    return false;
            }
        return false;
    }
}
