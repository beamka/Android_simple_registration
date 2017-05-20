package ua.ibt.android_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by IRINA on 19.05.2017.
 */

public class RegistrationActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText conf_password;

    private Singleton singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        conf_password = (EditText) findViewById(R.id.confpass);
        singleton = Singleton.getInstance();
    }

    public void setRegistration(View view) {
        if (addNewUser()) {
            Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goLogout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean addNewUser() {
        List<User> users = singleton.getUsers();
        String str_email = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (str_email.matches(emailPattern)) {
            if (str_email.length() < 6) {
                Toast.makeText(getApplicationContext(), "Email must be longer than 6 symbol", Toast.LENGTH_LONG).show();
                return false;
            }
        } else
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_LONG).show();
        String str_pass = password.getText().toString();
        if (str_pass.length() < 4) {
            Toast.makeText(getApplicationContext(), "Password must be longer than 4 symbol", Toast.LENGTH_LONG).show();
            return false;
        }
        String str_conf_password = conf_password.getText().toString();
        for (User user : users)
            if (str_email.equals(user.getEmail())) {
                Toast.makeText(getApplicationContext(), "This email was exist!", Toast.LENGTH_LONG).show();
                return false;
            }
        if (str_pass.equals(str_conf_password)) {
            User newUser = new User(email.getText().toString(), singleton.digest(password.getText().toString()));
            singleton.addUser(newUser);
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
