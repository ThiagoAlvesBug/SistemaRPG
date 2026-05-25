package efeitos.buffs;

import batalha.SistemaBatalha;
import efeitos.EfeitoStatus;
import model.Personagem;

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