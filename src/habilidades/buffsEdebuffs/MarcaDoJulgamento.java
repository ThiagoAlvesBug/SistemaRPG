package habilidades.buffsEdebuffs;
import batalha.SistemaBatalha;
import efeitos.debuffs.EfeitoJulgamento;
import habilidades.Habilidades;
import model.Personagem;

public class MarcaDoJulgamento implements Habilidades {
    @Override
    public String getNome(){
        return "Marca do Julgamento";
    }

    @Override
    public int getCustoMana(){
        return 20;
    }

    @Override
    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        int custoMana = getCustoMana();
        if(usuario.getMana() > custoMana){
            batalha.adicionarLog(usuario.getNome() + " não possui mana suficiente.");
            return;
        }
        usuario.setMana(usuario.getMana() - custoMana);
        alvo.adicionarEfeito(new EfeitoJulgamento(4));
    }
}