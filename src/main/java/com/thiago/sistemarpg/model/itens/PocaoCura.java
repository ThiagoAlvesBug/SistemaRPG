package com.thiago.sistemarpg.model.itens;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.model.Personagem;

public class PocaoCura extends Item{
    private int cura;

    public PocaoCura(){
        super("Poção de Cura");
        this.cura = 200;
    }

    @Override
    public void usar(Personagem usuario, SistemaBatalha batalha){
        usuario.receberVida(cura);
        batalha.adicionarLog(usuario.getNome() + " recuperou " + cura + " de vida.");
    }
}