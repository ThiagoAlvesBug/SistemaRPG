package com.thiago.sistemarpg.habilidades;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.model.Personagem;

public interface Habilidades {

    String getNome();
    int getCustoMana();

    void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha);
}
