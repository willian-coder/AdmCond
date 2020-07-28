package com.projeto.admcond;
/*Created by Willianlq*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projeto.admcond.adpter.AdapterLeituras;
import com.projeto.admcond.adpter.RecyclerItemClickListener;
import com.projeto.admcond.cadastro.CadastroNovaLeitura;
import com.projeto.admcond.configfirebase.ConfigDb;
import com.projeto.admcond.configfirebase.NovaLeitura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataConsumoActivity extends AppCompatActivity {
    //Lista
    private RecyclerView recyclerViewNovaLeitura;
    private List<NovaLeitura> novaLeitura = new ArrayList<>();
    private AdapterLeituras adapterLeitura;
    private ProgressBar progressBar;
    private FloatingActionButton fab;

    //Firebase
    private String idUsuario;
    private DatabaseReference leituraRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_nova_leitura);

        inicializaComponentes();

        idUsuario = ConfigDb.getIdUsuario();
        leituraRef = ConfigDb.getRefFirebase()
                .child("Leituras")
                .child(idUsuario);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroNovaLeitura.class));
            }
        });

        recyclerViewNovaLeitura.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNovaLeitura.setHasFixedSize(true);

        adapterLeitura = new AdapterLeituras(novaLeitura, this);
        recyclerViewNovaLeitura.setAdapter(adapterLeitura);

        recuperaLeitura();

        recyclerViewNovaLeitura.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerViewNovaLeitura,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        NovaLeitura leituraSelecionada = novaLeitura.get(position);
                        Intent intent = new Intent(DataConsumoActivity.this, MoradoresActivity.class);
                        intent.putExtra("leituraSelecionada", leituraSelecionada);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        alertaLeitura(position);
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));

    }

    public void recuperaLeitura() {
        leituraRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                novaLeitura.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    novaLeitura.add(ds.getValue(NovaLeitura.class));
                }

                Collections.reverse(novaLeitura);
                adapterLeitura.notifyDataSetChanged();
                exibeProgressbar(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void inicializaComponentes() {
        recyclerViewNovaLeitura = findViewById(R.id.recycleNovaLeituraId);
        progressBar = findViewById(R.id.progressBarLeituras);
        fab = findViewById(R.id.fabConsumoId);
    }

    public void exibeProgressbar(boolean exibe) {
        progressBar.setVisibility(exibe ? View.VISIBLE : View.GONE);
    }

    public void alertaLeitura(final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Encerrar leitura");
        alert.setMessage("Deseja encerrar a leitura?");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Inativar o click

            }
        });
        alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertEx = alert.create();
        alertEx.show();
    }

    public void alteraCor(View view) {
        view.setBackgroundColor(getColor(R.color.colorPrimary));
    }
}