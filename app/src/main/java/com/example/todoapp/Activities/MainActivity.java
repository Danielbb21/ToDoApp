package com.example.todoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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