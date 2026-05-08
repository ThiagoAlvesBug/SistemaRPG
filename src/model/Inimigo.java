package model;

public class Inimigo extends Personagem{
    public Inimigo(String nome){
        super(nome, 200, 25);
    }
    @Override
    public void mostrarStatus(){
        System.out.println();
        System.out.println("Inimigo: " + nome);
        System.out.println("HP: " + vida + "\n");
    }
}