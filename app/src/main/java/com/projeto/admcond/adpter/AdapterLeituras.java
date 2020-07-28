package com.projeto.admcond.adpter;
/*Created by Willianlq*/
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projeto.admcond.R;
import com.projeto.admcond.configfirebase.NovaLeitura;

import java.util.List;

public class AdapterLeituras extends RecyclerView.Adapter<AdapterLeituras.MyViewHolder> {

    private List<NovaLeitura> novaLeitura;
    public Context context;

    public AdapterLeituras(List<NovaLeitura> leituras, Context context) {
        this.novaLeitura = leituras;
        this.context = context;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_nova_leitura, parent, false);
        return new MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NovaLeitura leitura = novaLeitura.get(position);
        holder.data.setText(leitura.getData());
        holder.conta.setText(leitura.getConta());
        holder.total.setText(leitura.getTotal());
        holder.condominio.setText(leitura.getCondominio());
    }

    @Override
    public int getItemCount() {
        return novaLeitura.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView data;
        TextView conta;
        TextView total;
        TextView condominio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textData);
            conta = itemView.findViewById(R.id.textValorConta);
            total = itemView.findViewById(R.id.textTotalInquilinos);
            condominio = itemView.findViewById(R.id.textCondominio);
        }
    }

}
