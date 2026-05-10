package model;
import java.util.Scanner;

//    TODO: converter classe para 'abstract', impedindo que seja
//     diretamente instanciada, podendo instanciar apenas via classes filhas 'concretas'
public abstract class Personagem {
    public String nome;
    public float vida;
    public int mana;
    public float defesa;
    public int poçaoCura = 3;
    public int pocaoMana = 3;
    public float ataque;
    public boolean defendendo = false;

    // Constructor
    public Personagem(String nome, float vida, int mana, float defesa, float ataque, boolean defendendo){
        this.nome = nome;
        this.vida = vida;
        this.mana = mana;
        this.defesa = defesa;
        this.ataque = ataque;
        this.defendendo = false;
    }

    // Métodos
    public void atacar(Personagem inimigo){
        inimigo.receberDano(ataque);
        System.out.println("🗡️ " + nome + " atacou " + inimigo.nome);
    }
    public void defender(){
        defendendo = true;
        System.out.println(nome + " está defendendo! O dano recebido será reduzido.");
    }
    public void receberDano(float dano) {
        if (defendendo) {
            dano = dano / 2;
            defendendo = false;
            System.out.println("🛡️ " + nome + " se protegeu!");
        }

        float danoFinal = dano - defesa;
        if (danoFinal < 0) {
            danoFinal = 0;
        }
        vida -= danoFinal;

        if (vida < 0) {
            vida = 0;
        }
        System.out.println(nome + " recebeu " + danoFinal + " de dano.");
    }

    public boolean morto(){
        return vida <= 0;
    }

    public abstract void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo);


    public void mostrarStatus(){
        System.out.println();
        System.out.println("Nome: " + nome);
        System.out.println("HP: " + vida);
    }
}