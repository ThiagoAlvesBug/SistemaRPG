package efeitos.buffs;
import batalha.SistemaBatalha;
import efeitos.EfeitoStatus;
import model.Personagem;

public class EfeitoPeleDeAco extends EfeitoStatus {
    private boolean aplicado = false;

    public EfeitoPeleDeAco(int duracao){
        super("Pele de Aço", duracao);
    }

    @Override
    public void aplicar(Personagem alvo, SistemaBatalha batalha){
        if(!aplicado){
            alvo.setDefesa(alvo.getDefesaOriginal() * 2.0f);
            batalha.adicionarLog(alvo.getNome() + " revestiu sua pele com aço! 🦾");
            aplicado = true;
        }
        batalha.adicionarLog("Pele de Aço ativa por mais " + duracao + " turno(s).");
        duracao--;

        if(duracao <= 0){
            alvo.setDefesa(alvo.getDefesaOriginal());
            batalha.adicionarLog(alvo.getNome() + " voltou a sentir o peso dos golpes.");
        }
    }
}
