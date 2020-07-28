package com.projeto.admcond;
/*Created by Willianlq*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuporteActivity extends AppCompatActivity {
    private Button btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suporte);

        btnEmail = findViewById(R.id.btnEmailId);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(v);
            }
        });
    }

    public void email(View view) {
        final Intent intent = ShareCompat.IntentBuilder.from(SuporteActivity.this)
                .setType("message/rfc822")
                .addEmailTo("technoawt@gmail.com")
                .setSubject("Ajuda ADMcond")
                .createChooserIntent();

        startActivity(intent);
    }

    public void whats(View view) {
        String numeroSuporte = "51983274652";
        Uri uri = Uri.parse("smsto:" + numeroSuporte);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }
}