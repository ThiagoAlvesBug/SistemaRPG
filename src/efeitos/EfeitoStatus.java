package efeitos;
import batalha.SistemaBatalha;
import model.Personagem;

public abstract class EfeitoStatus {

    protected String nome;
    protected int duracao;

    public EfeitoStatus(String nome, int duracao){
        this.nome = nome;
        this.duracao = duracao;
    }
    public abstract void aplicar(Personagem alvo, SistemaBatalha batalha);

    public String getNome() {
        return nome;
    }
    public int getDuracao() {
        return duracao;
    }
    public void reduzirDuracao(){
        duracao--;
    }
    public boolean duracaoExpirou(){
        return duracao <= 0;
    }
    // Executando a cada turno
    // Modificando dano recebido
    public float modificarDanoRecebido(float dano){
        return dano;
    }
    public void aoExpirar(Personagem alvo, SistemaBatalha batalha){}
}
