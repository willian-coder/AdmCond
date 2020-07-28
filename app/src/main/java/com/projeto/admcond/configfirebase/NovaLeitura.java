package com.projeto.admcond.configfirebase;
/*Created by Willianlq*/

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class NovaLeitura implements Serializable {
    private String idLeitura;
    private String data;
    private String conta;
    private String total;
    private String taxa;
    private String metroCubico;
    private String condominio;

    public NovaLeitura() {
        DatabaseReference dbNovaLeitura = ConfigDb.getRefFirebase()
                .child("Leituras");
        setIdLeitura(dbNovaLeitura.push().getKey());
    }

    public void salvar() {
        String idUsuario = ConfigDb.getIdUsuario();
        DatabaseReference novaLeitura = ConfigDb.getRefFirebase()
                .child("Leituras");

        novaLeitura
                .child(idUsuario)
                //Realizado troca de get para data e realizada conversão de caracteres
                .child(getData().replaceAll("/",""))
                .setValue(this);
    }

    public String getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(String idLeitura) {
        this.idLeitura = idLeitura;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCondominio() {
        return condominio;
    }

    public void setCondominio(String condominio) {
        this.condominio = condominio;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public String getMetroCubico() {
        return metroCubico;
    }

    public void setMetroCubico(String metroCubico) {
        this.metroCubico = metroCubico;
    }
}
