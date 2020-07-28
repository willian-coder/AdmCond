package com.projeto.admcond.cadastro;
/*Created by Willianlq*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.admcond.DetalhesInquilinosActivity;
import com.projeto.admcond.R;
import com.projeto.admcond.configfirebase.NovaLeitura;

public class CadastroNovaLeitura extends AppCompatActivity {
    private EditText data;
    private EditText metroCubico;
    private EditText taxa;
    private EditText conta;
    private Button btnSalvar;

    public static String novoMetrocubico;
    public static String novaTaxa;

    private NovaLeitura novaLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_nova_leitura);

        inicializaComponentes();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaCampos();
            }
        });
    }

    public NovaLeitura novaLeitura() {
        NovaLeitura geraLeitura = new NovaLeitura();

        String novaData = data.getText().toString();
        String novaConta = conta.getText().toString();
        novoMetrocubico = metroCubico.getText().toString();
        novaTaxa = taxa.getText().toString();

        geraLeitura.setData(novaData);
        geraLeitura.setConta(novaConta);
        geraLeitura.setMetroCubico(novoMetrocubico);
        geraLeitura.setTaxa(novaTaxa);

        return geraLeitura;
    }

    public void verificaCampos() {
        novaLeitura = novaLeitura();

        if (!novaLeitura.getData().isEmpty()) {
            if (!novaLeitura.getMetroCubico().isEmpty()) {
                if (!novaLeitura.getConta().isEmpty()) {
                    if (!novaLeitura.getTaxa().isEmpty()) {
                        salvaNovaLeitura();
                        finish();
                        aviso("Salvo.");
                    } else {
                        aviso("Informe a taxa.");
                    }
                } else {
                    aviso("Informe o valor da conta.");
                }
            } else {
                aviso("Informe o valor do metro.");
            }
        } else {
            aviso("Informe a data.");
        }
    }

    public void salvaNovaLeitura() {
        novaLeitura.salvar();
    }

    public void inicializaComponentes() {
        data = findViewById(R.id.dataNovoConsumoId);
        conta = findViewById(R.id.valorContaId);
        metroCubico = findViewById(R.id.metroCubicoId);
        taxa = findViewById(R.id.taxaId);
        btnSalvar = findViewById(R.id.salvarId);
    }

    public void aviso(String msg) {
        Toast.makeText(CadastroNovaLeitura.this, msg, Toast.LENGTH_LONG).show();
    }
}