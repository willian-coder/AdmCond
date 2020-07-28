package com.projeto.admcond.configfirebase;
/*Created by Willianlq*/
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class NovoCondominio {
    private String idCondominio;
    private String nomeCondominio;
    private String cidadeCondominio;
    private String ruaCondominio;
    private String bairroCondominio;

    public NovoCondominio() {
        DatabaseReference novoCondominio = ConfigDb.getRefFirebase()
                .child("Condominio");
        setIdCondominio(novoCondominio.push().getKey());
    }

    public void salvar() {
        DatabaseReference novoUsuarioRef = ConfigDb.getRefFirebase();
        novoUsuarioRef.child("Condominio")

                .child(getNomeCondominio())
                .setValue(this);

    }

    @Exclude
    public String getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(String idCondominio) {
        this.idCondominio = idCondominio;
    }

    public String getNomeCondominio() {
        return nomeCondominio;
    }

    public void setNomeCondominio(String nomeCondominio) {
        this.nomeCondominio = nomeCondominio;
    }

    public String getCidadeCondominio() {
        return cidadeCondominio;
    }

    public void setCidadeCondominio(String cidadeCondominio) {
        this.cidadeCondominio = cidadeCondominio;
    }

    public String getRuaCondominio() {
        return ruaCondominio;
    }

    public void setRuaCondominio(String ruaCondominio) {
        this.ruaCondominio = ruaCondominio;
    }

    public String getBairroCondominio() {
        return bairroCondominio;
    }

    public void setBairroCondominio(String bairroCondominio) {
        this.bairroCondominio = bairroCondominio;
    }
}
