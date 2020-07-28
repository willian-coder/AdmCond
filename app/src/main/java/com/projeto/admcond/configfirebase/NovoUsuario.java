package com.projeto.admcond.configfirebase;
/*Created by Willianlq*/
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class NovoUsuario {
    private String idUsuario;
    private String nome;
    private String email;

    public NovoUsuario() {
        DatabaseReference novoUsuario = ConfigDb.getRefFirebase()
                .child("Usuario");
        setIdUsuario(novoUsuario.push().getKey());

    }

    public void salvar() {
        DatabaseReference novoUsuarioRef = ConfigDb.getRefFirebase()
                .child("Usuario");
        //Usado como referencia da child- nome do usuario
        novoUsuarioRef.child(getNome())
                .setValue(this);

    }
    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
