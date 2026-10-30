package com.thiago.sistemarpg.habilidades.buffsEdebuffs;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.efeitos.debuffs.EfeitoJulgamento;
import com.thiago.sistemarpg.habilidades.Habilidades;
import com.thiago.sistemarpg.model.Personagem;

public class MarcaDoJulgamento implements Habilidades {
    @Override
    public String getNome(){
        return "Marca do Julgamento";
    }

    @Override
    public int getCustoMana(){
        return 20;
    }

    @Override
    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        int custoMana = getCustoMana();
        if(usuario.getMana() > custoMana){
            batalha.adicionarLog(usuario.getNome() + " não possui mana suficiente.");
            return;
        }
        usuario.setMana(usuario.getMana() - custoMana);
        alvo.adicionarEfeito(new EfeitoJulgamento(4));
    }
}