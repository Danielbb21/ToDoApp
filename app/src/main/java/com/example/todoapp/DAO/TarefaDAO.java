package com.example.todoapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todoapp.Helper.SQLiteDatabaseHelper;
import com.example.todoapp.Models.Tarefas;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase objWrite;
    private SQLiteDatabase objRead;

    public TarefaDAO(Context ctx) {
        SQLiteDatabaseHelper db = new SQLiteDatabaseHelper(ctx);
        objWrite = db.getWritableDatabase();
        objRead = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefas tarefas) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("Titulo",tarefas.getTitulo().toString());
            cv.put("Descricao",tarefas.getDescricao().toString());

            objWrite.insert("tarefas", null,cv);

            Log.i("INFO BD", "Dados Salvos com Sucesso");
        } catch (Exception ex) {
            Log.i("INFO BD", "Falha na gravação dos dados");
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefas tarefas) {
        try {

            ContentValues cv = new ContentValues();
            cv.put("Titulo",tarefas.getTitulo().toString());
            cv.put("Descricao",tarefas.getDescricao().toString());


            String[] args ={String.valueOf(tarefas.getId())};
            objWrite.update("tarefas", cv, "id = ?",  args);

            Log.i("INFO BD", "Dados Salvos com Sucesso");

        } catch (Exception ex) {
            Log.i("INFO BD", "Falha na gravação dos dados");
            return false;
        }
        return true;
    }


    @Override
    public boolean deletar(Tarefas tarefas) {

        try {
            objWrite.delete("tarefas", "id = " + tarefas.getId(), null);

            Log.i("INFO BD", "Dados Deletados com Sucesso");
        }catch (Exception ex) {
            Log.i("INFO BD", "Falha oa deletar os dados " + ex.getMessage());
            return false;
        }
        return true;
    }
    @Override
    public ArrayList<Tarefas> listar() {

        ArrayList<Tarefas> lista_tarefas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tarefas ";
            Cursor cursor = objRead.rawQuery(sql, null);

            int iid = cursor.getColumnIndexOrThrow("id");
            int iTitulo = cursor.getColumnIndexOrThrow("Titulo");
            int iDescricao = cursor.getColumnIndexOrThrow("Descricao");


            cursor.moveToFirst();

            do {
                if (cursor.getCount() ==0) {break;}
                Tarefas tarefa = new Tarefas();
                tarefa.setId(Integer.valueOf(cursor.getString(iid)));
                tarefa.setTitulo(cursor.getString(iTitulo));
                tarefa.setDescricao(cursor.getString(iDescricao));
                lista_tarefas.add(tarefa);

            } while (cursor.moveToNext());

            Log.i("INFO BD", "Sucesso na listagem dos dados");
        } catch (Exception ex) {
            Log.i("INFO BD", "Falha na listagem dos dados: " + ex.getMessage());
            return null;
        }
        return lista_tarefas;
    }
}
