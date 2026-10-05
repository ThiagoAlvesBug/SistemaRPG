package model.itens;
import batalha.SistemaBatalha;
import model.Personagem;

public class PocaoMana extends Item{
    private int mana;

    public PocaoMana(){
        super("Poção de Mana");
        this.mana = 200;
    }

    @Override
    public void usar(Personagem usuario, SistemaBatalha batalha){
        usuario.receberMana(mana);
        batalha.adicionarLog(usuario.getNome() + " recuperou " + mana + " de mana.");
    }
}