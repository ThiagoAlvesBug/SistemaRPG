package model;
import java.util.Scanner;

public abstract class Personagem {
    public String nome;
    public float vida;
    public float mana;
    public float defesa;
    public int pocaoCura = 3;
    public int pocaoMana = 3;
    public float ataque;
    public boolean defendendo = false;

    // Constructor
    public Personagem(String nome, float vida, float mana, int pocaoCura, int pocaoMana,float defesa, float ataque, boolean defendendo){
        this.nome = nome;
        this.vida = vida;
        this.mana = mana;
        this.pocaoCura = pocaoCura;
        this.pocaoMana = pocaoMana;
        this.defesa = defesa;
        this.ataque = ataque;
        this.defendendo = false;

        this.defesaOriginal = defesa;
        this.ataqueOriginal = ataque;
        this.vidaMaxima = vida;
        this.manaMaxima = mana;
    }

    float defesaOriginal;
    float ataqueOriginal;
    float vidaMaxima;
    float manaMaxima;

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

    public void curarVida(){
        int curaVida = 100;

        vida += curaVida;
        if(vida > vidaMaxima){
            vida = vidaMaxima;
            System.out.println(" A vida já está no máximo!");
        }
        System.out.println(nome + " curou " + curaVida + " de vida.");
    }

    public void curarMana(){
        int curaMana = 100;

        mana += curaMana;
        if(mana > manaMaxima){
            mana = manaMaxima;
            System.out.println(" A mana já está no máximo!");
        }
        System.out.println(nome + " regenerou " + curaMana + " de mana.");
    }

    public boolean morto(){
        return vida <= 0;
    }

    public abstract void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo);

    public abstract void abrirMenuItens(Scanner scanner);

    public void mostrarStatus(){
        System.out.println();
        System.out.println("Nome: " + nome);
        System.out.println(">HP: " + vida);
        System.out.println(">Mana: " + mana);
    }
}