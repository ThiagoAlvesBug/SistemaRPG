package habilidades.fisica;

import batalha.SistemaBatalha;
import habilidades.Habilidades;
import model.Personagem;

public class ExecucaoCondenada implements Habilidades {
    @Override
    public String getNome() {
        return "Execução Condenada";
    }

    @Override
    public int getCustoMana() {
        return 25;
    }

    @Override
    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        int dano = 50;
        int custoMana = getCustoMana();
        if (usuario.getMana() < custoMana) {
            batalha.adicionarLog(usuario.getNome() + " não tem mana suficiente.");
            return;
        }
        usuario.setMana(usuario.getMana() - custoMana);
        if (alvo.possuiEfeito("Marca do Julgamento")) {
            dano *= 3;
            batalha.adicionarLog("☠️ A marca amplificou o dano!");
        }
        batalha.adicionarLog(usuario.getNome() + " usou Execução Condenada!");
        alvo.receberDano(dano, batalha);
    }
}