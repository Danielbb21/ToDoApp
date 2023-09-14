package com.example.todoapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoapp.Models.Tarefas;
import com.example.todoapp.R;

import java.util.ArrayList;

public class TarefaAdapter extends ArrayAdapter<Tarefas> {

    public TarefaAdapter(Context context, ArrayList<Tarefas> tarefas) {
        super(context, 0, tarefas);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tarefa_item_lista, parent, false);
        }

        Tarefas tarefa = getItem(position);

        TextView titulo = (TextView) convertView.findViewById(R.id.TituloView);
        TextView descricao = (TextView) convertView.findViewById(R.id.DescricaoView);

        titulo.setText(tarefa.getTitulo());
        descricao.setText(tarefa.getDescricao());

        return convertView;
    }
}
