package com.projeto.admcond.configfirebase;
/*Created by Willianlq*/

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class NovoInquilino implements Serializable {
    private String idInquilino;
    private String nomeInquilino;
    private String numeroApt;
    private String numeroTelefoneInc;
    private String cpfInc;
    private String valorAluguel;

    //Dados de consumo
    private String leitAnterior;
    private String leiturAtual;
    private String consumo;
    private String taxa;
    private String metroCubico;
    private String total;
    private String contaN;
    private String condominio;

    public NovoInquilino() {
        DatabaseReference novoInquilino = ConfigDb.getRefFirebase()
                .child("Inquilino");
        setIdInquilino(novoInquilino.push().getKey());
    }

    public void salvar() {
        String idUsuario = ConfigDb.getIdUsuario();
        DatabaseReference novoUsuarioRef = ConfigDb.getRefFirebase()
                .child("Inquilino");

        novoUsuarioRef.child(idUsuario)
                //Usado como referencia da child nome do inquilino
                .child(getNomeInquilino())
                .setValue(this);
    }

    //Ajustar
    public void salvarDadosConsumo() {
        String idConsumo = ConfigDb.getIdUsuario();
        DatabaseReference dbAgua = ConfigDb.getRefFirebase()
                .child("Agua");

        dbAgua.child(idConsumo)
                .setValue(this);

    }

    public String getLeitAnterior() {
        return leitAnterior;
    }

    public void setLeitAnterior(String leitAnterior) {
        this.leitAnterior = leitAnterior;
    }

    public String getLeiturAtual() {
        return leiturAtual;
    }

    public void setLeiturAtual(String leiturAtual) {
        this.leiturAtual = leiturAtual;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getContaN() {
        return contaN;
    }

    public void setConta(String contaN) {
        this.contaN = contaN;
    }

    public String getCondominio() {
        return condominio;
    }

    public void setCondominio(String condominio) {
        this.condominio = condominio;
    }

    public String getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(String idInquilino) {
        this.idInquilino = idInquilino;
    }

    public String getNomeInquilino() {
        return nomeInquilino;
    }

    public void setNomeInquilino(String nomeInquilino) {
        this.nomeInquilino = nomeInquilino;
    }

    public String getNumeroApt() {
        return numeroApt;
    }

    public void setNumeroApt(String numeroApt) {
        this.numeroApt = numeroApt;
    }

    public String getCpf() {
        return cpfInc;
    }

    public void setCpf(String cpf) {
        this.cpfInc = cpf;
    }

    public String getNumeroTelefoneInc() {
        return numeroTelefoneInc;
    }

    public void setNumeroTelefoneInc(String numeroTelefoneInc) {
        this.numeroTelefoneInc = numeroTelefoneInc;
    }

    public String getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(String valorAluguel) {
        this.valorAluguel = valorAluguel;
    }
}
