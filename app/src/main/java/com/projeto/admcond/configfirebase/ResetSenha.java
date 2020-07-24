package com.projeto.admcond.configfirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.projeto.admcond.R;
import com.projeto.admcond.login.LoginActivity;

public class ResetSenha extends AppCompatActivity {
    private AutoCompleteTextView email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);

        auth = ConfigDb.getRefAutenticacao();
        email = findViewById(R.id.emailRec);

    }

    public void reset(View view) {
        auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    email.setText("");// Após sucesso deixa o campo em branco
                    alertDialog();
                } else {
                    Toast.makeText(getApplicationContext(), "Falhou!Tente novamente.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public final void alertDialog() {
        AlertDialog.Builder aviso = new AlertDialog.Builder(this);
        aviso.setTitle("Redefinição de senha");
        aviso.setMessage("E-mail enviado.\nVerifique sua caixa de SPAM.\nCaso não receba\nentre em contato.Obrigado.");
        aviso.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ResetSenha.this, LoginActivity.class));
                finish();
            }
        });
        AlertDialog dialog = aviso.create();
        dialog.show();
    }
}