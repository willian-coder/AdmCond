package com.projeto.admcond.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.projeto.admcond.MainActivity;
import com.projeto.admcond.R;
import com.projeto.admcond.configfirebase.NovoInquilino;

public class CadastroMorador extends AppCompatActivity {
    private EditText nomeInc;
    private EditText numeroApartInc;
    private EditText campoTelefoneInc;
    private EditText campoCpfInc;
    private CurrencyEditText valorAluguelInc;
    private Button btnCadastro;

    private NovoInquilino novoInquilino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_morador);

        inicializaComponentes();

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaDadosInquilino();
            }
        });

    }


    public NovoInquilino recuperaValores() {
        String nomeInquilino = nomeInc.getText().toString();
        String numeroApeInquilino = numeroApartInc.getText().toString();
        String numeroTelefone = campoTelefoneInc.getText().toString();
        String numeroCpf = campoCpfInc.getText().toString();
        String valorAluguel = valorAluguelInc.getText().toString();

        NovoInquilino novoInquilino = new NovoInquilino();
        novoInquilino.setNomeInquilino(nomeInquilino);
        novoInquilino.setNumeroApt(numeroApeInquilino);
        novoInquilino.setNumeroTelefoneInc(numeroTelefone);
        novoInquilino.setCpf(numeroCpf);
        novoInquilino.setValorAluguel(valorAluguel);

        return novoInquilino;
    }

    public void verificaDadosInquilino() {

        novoInquilino = recuperaValores();

            if (!novoInquilino.getNomeInquilino().isEmpty()) {
                if (!novoInquilino.getNumeroApt().isEmpty()) {
                    if (!novoInquilino.getNumeroTelefoneInc().isEmpty()) {
                        if (!novoInquilino.getCpf().isEmpty()) {
                            if (!novoInquilino.getValorAluguel().isEmpty()) {
                                novoInquilino.salvar();
                                aviso("Cadastro realizado com sucesso.");
                                startActivity(new Intent(CadastroMorador.this, MainActivity.class));
                                finish();
                            } else {
                                aviso("Informe o valor do aluguel");
                            }
                        } else {
                            aviso("CPF inválido!");
                        }
                    } else {
                        aviso("Campo telefone em branco!");
                    }
                } else {
                    aviso("Numero do Apt em branco!");
                }
            } else {
                aviso("Nome do morador em branco!");
            }
    }

    public void inicializaComponentes() {
        nomeInc = findViewById(R.id.nomeIncId);
        numeroApartInc = findViewById(R.id.apartIncId);
        campoTelefoneInc = findViewById(R.id.editTel);
        campoCpfInc = findViewById(R.id.editCpf);
        valorAluguelInc = findViewById(R.id.editValorAluguel);
        btnCadastro = findViewById(R.id.btnCadastroInc);


    }

    public void aviso(String msg) {
        Toast.makeText(CadastroMorador.this, msg, Toast.LENGTH_LONG).show();
    }
}