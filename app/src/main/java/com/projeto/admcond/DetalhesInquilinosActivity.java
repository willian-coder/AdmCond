package com.projeto.admcond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.projeto.admcond.cadastro.CadastroUsuario;
import com.projeto.admcond.configfirebase.ConfigDb;
import com.projeto.admcond.configfirebase.NovoInquilino;

public class DetalhesInquilinosActivity extends AppCompatActivity {

    private EditText data;
    private EditText leituraAnterior;
    private EditText leituraAtual;
    private TextView consumo;
    private EditText taxa;
    private EditText metroCub;
    public TextView total;
    private EditText conta;
    public TextView condominio;
    private Button calcularBtn;
    private Button salvarBtn;

    //Resultados de Consumo
    public double restConsumo;
    public double restTotal;
    public String valorDeConsumo;
    public String valorDoTotal;
    public String valorDaConta;

    //FireBase
    public NovoInquilino dadosConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_inquilinos);

        inicializaComponentes();

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
        String dataLeitura = data.getText().toString();
        String leituraAnt = leituraAnterior.getText().toString();
        String leituraAtu = leituraAtual.getText().toString();
        String taxaCons = taxa.getText().toString();
        String metroCubi = metroCub.getText().toString();
        String valorConta = conta.getText().toString();

        if (!dataLeitura.isEmpty()) {
            if (!leituraAnt.isEmpty()) {
                if (!leituraAtu.isEmpty()) {
                    if (!taxaCons.isEmpty()) {
                        if (!metroCubi.isEmpty()) {
                            if (!valorConta.isEmpty()) {
                                salvaNovoConsumo();
                                aviso("Dados salvos.");
                                startActivity(new Intent());
                            } else {
                                aviso("Informe o valor da conta.");
                            }
                        } else {
                            aviso("Informe o metro cubido.");
                        }
                    } else {
                        aviso("Informe a taxa.");
                    }
                } else {
                    aviso("Informe a leitura atual.");
                }
            } else {
                aviso("Informe a leitura anterior.");
            }
        } else {
            aviso("Preencha a 'Data'.");
        }
    }

    private NovoInquilino recuperaConsumo() {
        NovoInquilino novoInquilino = new NovoInquilino();

        String dataLeitura = data.getText().toString();
        String leituraAnt = leituraAnterior.getText().toString();
        String leituraAtu = leituraAtual.getText().toString();
        String taxaCons = taxa.getText().toString();
        String metroCubi = metroCub.getText().toString();
        String valorConta = conta.getText().toString();

        //Calcula consumo
        double leituraAntConsumo = Double.parseDouble(leituraAnterior.getText().toString());
        double leituraAtualConsumo = Double.parseDouble(leituraAtual.getText().toString());
        String subLeituras = String.valueOf(leituraAtualConsumo - leituraAntConsumo);
        consumo.setText(subLeituras);
        valorDeConsumo = consumo.getText().toString();

        //Calcula Total
        restConsumo = Double.parseDouble(consumo.getText().toString());
        double metroCubico = Double.parseDouble(metroCub.getText().toString());
        double taxaFixa = Double.parseDouble(taxa.getText().toString());
        String calcTotal = String.valueOf(restConsumo * metroCubico + taxaFixa);
        total.setText(calcTotal);
        valorDoTotal = total.getText().toString();

        //Calcula Conta
        restTotal = Double.parseDouble(total.getText().toString());
        double calcConta = Double.parseDouble(conta.getText().toString());
        String calculoFinal = String.valueOf(calcConta - restTotal);
        condominio.setText(calculoFinal);
        valorDaConta = condominio.getText().toString();

        novoInquilino.setData(dataLeitura);
        novoInquilino.setLeitAnterior(leituraAnt);
        novoInquilino.setLeiturAtual(leituraAtu);
        novoInquilino.setTaxa(taxaCons);
        novoInquilino.setMetroCubico(metroCubi);
        novoInquilino.setConta(valorConta);
        novoInquilino.setConsumo(valorDeConsumo);
        novoInquilino.setTotal(valorDoTotal);
        novoInquilino.setCondominio(valorDaConta);


        return novoInquilino;
    }

    private void salvaNovoConsumo() {
        dadosConsumo.salvar();
    }

    private void verificaConsumo() {
        dadosConsumo = recuperaConsumo();

        if (!dadosConsumo.getData().isEmpty()) {
            if (!dadosConsumo.getLeitAnterior().isEmpty()) {
                if (!dadosConsumo.getLeiturAtual().isEmpty()) {
                    if (!dadosConsumo.getTaxa().isEmpty()) {
                        if (!dadosConsumo.getMetroCubico().isEmpty()) {
                            if (!dadosConsumo.getContaN().isEmpty()) {
                                calculaConsumo();
                                salvarBtn.setVisibility(View.VISIBLE);
                            } else {
                                aviso("Informe o valor da conta.");
                            }
                        } else {
                            aviso("Informe o metro cubico.");
                        }
                    } else {
                        aviso("Informe a taxa.");
                    }
                } else {
                    aviso("Informe a leitura atual.");
                }
            } else {
                aviso("Informe a leitura anterior.");
            }
        } else {
            aviso("Preencha a 'Data'.");
        }
    }

    private void calculaConsumo() {

        //Calcula consumo
        double leituraAntConsumo = Double.parseDouble(leituraAnterior.getText().toString());
        double leituraAtualConsumo = Double.parseDouble(leituraAtual.getText().toString());
        String subLeituras = String.valueOf(leituraAtualConsumo - leituraAntConsumo);
        consumo.setText(subLeituras);

        //Calcula Total
        restConsumo = Double.parseDouble(consumo.getText().toString());
        double metroCubico = Double.parseDouble(metroCub.getText().toString());
        double taxaFixa = Double.parseDouble(taxa.getText().toString());
        String calcTotal = String.valueOf(restConsumo * metroCubico + taxaFixa);
        total.setText(calcTotal);

        //Calcula Conta
        restTotal = Double.parseDouble(total.getText().toString());
        double calcConta = Double.parseDouble(conta.getText().toString());
        String calculoFinal = String.valueOf(calcConta - restTotal);
        condominio.setText(calculoFinal);
    }

    public void inicializaComponentes() {
        data = findViewById(R.id.dataId);
        leituraAnterior = findViewById(R.id.leituraAnteriorId);
        leituraAtual = findViewById(R.id.leituraAtualId);
        consumo = findViewById(R.id.viewConsumoId);
        taxa = findViewById(R.id.taxaId);
        metroCub = findViewById(R.id.metroCubicoId);
        total = findViewById(R.id.viewTotalId);
        conta = findViewById(R.id.contaId);
        condominio = findViewById(R.id.viewCondominioId);
        calcularBtn = findViewById(R.id.calcularBtnId);
        salvarBtn = findViewById(R.id.salvaBtnId);
    }

    public void aviso(String msg) {
        Toast.makeText(DetalhesInquilinosActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}