package com.example.todoapp.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "AV1";
    private static final int version = 1;

    public SQLiteDatabaseHelper(Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS tarefas(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, Titulo VARCHAR(255), Descricao VARCHAR(255) ) ");
            Log.i("INFO: ", "Deu Bom");
        }catch (Exception e){
            Log.i("INFO: ", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
