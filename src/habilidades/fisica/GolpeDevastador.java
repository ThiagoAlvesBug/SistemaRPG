package habilidades.fisica;
import batalha.SistemaBatalha;
import habilidades.Habilidades;
import model.Personagem;

public class GolpeDevastador implements Habilidades {
    @Override
    public String getNome(){
        return "Golpe Devastador";
    }
    @Override
    public int getCustoMana(){
        return 20;
    }

    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha){
        int custoMana = getCustoMana();
        if(usuario.getMana() < custoMana){
            System.out.println(usuario.getNome() + " não possui mana suficiente!");
            return;
        }
        usuario.setMana(usuario.getMana() - custoMana);
        float dano = (float) (usuario.getAtaque() * 2.0);
        batalha.adicionarLog("⚔️ " + usuario.getNome() + " atingiu " + alvo.getNome() + " com um ataque devastador! ⚔️");
        alvo.receberDano(dano, batalha);
    }
}