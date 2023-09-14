package com.example.todoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.todoapp.Adapters.TarefaAdapter;
import com.example.todoapp.DAO.TarefaDAO;
import com.example.todoapp.Models.Tarefas;
import com.example.todoapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btAdicionar;
    private ListView listTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btAdicionar = findViewById(R.id.btAdicionar);
        listTarefas = findViewById(R.id.listTarefas);

        ListaTarefas();

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastrarTarefas.class);
                startActivity(intent);
            }
        });
        listTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                ArrayAdapter<Tarefas> adapter = (ArrayAdapter) listTarefas.getAdapter();
                Tarefas selected = adapter.getItem(position);
                TarefaDAO dbTarefa = new TarefaDAO(getApplicationContext());
                Tarefas tarefa = new Tarefas();
                tarefa.setId((int) selected.getId());
                dbTarefa.deletar(tarefa);
                ListaTarefas();
                Toast.makeText(MainActivity.this, "Tarefa excluida com sucesso", Toast.LENGTH_SHORT).show();
                return true;
        }});
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListaTarefas();
    }

    public void ListaTarefas(){

        TarefaDAO dbTarefa = new TarefaDAO(getApplicationContext());
        ArrayList<Tarefas> lista =  dbTarefa.listar();

        if(lista == null){
            lista =  new ArrayList<>();
        }

        ArrayAdapter<Tarefas> adaptador = new TarefaAdapter(getApplicationContext(), lista);
        listTarefas.setAdapter(adaptador);
    }
}