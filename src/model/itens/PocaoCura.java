package model.itens;
import batalha.SistemaBatalha;
import model.Personagem;

public class PocaoCura extends Item{
    private int cura;

    public PocaoCura(){
        super("Poção de Cura");
        this.cura = 200;
    }

    @Override
    public void usar(Personagem usuario, SistemaBatalha batalha){
        usuario.receberVida(cura);
        batalha.adicionarLog(usuario.getNome() + " recuperou " + cura + " de vida.");
    }
}