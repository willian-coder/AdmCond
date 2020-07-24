package com.projeto.admcond.configfirebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigDb {
    private static DatabaseReference databaseReference;
    private static FirebaseAuth auth;

    public static String getIdUsuario(){
        FirebaseAuth autenticacao = getRefAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }

    public static DatabaseReference getRefFirebase() {
        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();

        }
        return databaseReference;
    }

    public static FirebaseAuth getRefAutenticacao() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

}

