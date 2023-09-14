package com.example.todoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.DAO.TarefaDAO;
import com.example.todoapp.Models.Tarefas;
import com.example.todoapp.R;

public class CadastrarTarefas extends AppCompatActivity {
    private EditText txtTitulo, txtDescricao;
    private Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefas);

        txtDescricao = findViewById(R.id.txtDescricao);
        txtTitulo = findViewById(R.id.txtTitulo);
        btSalvar = findViewById(R.id.btCriar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TarefaDAO dbTarefa = new TarefaDAO(getApplicationContext());
                Tarefas tarefa = new Tarefas();
                tarefa.setTitulo(txtTitulo.getText().toString());
                tarefa.setDescricao(txtDescricao.getText().toString());

                dbTarefa.salvar(tarefa);

                Toast.makeText(CadastrarTarefas.this,
                        "Dados Salvos com Sucesso",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}