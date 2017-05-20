package ua.ibt.android_registration;

import java.math.BigInteger;

/**
 * Created by IRINA on 19.05.2017.
 */

public class User {
    String email;
    BigInteger password;

    public User(){

    }

    public User(String email, BigInteger password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getPassword() {
        return password;
    }

    public void setPassword(BigInteger password) {
        this.password = password;
    }
}
