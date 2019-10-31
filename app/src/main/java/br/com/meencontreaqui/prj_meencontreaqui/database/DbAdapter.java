package br.com.meencontreaqui.prj_meencontreaqui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.meencontreaqui.prj_meencontreaqui.entidades.Usuarios;

public class DbAdapter {
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = {DbHelper.ID, DbHelper.USERNAME,
            DbHelper.SENHA};

    public DbAdapter (Context context) {
        dbHelper = new DbHelper(context);
    }

    public Usuarios createUsuarios (String username, String senha) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.USERNAME, username);
        values.put(DbHelper.SENHA, senha);
        database = dbHelper.getWritableDatabase();
        long insertId = database.insert(DbHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns, DbHelper.ID +
                " = " +insertId, null, null, null, null);
        cursor.moveToFirst();
        return cursorToUsuarios(cursor);
    }

    public void removeUsuarios (int idUsuarios) {
        database.delete(DbHelper.TABLE_NAME, DbHelper.ID + " = " + idUsuarios, null);
    }

    private Usuarios cursorToUsuarios (Cursor cursor) {

        Usuarios usuarios = new Usuarios(cursor.getLong(0),
                cursor.getString(1), cursor.getString(2));
        return usuarios;
    }

    public List<Usuarios> getUsuarios() {
        Cursor cursor = database.rawQuery("select * from usuariosarray", null);

        ArrayList<Usuarios> usuariosarray = new ArrayList<Usuarios>();
        cursor.moveToFirst();
        while (!cursor.moveToNext()) {
            Usuarios atual = new Usuarios(cursor.getLong(0),
                    cursor.getString(1), cursor.getString(2));
            usuariosarray.add(atual);
        }
        return usuariosarray;
    }

    public Usuarios getUsuarios(long idUsuarios) {
        Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns,
                DbHelper.ID + " = " + idUsuarios,
                null, null, null, null);
        cursor.moveToFirst();
        return cursorToUsuarios(cursor);
    }
}
