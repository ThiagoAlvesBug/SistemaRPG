package habilidades;
import model.Maga;
import model.Personagem;

public enum HabilidadesMaga {

    BOLA_DE_FOGO{
        @Override
        public void usar(Maga usuario, Personagem inimigo){
            usuario.bolaDeFogo(inimigo);
        }
    },
    RAJADA_ARCANA{
        @Override
        public void usar(Maga usuario, Personagem inimigo){
            usuario.rajadaArcana(inimigo);
        }
    },
    BARREIRA_DE_SANGUE{
        @Override
        public void usar(Maga usuario, Personagem inimigo){ usuario.barreiraDeSangue(); }
    };

    public abstract void usar(Maga usuario, Personagem inimigo);
}
