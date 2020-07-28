package com.projeto.admcond.adpter;
/*Created by Willianlq*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import com.projeto.admcond.R;
import com.projeto.admcond.configfirebase.NovoInquilino;

import java.util.List;

public class AdapterMoradores extends RecyclerView.Adapter<AdapterMoradores.MyViewHolder> {

    private List<NovoInquilino> inquilinos;
    public Context context;

    public AdapterMoradores(List<NovoInquilino> inquilinos, Context context) {
        this.inquilinos = inquilinos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_moradores, parent, false);
        return new MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NovoInquilino novoInquilino = inquilinos.get(position);
        holder.apartamento.setText(novoInquilino.getNumeroApt());
        holder.nomeInquilino.setText(novoInquilino.getNomeInquilino());
    }

    @Override
    public int getItemCount() {
        return inquilinos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView apartamento;
        TextView nomeInquilino;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            apartamento = itemView.findViewById(R.id.textApt);
            nomeInquilino = itemView.findViewById(R.id.textInquilino);
        }
    }

}
