package com.projeto.admcond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.projeto.admcond.adpter.AdapterMoradores;
import com.projeto.admcond.adpter.RecyclerItemClickListener;
import com.projeto.admcond.configfirebase.ConfigDb;
import com.projeto.admcond.configfirebase.NovoInquilino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoradoresActivity extends AppCompatActivity {
    //Lista
    private RecyclerView recyclerView;
    private List<NovoInquilino> inquilinos = new ArrayList<>();
    private AdapterMoradores adapterInquilinos;
    private ProgressBar progressBar;

    //Firebase
    private String idUsuario;
    private DatabaseReference inquilinosRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_moradores);

        inicializaComponentes();

        idUsuario = ConfigDb.getIdUsuario();
        inquilinosRef = ConfigDb.getRefFirebase()
                .child("Inquilino")
                .child(idUsuario);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapterInquilinos = new AdapterMoradores(inquilinos, this);
        recyclerView.setAdapter(adapterInquilinos);

        recuperaInquilinos();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        NovoInquilino inquilinoSelecionado = inquilinos.get(position);
                        Intent intent = new Intent(MoradoresActivity.this, TipoConsumo.class);
                        intent.putExtra("inquilinoSelecionado", inquilinoSelecionado);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));
    }

    public void inicializaComponentes() {
        recyclerView = findViewById(R.id.recycleInquilinosId);
        progressBar = findViewById(R.id.progressBar);
    }

    public void recuperaInquilinos() {
        inquilinosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inquilinos.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    inquilinos.add(ds.getValue(NovoInquilino.class));
                }

                Collections.reverse(inquilinos);
                adapterInquilinos.notifyDataSetChanged();
                exibeProgressbar(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void exibeProgressbar(boolean exibe) {
        progressBar.setVisibility(exibe ? View.VISIBLE : View.GONE);
    }
}