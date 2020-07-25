package com.projeto.admcond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projeto.admcond.cadastro.CadastroMorador;

public class MainActivity extends AppCompatActivity {
    private Button btnMorador;
    private Button btnAdicionar;
    private Button btnRelatorio;
    private Button btnSuporte;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaComponentes();

        btnMorador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoradoresActivity.class));
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroMorador.class));
            }
        });

        btnRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RelatorioActivity.class));
            }
        });

        btnSuporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SuporteActivity.class));
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    public void inicializaComponentes() {
        btnMorador = findViewById(R.id.btnMoradoresId);
        btnAdicionar = findViewById(R.id.btnAdicionarId);
        btnRelatorio = findViewById(R.id.btnRelatorioId);
        btnSuporte = findViewById(R.id.btnSuporteId);
        btnSair = findViewById(R.id.btnSairId);

    }

}