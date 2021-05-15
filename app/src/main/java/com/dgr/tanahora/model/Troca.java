package com.dgr.tanahora.model;

public class Troca {

    private String id;
    private String dataTroca;
    private String tipoOleo;
    private String kmTroca;
    private String proximaTroca;

    public Troca() {
    }

    public String getId() {
        return id;
    }

    public String getDataTroca() {
        return dataTroca;
    }

    public void setDataTroca(String dataTroca) {
        this.dataTroca = dataTroca;
    }

    public String getTipoOleo() {
        return tipoOleo;
    }

    public void setTipoOleo(String tipoOleo) {
        this.tipoOleo = tipoOleo;
    }

    public String getKmTroca() {
        return kmTroca;
    }

    public void setKmTroca(String kmTroca) {
        this.kmTroca = kmTroca;
    }

    public String getProximaTroca() {
        return proximaTroca;
    }

    public void setProximaTroca(String proximaTroca) {
        this.proximaTroca = proximaTroca;
    }
}
