package com.thiago.sistemarpg.controller;

public class JogoResponse {

    private String guerreiro;
    private String mago;

    public JogoResponse(String guerreiro, String mago){
        this.guerreiro = guerreiro;
        this.mago = mago;
    }

    public String getGuerreiro(){
        return guerreiro;
    }

    public String getMago(){
        return mago;
    }
}
