package com.projeto.admcond.login;
/*Created by Willianlq*/
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.projeto.admcond.MainActivity;
import com.projeto.admcond.R;
import com.projeto.admcond.cadastro.CadastroUsuario;
import com.projeto.admcond.configfirebase.ConfigDb;
import com.projeto.admcond.configfirebase.ResetSenha;

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail;
    private EditText textSenha;
    private Button btnLogin;
    private Button btnCadastrar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Usuario Autenticado
        auth = ConfigDb.getRefAutenticacao();

        verificadoUsuarioLogado();
        //Wi-fi
        verificaConexao();

        inicializaComponentes();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaLogin();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroUsuario.class));
                finish();
            }
        });
    }

    private void validaLogin() {
        String email = textEmail.getText().toString();
        String senha = textSenha.getText().toString();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            aviso("Seja Bem-Vindo.");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            String erro = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                aviso("Usuario não está cadastrado.");
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                aviso("E-mail e senha, não correspondem ao usuario cadastrado.");
                            } catch (Exception e) {
                                aviso("Falha ao realizar Login, verifique os dados." + e.getMessage());
                                e.printStackTrace();
                            }
                            aviso(erro);
                        }
                    }
                });
            } else {
                aviso("Senha em branco!");
            }
        } else {
            aviso("E-mail em branco!");
        }

    }

    private void inicializaComponentes() {
        textEmail = findViewById(R.id.editEmailId);
        textSenha = findViewById(R.id.editSenhaId);
        btnLogin = findViewById(R.id.btnLogarId);
        btnCadastrar = findViewById(R.id.btnCadastroId);
    }

    public void aviso(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    //Reset de senha
    public void chamaReset(View v) {
        startActivity(new Intent(getApplicationContext(), ResetSenha.class));
    }

    //Verificação de WI-FI
    public boolean verificaConexao() {
        boolean conectado;
        Context context = getApplicationContext();
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
            msgWifi();
        }
        return conectado;
    }

    public void msgWifi() {
        //Customizar o alerta //05/06/2020
        final AlertDialog.Builder wifiState = new AlertDialog.Builder(this);
        wifiState.setTitle("Conexão de internet");
        wifiState.setView(R.layout.dialog_wifi);
        wifiState.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        wifiState.setNegativeButton("Configurações", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS), 0);
            }
        });
        AlertDialog alertDialog = wifiState.create();
        alertDialog.show();

    }

    public void verificadoUsuarioLogado() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}