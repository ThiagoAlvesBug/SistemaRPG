package habilidades.magica;

import batalha.SistemaBatalha;
import efeitos.debuffs.EfeitoQueimadura;
import habilidades.Habilidades;
import model.Personagem;

public class BolaDeFogo implements Habilidades {

    @Override
    public String getNome() {
        return "Bola de Fogo";
    }

    @Override
    public int getCustoMana() {
        return 25;
    }

    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        int custoMana = getCustoMana();
        float dano = 30;
        if(usuario.getMana() < custoMana){
            batalha.adicionarLog(usuario.getNome() + " não possui mana suficiente!");
            return;
        }
        usuario.setMana(usuario.getMana() - custoMana);

        batalha.adicionarLog("🟠 " + usuario.getNome() + " lançou Bola de Fogo! 🟠");
        alvo.receberDano(dano, batalha);

        alvo.adicionarEfeito(new EfeitoQueimadura(4));
        batalha.adicionarLog(alvo.getNome() + " está em chamas! 🔥");
    }
}