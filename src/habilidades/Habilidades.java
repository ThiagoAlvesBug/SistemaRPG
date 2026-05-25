package habilidades;

import batalha.SistemaBatalha;
import model.Personagem;

public interface Habilidades {

    String getNome();
    int getCustoMana();

    void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha);
}
