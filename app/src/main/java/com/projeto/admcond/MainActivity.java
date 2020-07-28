package com.projeto.admcond;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.projeto.admcond.cadastro.CadastroCondominio;
import com.projeto.admcond.cadastro.CadastroMorador;
import com.projeto.admcond.configfirebase.ConfigDb;
import com.projeto.admcond.configfirebase.NovaLeitura;

public class MainActivity extends AppCompatActivity {
    private Button btnMorador;
    private Button btnAdicionar;
    private Button btnRelatorio;
    private Button btnCondominio;
    private Button btnSuporte;
    private Button btnSair;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaComponentes();

        auth = ConfigDb.getRefAutenticacao();//Autenticação

        btnMorador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataConsumoActivity.class));
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

        btnCondominio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroCondominio.class));
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
                alertaSair();

            }
        });
    }

    public void inicializaComponentes() {
        btnMorador = findViewById(R.id.btnMoradoresId);
        btnAdicionar = findViewById(R.id.btnAdicionarId);
        btnRelatorio = findViewById(R.id.btnRelatorioId);
        btnCondominio = findViewById(R.id.btnCondominioId);
        btnSuporte = findViewById(R.id.btnSuporteId);
        btnSair = findViewById(R.id.btnSairId);

    }

    public void deslogaUsuario() {
        auth.signOut();
    }

    public void alertaSair() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Sair");
        alert.setMessage("Deseja sair e deslogar o usuario?");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deslogaUsuario();
                finishAffinity();
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
}