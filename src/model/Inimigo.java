package model;
import java.util.Scanner;

public class Inimigo extends Personagem{
    public Inimigo(String nome){
        super(nome,200,100,0,40,false);
    }

    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo) {

    }

    @Override
    public void mostrarStatus(){
        System.out.println();
        System.out.println("Inimigo: " + nome);
        System.out.println("HP: " + vida + "\n");
    }
}