package soaptech.asbestosreporting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class containing helper methods for interacting with the database.
 */
public class DBTools extends SQLiteOpenHelper {

    public DBTools(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(id integer PRIMARY KEY AUTOINCREMENT, name text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            if(i < 10) {
                sqLiteDatabase.execSQL("CREATE TABLE users(id integer PRIMARY KEY AUTOINCREMENT, name text, password text)");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a user into the database.
     * @param user User to get info from
     * @return The ID of the user
     */
    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", user.getName());
        values.put("password", user.getPassword());

        long result = db.insert("users", null, values);
        db.close();

        return result;
    }

    /**
     * Updates a user's information in the database.
     * @param user User to get updated information
     * @return 1 if update is successful, 0 otherwise
     */
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", user.getName());
        values.put("password", user.getPassword());

        int result = db.update("users", values, "id = ?", new String[]{String.valueOf(user.getId())});
        db.close();

        return result;
    }

    /**
     * Deletes a user from the database.
     * @param user User to delete from database
     * @return 1 if delete is successful, 0 otherwise
     */
    public int deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete("users", "id = ?", new String[]{String.valueOf(user.getId())});
        db.close();

        return result;
    }

    /**
     * Query all users in the database.
     * @return Array of all users inside the database
     */
    public User[] queryUsers() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(false, "users", null, null, null, null, null, null, null);
        User[] users = new User[cursor.getCount()];

        if(cursor.moveToFirst()) {
            do {
                users[cursor.getPosition() + 1] = new User(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
            } while(cursor.moveToNext());
        }
        db.close();

        return users;
    }

    /**
     * Query a user with the specified name in the database.
     * @param name Name to search for
     * @return User inside the database with the specified name
     */
    public User queryUserByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(false, "users", new String[]{"id", "password"}, "name = ?", new String[]{name}, null, null, null, null);
        User user = new User(0, name, "");

        if(cursor.moveToFirst()) {
            do {
                user.setId(cursor.getLong(0));
                user.setPassword(cursor.getString(1));
            } while(cursor.moveToNext());
        }
        db.close();

        return user;
    }

    /**
     * Query a user with the specified ID in the database.
     * @param id ID to search for
     * @return User inside the database with the specified ID
     */
    public User queryUserByID(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(false, "users", new String[]{"name", "password"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        User user = new User(id, "", "");

        if(cursor.moveToFirst()) {
            do {
                user.setName(cursor.getString(0));
                user.setPassword(cursor.getString(1));
            } while(cursor.moveToNext());
        }
        db.close();

        return user;
    }
}
