package br.com.meencontreaqui.prj_meencontreaqui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Prj_MeEncontreAqui";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "usuariosarray";
    public static final String ID = "_id";
    public static final String USERNAME = "username";
    public static final String SENHA = "senha";
    private static final String DATABASE_CREATE = "create table " +
            TABLE_NAME + " ( " + ID + " integer primary key autoincrement, " +
            USERNAME + " text not null, " + SENHA + " text not null);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(), "Upgrading database from " +
                oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}