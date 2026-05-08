package model;

// TODO: converter classe para 'abstract', impedindo que seja
//  diretamente instanciada, podendo instanciar apenas via classes filhas 'concretas'
public abstract class Personagem {
    public String nome;
    public float vida;
    public float ataque;
    private boolean defendendo = false;

    // Constructor
    public Personagem(String nome, float vida, float ataque){
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
    }

    // Métodos
    public void atacar(Personagem inimigo){
        inimigo.receberDano(ataque);
        System.out.println("⚔️ " + nome + " atacou " + inimigo.nome);
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
        vida -= dano;
    }
    public void mostrarStatus(){
        System.out.println();
        System.out.println("Nome: " + nome);
        System.out.println("HP: " + vida);
    }

    // ✅ TODO: Converter método para printStatus() e marcá-lo como 'virtual',
    //     para poder sobrepor sua implementação nas classes filhas.
/*
    public void statusPersonagem(){
        System.out.println();
        System.out.println("Nome: " + nome);
        System.out.println("HP: " + vida);
    }
*/
    // ✅ TODO: Alterar método para a classe de Inimigo como 'override',
    //     sobrepondo a versão da classe pai.
/*
    public void statusInimigo(){
        System.out.println();
        System.out.println("Inimigo: " + nome);
        System.out.println("HP: " + vida + "\n");
    }
*/
}