package com.projeto.admcond.configfirebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.projeto.admcond.configfirebase.ConfigDb;

import java.io.Serializable;

public class NovoInquilino implements Serializable {
    private String idInquilino;
    private String nomeInquilino;
    private String numeroApt;
    private String numeroTelefoneInc;
    private String cpfInc;
    private String valorAluguel;

    //Dados de consumo
    private String data;
    private String leitAnterior;
    private String leiturAtual;
    public String consumo;
    private String taxa;
    private String metroCubico;
    public String total;
    private String contaN;
    public String condominio;

    public NovoInquilino() {
        DatabaseReference novoInquilino = ConfigDb.getRefFirebase()
                .child("Inquilino");
        setIdInquilino(novoInquilino.push().getKey());
    }

    public void salvar() {
        String idUsuario = ConfigDb.getIdUsuario();
        DatabaseReference novoUsuarioRef = ConfigDb.getRefFirebase()
                .child("Inquilino");

        String idConsumo = ConfigDb.getIdUsuario();
        novoUsuarioRef.child(idUsuario)
                .child("-MCyWuzZv2g0TXTaZDrU") // TESTE
                .child("Agua")
                .child(idConsumo)
                .setValue(this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public void setTotal(String total) {
        this.total = total;
    }

    public String getContaN() {
        return contaN;
    }

    public void setConta(String contaN) {
        this.contaN = contaN;
    }

    public void setCondominio(String condominio) {
        this.condominio = condominio;
    }

    @Exclude
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
