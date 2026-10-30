package com.thiago.sistemarpg.habilidades.buffsEdebuffs;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.efeitos.buffs.EfeitoPeleDeAco;
import com.thiago.sistemarpg.habilidades.Habilidades;
import com.thiago.sistemarpg.model.Personagem;

public class PeleDeAco implements Habilidades {
    @Override
    public String getNome(){
        return "Pele de Aço";
    }
    @Override
    public int getCustoMana(){
        return 35;
    }


    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        int custoMana = getCustoMana();

        if(usuario.getMana() < custoMana){
            System.out.println(usuario.getNome() + " não possui mana suficiente!");
            return;
        }
        usuario.setMana(usuario.getMana() - custoMana);
        usuario.adicionarEfeito(new EfeitoPeleDeAco(3));
    }
}