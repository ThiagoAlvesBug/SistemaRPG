package efeitos.debuffs;

import batalha.SistemaBatalha;
import efeitos.EfeitoStatus;
import model.Personagem;

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
