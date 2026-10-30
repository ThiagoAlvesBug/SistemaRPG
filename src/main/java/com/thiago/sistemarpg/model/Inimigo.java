package com.thiago.sistemarpg.model;
import com.thiago.sistemarpg.batalha.SistemaBatalha;

import java.util.Scanner;

public class Inimigo extends PersonagemInimigo{

    public Inimigo(String nome){
        super(nome,200,100,0,45);
        setAtaqueOriginal(getAtaque());
        setDefesaOriginal(getDefesa());
    }

    @Override
    public void executarTurno(Personagem jogadorAtivo, SistemaBatalha batalha){
        atacar(jogadorAtivo, batalha);
    }

    @Override
    public void abrirMenuHabilidades(Scanner scanner, Personagem alvo, SistemaBatalha batalha) { }

    @Override
    public void abrirMenuItens(Scanner scanner, SistemaBatalha batalha) { }


}
