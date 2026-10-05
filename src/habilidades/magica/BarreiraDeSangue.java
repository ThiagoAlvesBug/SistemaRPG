package habilidades.magica;
import batalha.SistemaBatalha;
import efeitos.buffs.EfeitoBarreira;
import habilidades.Habilidades;
import model.Personagem;

public class BarreiraDeSangue implements Habilidades {

    @Override
    public String getNome(){
        return "Barreira de Sangue";
    }

    @Override
    public int getCustoMana(){
        return 0;
    }

    @Override
    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha){
        int custoVida = 20;

        if(usuario.getVida() <= custoVida){
            batalha.adicionarLog(usuario.getNome() + " não possui vida suficiente para sacrificar.");
            return;
        }
        usuario.setVida(usuario.getVida() - custoVida);
        usuario.adicionarEfeito(new EfeitoBarreira(3));
        batalha.adicionarLog(usuario.getNome() + " criou uma barreira de sangue! 🩸");
    }
}