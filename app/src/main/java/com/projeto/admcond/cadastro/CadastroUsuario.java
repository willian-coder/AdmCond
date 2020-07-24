package com.projeto.admcond.cadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.projeto.admcond.MainActivity;
import com.projeto.admcond.R;
import com.projeto.admcond.configfirebase.ConfigDb;
import com.projeto.admcond.configfirebase.NovoCondominio;
import com.projeto.admcond.configfirebase.NovoUsuario;

public class CadastroUsuario extends AppCompatActivity {

    //Dados Síndico
    private EditText nomeNovoUsuario;
    private EditText emailNovoUsuario;
    private EditText senhaNovoUsuario;
    private Button btnCadastroNovoUser;
    private NovoUsuario novoUser;

    //Firebase
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        auth = ConfigDb.getRefAutenticacao();

        inicializaComponentes();

        btnCadastroNovoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCadastro();
            }
        });

    }

    private void inicializaComponentes() {
        //Síndico
        nomeNovoUsuario = findViewById(R.id.editNovoUserId);
        emailNovoUsuario = findViewById(R.id.editEmailNovoUserId);
        senhaNovoUsuario = findViewById(R.id.editSenhaNovoUserId);
        btnCadastroNovoUser = findViewById(R.id.btnCadastroUsuarioId);
    }


    private NovoUsuario recuperaNovoUsuario() {
        String nomeUsuario = nomeNovoUsuario.getText().toString();
        String emailUsuario = emailNovoUsuario.getText().toString();

        NovoUsuario novoUsuario = new NovoUsuario();
        novoUsuario.setNome(nomeUsuario);
        novoUsuario.setEmail(emailUsuario);

        return novoUsuario;
    }

    private void validaCadastro() {
        String senhaUsuario = String.valueOf(senhaNovoUsuario.getText());

        novoUser = recuperaNovoUsuario();

        if (!novoUser.getNome().isEmpty()) {//nome
            if (!novoUser.getEmail().isEmpty()) {//email
                if (!senhaUsuario.isEmpty()) { //senha

                    auth.createUserWithEmailAndPassword(novoUser.getEmail(), senhaUsuario)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        salvaUsuario();
                                        aviso("Sucesso ao realizar cadastro.");
                                        startActivity(new Intent(CadastroUsuario.this, MainActivity.class));
                                        finish();
                                    } else {
                                        String erroExcecao = "";
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            erroExcecao = "Digite uma senha mais forte!";
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            erroExcecao = "Conta já cadastrada.";
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            erroExcecao = "Digite um e-mail válido.";
                                        } catch (Exception e) {
                                            erroExcecao = "ao cadastrar usuario!" + e.getMessage();
                                            e.printStackTrace();
                                        }
                                        aviso("FALHA " + erroExcecao);
                                    }

                                }
                            });

                } else {
                    aviso("Senha inválida.");
                }
            } else {
                aviso("E-mail inválido.");
            }
        } else {
            aviso("Nome inválido.");
        }
    }

    public void salvaUsuario() {
        novoUser.salvar();
    }

    public void aviso(String msg) {
        Toast.makeText(CadastroUsuario.this, msg, Toast.LENGTH_LONG).show();
    }
}