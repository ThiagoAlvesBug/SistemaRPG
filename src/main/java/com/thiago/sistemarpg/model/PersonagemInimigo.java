package com.thiago.sistemarpg.model;
import com.thiago.sistemarpg.batalha.SistemaBatalha;

public abstract class PersonagemInimigo extends Personagem{

    public PersonagemInimigo(String nome, int vida, int mana, int ataque, int defesa){
        super(nome, vida, mana, ataque, defesa, false);

        setAtaqueOriginal(getAtaque());
        setDefesaOriginal(getDefesa());
    }

    public abstract void executarTurno(
            Personagem jogadorAtivo,
            SistemaBatalha batalha
    );
}
