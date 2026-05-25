package model;

import batalha.SistemaBatalha;
import efeitos.EfeitoStatus;

import java.util.*;

public abstract class Personagem {
    List<EfeitoStatus> efeitos = new ArrayList<>();
    public String nome;
    public float vida;
    public float mana;
    public float defesa;
    public int pocaoCura = 3;
    public int pocaoMana = 3;
    public float ataque;
    public boolean defendendo = false;

    //Getters & Setters
    public String getNome() {
        return nome;
    }
    public float getVida() {
        return vida;
    }
    public void setVida(float vida) {
        this.vida = vida;
    }
    public float getMana() {
        return mana;
    }
    public void setMana(float mana) {
        this.mana = mana;
    }
    public float getAtaque() {
        return ataque;
    }
    public void setAtaque(float ataque) {
        this.ataque = ataque;
    }
    public float getDefesa() {
        return defesa;
    }
    public void setDefesa(float defesa) {
        this.defesa = defesa;
    }
    public boolean isDefendendo() {
        return defendendo;
    }
    public void setDefendendo(boolean defendendo) {
        this.defendendo = defendendo;
    }
    public float getDefesaOriginal() {
        return defesaOriginal;
    }
    public void setDefesaOriginal(float defesaOriginal) {
        this.defesaOriginal = defesaOriginal;
    }
    public float getAtaqueOriginal() {
        return ataqueOriginal;
    }
    public void setAtaqueOriginal(float ataqueOriginal) {
        this.ataqueOriginal = ataqueOriginal;
    }
    public float getVidaMaxima() {
        return vidaMaxima;
    }
    public void setVidaMaxima(float vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }
    public float getManaMaxima() {
        return manaMaxima;
    }
    public void setManaMaxima(float manaMaxima) {
        this.manaMaxima = manaMaxima;
    }

    // Constructor
    public Personagem(String nome, float vida, float mana, int pocaoCura, int pocaoMana, float defesa, float ataque, boolean defendendo) {
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

    final String RED = "\u001B[31m";
    final String RESET = "\u001B[0m";

    // Métodos
    public void atacar(Personagem inimigo, SistemaBatalha batalha) {
        batalha.adicionarLog("🗡️ " + nome + " atacou " + inimigo.nome + "!");
        inimigo.receberDano(ataque, batalha);
    }

    public void defender() {
        defendendo = true;
    }

    public void receberDano(float dano, SistemaBatalha batalha) {
        for(EfeitoStatus efeito : efeitos){

            dano = efeito.modificarDanoRecebido(dano);
        }

        if (defendendo) {
            dano = dano / 2;
            defendendo = false;
            batalha.adicionarLog("🛡️ " + nome + " se defendeu!");
        }

        float danoFinal = dano - defesa;
        if (danoFinal < 0) {
            danoFinal = 0;
        }
        batalha.adicionarLog(nome + " recebeu " + danoFinal + " de dano.");
        vida -= danoFinal;

        if (vida < 0) {
            vida = 0;
        }
    }

    public void adicionarEfeito(EfeitoStatus efeito){
        efeitos.add(efeito);
    }

    public void aplicarEfeitos(SistemaBatalha batalha){
        Iterator<EfeitoStatus> iterator = efeitos.iterator();

        while(iterator.hasNext()){
            EfeitoStatus efeito = iterator.next();
            efeito.aplicar(this, batalha);

            if(efeito.duracaoExpirou()){
                efeito.aoExpirar(this, batalha);
                iterator.remove();
            }
        }
    }

    public boolean possuiEfeito(String nomeEfeito){
        for(EfeitoStatus efeito : efeitos){
            if(efeito.getNome().equals(nomeEfeito)){
                return true;
            }
        }
        return false;
    }

    public void curarVida() {
        int curaVida = 200;
        if (pocaoCura <= 0) {
            System.out.println("Sem poções de cura restante.");
        }

        vida += curaVida;
        pocaoCura--;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
            System.out.println(" A vida já está no máximo!");
        }
        System.out.println(nome + " curou " + curaVida + " de vida.");
    }

    public void curarMana() {
        int curaMana = 200;
        if (pocaoMana <= 0) {
            System.out.println("Sem poções de mana restante.");
        }

        mana += curaMana;
        pocaoMana--;
        if (mana > manaMaxima) {
            mana = manaMaxima;
            System.out.println(" A mana já está no máximo!");
        }
        System.out.println(nome + " regenerou " + curaMana + " de mana.");
    }

    public boolean morto() {
        return vida <= 0;
    }

    public abstract void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo, SistemaBatalha batalha);

    public abstract void abrirMenuItens(Scanner scanner);

    public void mostrarStatus() {
        System.out.println();
        System.out.println("Nome: " + nome);
        System.out.println(">HP: " + vida);
        System.out.println(">Mana: " + mana);
    }
}