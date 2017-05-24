package soaptech.asbestosreporting;

import android.content.Context;
import android.database.DatabaseErrorHandler;
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

    public DBTools(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
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
}
