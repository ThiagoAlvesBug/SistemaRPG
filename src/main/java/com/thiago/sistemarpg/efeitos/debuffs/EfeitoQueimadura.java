package com.thiago.sistemarpg.efeitos.debuffs;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.efeitos.EfeitoStatus;
import com.thiago.sistemarpg.model.Personagem;

public class EfeitoQueimadura extends EfeitoStatus {
    public EfeitoQueimadura(int duracao){
        super("Queimadura", duracao);
    }

    @Override
    public void aplicar(Personagem alvo,SistemaBatalha batalha){
        float dano = 5;
        alvo.receberDano(dano,batalha);
        batalha.adicionarLog("🔥 " + alvo.getNome() + " sofre " + dano + " de dano pela queimadura!");
        batalha.adicionarLog("Turnos de queimadura restante: " + getDuracao());
        reduzirDuracao();
    }
}
