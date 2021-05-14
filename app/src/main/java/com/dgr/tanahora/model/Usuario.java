package com.dgr.tanahora.model;

import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public void salvar() {

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDataBase();
        DatabaseReference usuariosRef = firebaseRef.child("usuarios").child(getId());
        usuariosRef.setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



}
