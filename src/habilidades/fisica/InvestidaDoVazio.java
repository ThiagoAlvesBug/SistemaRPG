package habilidades.fisica;
import batalha.SistemaBatalha;
import habilidades.Habilidades;
import model.Personagem;

public class InvestidaDoVazio implements Habilidades {

    @Override
    public String getNome(){
        return "Investida do Vazio";
    }
    @Override
    public int getCustoMana(){
        return 30;
    }

    @Override
    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        float dano = 70;
        int custoMana = getCustoMana();

        if(usuario.getMana() < custoMana){
            batalha.adicionarLog(usuario.getNome() + " não possui mana suficiente!");
            return;
        }
        usuario.setMana(getCustoMana() - custoMana);
        batalha.adicionarLog(alvo.getNome() + " foi atingido por Investida Do Vazio. 🕳️");
        batalha.adicionarLog(usuario.getNome() + " deixou um rastro de destruição.");
        alvo.receberDano(dano, batalha);

    }
}