package efeitos.buffs;
import batalha.SistemaBatalha;
import efeitos.EfeitoStatus;
import model.Personagem;

public class EfeitoBerserker extends EfeitoStatus {
    private boolean aplicado = false;

    public EfeitoBerserker(int duracao){
        super("Modo Berserker", duracao);
    }

    @Override
    public void aplicar(Personagem alvo, SistemaBatalha batalha){
        if(!aplicado){
            alvo.setDefesa(alvo.getDefesaOriginal() * 0.7f);
            alvo.setAtaque(alvo.getAtaqueOriginal() * 1.5f);
            batalha.adicionarLog("💢 " + alvo.getNome() + " entrou em modo Berserker! 💢");
        }
        batalha.adicionarLog("Modo Berserker ativa por mais " + duracao + " turno(s).");
        duracao--;

        if(duracao <= 0){
            alvo.setDefesa(alvo.getDefesaOriginal());
            alvo.setAtaque(alvo.getAtaqueOriginal());
            batalha.adicionarLog(alvo.getNome() + " saiu do modo Berserker.");
        }
    }
}
