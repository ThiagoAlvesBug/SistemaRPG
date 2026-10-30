package com.thiago.sistemarpg.controller;

import java.util.List;

public class BatalhaResponse {

    private String jogadorAtivo;

    private String guerreiro;
    private float vidaGuerreiro;
    private float manaGuerreiro;

    private String mago;
    private float vidaMago;
    private float manaMago;

    private String inimigo;
    private float vidaInimigo;

    private List<String> log;

    public BatalhaResponse(
            String jogadorAtivo,
            String guerreiro,
            float vidaGuerreiro,
            float manaGuerreiro,
            String mago,
            float vidaMago,
            float manaMago,
            String inimigo,
            float vidaInimigo,
            List<String> log)
    {
        this.jogadorAtivo = jogadorAtivo;
        this.guerreiro = guerreiro;
        this.vidaGuerreiro = vidaGuerreiro;
        this.manaGuerreiro = manaGuerreiro;
        this.mago = mago;
        this.vidaMago = vidaMago;
        this.manaMago = manaMago;
        this.inimigo = inimigo;
        this.vidaInimigo = vidaInimigo;
        this.log = log;
    }

    public String getJogadorAtivo() { return jogadorAtivo; }

    public String getGuerreiro() { return guerreiro; }
    public float getVidaGuerreiro() { return vidaGuerreiro; }
    public float getManaGuerreiro() { return manaGuerreiro; }

    public String getMago() { return mago; }
    public float getVidaMago() { return vidaMago; }
    public float getManaMago() { return manaMago; }

    public String getInimigo() { return inimigo; }
    public float getVidaInimigo() { return vidaInimigo; }
    public List<String> getLog() { return log; }
}
