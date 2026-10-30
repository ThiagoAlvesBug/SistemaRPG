package com.thiago.sistemarpg.efeitos.buffs;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.efeitos.EfeitoStatus;
import com.thiago.sistemarpg.model.Personagem;

public class EfeitoBarreira extends EfeitoStatus {

    public EfeitoBarreira(int duracao){
        super("Barreira de Sangue", duracao);
    }

    @Override
    public void aplicar(Personagem avlo, SistemaBatalha batalha){
        batalha.adicionarLog("🩸 Barreira ativa por " + duracao + " turno(s).");
        reduzirDuracao();
    }

    @Override
    public float modificarDanoRecebido(float dano){
        return 0;
    }

    @Override
    public void aoExpirar(Personagem alvo, SistemaBatalha batalha) {
        batalha.adicionarLog(alvo.getNome() + " perdeu a barreira.");
    }
}