package com.thiago.sistemarpg.model.itens;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.model.Personagem;

public class PocaoMana extends Item{
    private int mana;

    public PocaoMana(){
        super("Poção de Mana");
        this.mana = 200;
    }

    @Override
    public void usar(Personagem usuario, SistemaBatalha batalha){
        usuario.receberMana(mana);
        batalha.adicionarLog(usuario.getNome() + " recuperou " + mana + " de mana.");
    }
}