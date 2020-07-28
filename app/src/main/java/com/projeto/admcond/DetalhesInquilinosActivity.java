package com.projeto.admcond;
/*Created by Willianlq*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.admcond.adpter.AdapterMoradores;
import com.projeto.admcond.cadastro.CadastroNovaLeitura;
import com.projeto.admcond.configfirebase.NovoInquilino;

public class DetalhesInquilinosActivity extends AppCompatActivity {

    private EditText leituraAnterior;
    private EditText leituraAtual;
    public TextView consumo;
    public TextView total;
    private Button calcularBtn;
    private Button salvarBtn;

    //FireBase
    public NovoInquilino dadosConsumo;
    public CadastroNovaLeitura novaLeitura;

    //Calculos finais
    public String varCubico = CadastroNovaLeitura.novoMetrocubico;
    public String varNovaTaxa = CadastroNovaLeitura.novaTaxa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_inquilinos);

        inicializaComponentes();

        novaLeitura = new CadastroNovaLeitura();

        calcularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaConsumo();
            }
        });

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaConsumo();
            }
        });
    }

    private void salvaConsumo() {
        String leituraAnt = leituraAnterior.getText().toString();
        String leituraAtu = leituraAtual.getText().toString();

        if (!leituraAnt.isEmpty()) {
            if (!leituraAtu.isEmpty()) {
                salvaNovoConsumo();
                aviso("Dados salvos.");
                startActivity(new Intent(DetalhesInquilinosActivity.this, MoradoresActivity.class));

            } else {
                aviso("Informe a leitura atual.");
            }
        } else {
            aviso("Informe a leitura anterior.");
        }

    }

    private void salvaNovoConsumo() {
        dadosConsumo.salvarDadosConsumo();
    }

    private NovoInquilino recuperaConsumo() {
        NovoInquilino novoInquilino = new NovoInquilino();

        String leituraAnt = leituraAnterior.getText().toString();
        String leituraAtu = leituraAtual.getText().toString();

        novoInquilino.setLeitAnterior(leituraAnt);
        novoInquilino.setLeiturAtual(leituraAtu);

        return novoInquilino;
    }

    private void verificaConsumo() {
        dadosConsumo = recuperaConsumo();

        if (!dadosConsumo.getLeitAnterior().isEmpty()) {
            if (!dadosConsumo.getLeiturAtual().isEmpty()) {
                calculaConsumo();
                salvarBtn.setVisibility(View.VISIBLE);
            } else {
                aviso("Informe a leitura atual.");
            }
        } else {
            aviso("Informe a leitura anterior.");
        }

    }

    private void calculaConsumo() {

        //Calcula Consumo
        double leituraAntConsumo = Double.parseDouble(leituraAnterior.getText().toString());
        double leituraAtualConsumo = Double.parseDouble(leituraAtual.getText().toString());
        String subLeituras = String.valueOf(leituraAtualConsumo - leituraAntConsumo);
        consumo.setText(subLeituras);

        //Calcula Total
        double varTotal = Double.parseDouble(subLeituras);
        double varTotal1 = Double.parseDouble(varCubico);
        double varToral2 = Double.parseDouble(varNovaTaxa);

        String varFinal = String.valueOf(varTotal * varTotal1 + varToral2);
        total.setText(varFinal);
    }

    public void inicializaComponentes() {
        leituraAnterior = findViewById(R.id.leituraAnteriorId);
        leituraAtual = findViewById(R.id.leituraAtualId);
        consumo = findViewById(R.id.viewConsumoId);
        total = findViewById(R.id.viewTotalId);
        calcularBtn = findViewById(R.id.calcularBtnId);
        salvarBtn = findViewById(R.id.salvaBtnId);
    }

    public void aviso(String msg) {
        Toast.makeText(DetalhesInquilinosActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    public void alteraCor(View view) {
        view.setBackgroundColor(getColor(R.color.colorPrimary));
    }


}