package com.example.rober.appae_rotinaehigiene;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Samuel wallace assunção
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void inserirDados(byte[] Imagem, Integer valor) {


            deletarDados(valor);
            SQLiteDatabase database = getWritableDatabase();

            String sql = "INSERT INTO ATIVIDADE VALUES (?, ?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindDouble(1,valor);

            statement.bindBlob(2, Imagem);


            statement.executeInsert();
            database.close();



}



    public  void deletarDados(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM ATIVIDADE WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
