package habilidades;
import model.Guerreiro;
import model.Personagem;

public enum HabilidadeGuerreiro {

    GOLPE_DEVASTADOR {
        @Override
        public void usar(Guerreiro usuario, Personagem inimigo) {
            usuario.golpeDevastador(inimigo);
        }
    },
    BERSERKER{
        @Override
        public void usar(Guerreiro usuario, Personagem inimigo) { usuario.berserker(); }
    },
    PELE_DE_ACO{
        @Override
        public void usar(Guerreiro usuario, Personagem Inimigo){
            usuario.peleDeAco();
        }
    };

    public abstract void usar(Guerreiro usuario, Personagem inimigo);
}


