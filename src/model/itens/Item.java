package model.itens;
import batalha.SistemaBatalha;
import model.Personagem;

public abstract class Item {
    protected String nome;
    protected String descricao;

    public Item(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public abstract void usar(Personagem usuario, SistemaBatalha batalha);
}