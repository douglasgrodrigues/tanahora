package com.dgr.tanahora.model;

import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Veiculo {

    private String id;
    private String marca;
    private String modelo;
    private String ano;
    private String placa;
    private String tipoVeiculo;

    public Veiculo() {
    }

    public void salvar(){

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDataBase();
        firebase.child("veiculo")
                .child(getId())
                .push()
                .setValue(this);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
