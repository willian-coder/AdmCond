package com.projeto.admcond;
/*Created by Willianlq*/
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipoConsumo extends AppCompatActivity {
    private Button btnAgua;
    private Button btnLuz;
    private Button btnGas;
    private Button btnOutros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_consumo);

        inicializaComponentes();

        btnAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TipoConsumo.this, DetalhesInquilinosActivity.class));
            }
        });

        btnLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void inicializaComponentes() {
        btnAgua = findViewById(R.id.btnAguaId);
        btnLuz = findViewById(R.id.btnLuzId);
        btnGas = findViewById(R.id.btnGasId);
        btnOutros = findViewById(R.id.btnOutros);
    }
}