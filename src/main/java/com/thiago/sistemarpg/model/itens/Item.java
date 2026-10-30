package com.thiago.sistemarpg.model.itens;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.model.Personagem;

public abstract class Item {
    protected String nome;
    protected String descricao;

    public Item(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public abstract void usar(Personagem usuario, SistemaBatalha batalha);
}