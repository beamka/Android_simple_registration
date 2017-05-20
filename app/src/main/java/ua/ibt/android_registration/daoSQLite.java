package ua.ibt.android_registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigInteger;

/**
 * Created by IRINA on 20.05.2017.
 */

public class daoSQLite {
    private DBHelper dbHelper;

    public daoSQLite(Context context){
        dbHelper = new DBHelper(context);
    }

    public void saveUser(User user){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.KEY_EMAIL, user.getEmail());
        contentValues.put(dbHelper.KEY_PASS, user.getPassword().toString());
        database.insert(dbHelper.TABLE_NAME, null, contentValues);
    }

    public User findUser(String email){
        String selection = dbHelper.KEY_EMAIL + " = ?";
        String[] selectionArgs = new String[]{email};
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        User user = null;
        Cursor cursor = database.query(dbHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                user = new User();
                user.setEmail(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_EMAIL)));
                user.setPassword(new BigInteger(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_PASS))));
            }
            cursor.close();
        }
        return user;
    }

}
