package com.projeto.admcond.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.admcond.R;
import com.projeto.admcond.configfirebase.NovoCondominio;

public class CadastroCondominio extends AppCompatActivity {
    //Dados Condomínio
    private EditText nomeCond;
    private EditText cidadeCond;
    private EditText ruaCond;
    private EditText bairroCond;
    private Button btnCadastroCond;
    private NovoCondominio novoCondominio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_condominio);

        inicializaComponentes();

        btnCadastroCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCondominio();
            }
        });
    }

    private void inicializaComponentes() {

        //Condomínio
        nomeCond = findViewById(R.id.editNomeCondId);
        cidadeCond = findViewById(R.id.editCidadeCondId);
        ruaCond = findViewById(R.id.editRuaCondId);
        bairroCond = findViewById(R.id.editBairroCondId);
        btnCadastroCond = findViewById(R.id.btnCadastroCondId);

    }

    public NovoCondominio recuperaDadosCondominio() {
        String nomeCondominio = nomeCond.getText().toString();
        String cidadeCondominio = cidadeCond.getText().toString();
        String ruaCondominio = ruaCond.getText().toString();
        String bairroCondominio = bairroCond.getText().toString();

        NovoCondominio novoCond = new NovoCondominio();
        novoCond.setNomeCondominio(nomeCondominio);
        novoCond.setCidadeCondominio(cidadeCondominio);
        novoCond.setRuaCondominio(ruaCondominio);
        novoCond.setBairroCondominio(bairroCondominio);

        return novoCond;
    }


    public void validaCondominio() {

        novoCondominio = recuperaDadosCondominio();

        if (!novoCondominio.getNomeCondominio().isEmpty()) {
            if (!novoCondominio.getCidadeCondominio().isEmpty()) {
                if (!novoCondominio.getRuaCondominio().isEmpty()) {
                    if (!novoCondominio.getBairroCondominio().isEmpty()) {
                        salvaCondominio();
                        aviso("Condomínio salvo.");
                    } else {
                        aviso("Preencha o 'Bairro'.");
                    }
                } else {
                    aviso("Preencha a 'Rua'.");
                }
            } else {
                aviso("Preencha a 'Cidade'.");
            }
        } else {
            aviso("Preencha o 'Nome do Condomínio'.");
        }

    }

    public void salvaCondominio() {
        novoCondominio.salvar();
    }

    public void aviso(String msg) {
        Toast.makeText(CadastroCondominio.this, msg, Toast.LENGTH_LONG).show();
    }
}