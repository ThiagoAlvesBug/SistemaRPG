package model;
import batalha.SistemaBatalha;
import efeitos.EfeitoStatus;
import model.itens.Item;
import model.itens.PocaoCura;
import model.itens.PocaoMana;
import java.util.*;

public abstract class Personagem {
    List<EfeitoStatus> efeitos = new ArrayList<>();
    List<Item> inventario = new ArrayList<>();
    private String nome;
    private float vida;
    private float mana;
    private float defesa;
    private float ataque;
    private boolean defendendo = false;
    private float defesaOriginal;
    private float ataqueOriginal;
    private float vidaMaxima;
    private float manaMaxima;

    public List<Item> getInventario() {return inventario;}

    /* Getters & Setters */
    public String getNome() {return nome;}
    // VIDA
    public float getVida() {return vida;}
    public void setVida(float vida) {this.vida = vida;}
    // MANA
    public float getMana() {return mana;}
    public void setMana(float mana) {this.mana = mana;}
    // ATAQUE
    public float getAtaque() {return ataque;}
    public void setAtaque(float ataque) {this.ataque = ataque;}
    // DEFESA
    public float getDefesa() {return defesa;}
    public void setDefesa(float defesa) {this.defesa = defesa;}
    // DEFENDENDO
    public boolean isDefendendo() {return defendendo;}
    public void setDefendendo(boolean defendendo) {this.defendendo = defendendo;}
    // DEFESA ORIGINAL
    public float getDefesaOriginal() {return defesaOriginal;}
    public void setDefesaOriginal(float defesaOriginal) {this.defesaOriginal = defesaOriginal;}
    // ATAQUE ORIGINAL
    public float getAtaqueOriginal() {return ataqueOriginal;}
    public void setAtaqueOriginal(float ataqueOriginal) {this.ataqueOriginal = ataqueOriginal;}
    // VIDA MÁXIMA
    public float getVidaMaxima() {return vidaMaxima;}
    public void setVidaMaxima(float vidaMaxima) {this.vidaMaxima = vidaMaxima;}
    // MANA MÁXIMA
    public float getManaMaxima() { return manaMaxima; }
    public void setManaMaxima(float manaMaxima) {this.manaMaxima = manaMaxima;}

    // Constructor
    public Personagem(String nome, float vida, float mana, float defesa, float ataque, boolean defendendo) {
        this.nome = nome;
        this.vida = vida;
        this.mana = mana;
        this.defesa = defesa;
        this.ataque = ataque;
        this.defendendo = false;
        this.defesaOriginal = defesa;
        this.ataqueOriginal = ataque;
        this.vidaMaxima = vida;
        this.manaMaxima = mana;
    }

    /*__________MÉTODOS__________*/

    // ATACAR
    public void atacar(Personagem inimigo, SistemaBatalha batalha) {
        batalha.adicionarLog("🗡️ " + nome + " atacou " + inimigo.nome + "!");
        inimigo.receberDano(ataque, batalha);
    }
    // DEFENDER
    public void defender() {
        defendendo = true;
    }
    // RECEBER DANO
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
    // ADICIONAR EFEITO
    public void adicionarEfeito(EfeitoStatus efeito){
        efeitos.add(efeito);
    }
    // APLICAR EFEITO
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
    // VERIFICAR EFEITO
    public boolean possuiEfeito(String nomeEfeito){
        for(EfeitoStatus efeito : efeitos){
            if(efeito.getNome().equals(nomeEfeito)){
                return true;
            }
        }
        return false;
    }
    // CURAR VIDA
    public void receberVida(int cura) {
        vida += cura;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
            System.out.println(" A vida já está no máximo!");
        }
    }
    // CURAR MANA
    public void receberMana(int regenMana) {
        mana += regenMana;
        if (mana > manaMaxima) {
            mana = manaMaxima;
            System.out.println(" A mana já está no máximo!");
        }
    }
    // VERIFICAR MORTE
    public boolean morto() {
        return vida <= 0;
    }
    // QUANTIDADE DE POÇÕES DE VIDA
    public int getQuantidadePocaoVida() {
        int quantidade = 0;

        for(Item item : inventario){
            if(item instanceof PocaoCura){
                quantidade++;
            }
        }
        return quantidade;
    }
    // QUANTIDADE DE POÇÕES DE MANA
    public int getQuantidadePocaoMana() {
        int quantidade = 0;

        for(Item item : inventario){
            if(item instanceof PocaoMana){
                quantidade++;
            }
        }
        return quantidade;
    }
    // ADICIONAR ITEM
    public void adicionarItem(Item item){
        inventario.add(item);
    }
    // USAR ITEM
    public void usarItem(int indice, SistemaBatalha batalha) {
        if (indice < 0 || indice >= inventario.size()) {
            System.out.println("Item inválido.");
            return;
        }
        Item item = inventario.get(indice);
        item.usar(this, batalha);
        inventario.remove(indice);
    }
    // USAR ITEM
    public boolean usarItem(Class<? extends Item> tipoItem, SistemaBatalha batalha) {
        for(int i = 0; i < inventario.size(); i++){
            Item item = inventario.get(i);
            if(tipoItem.isInstance(item)){
                usarItem(i, batalha);
                return true;
            }
        }
        return false;
    }
    // ABRIR MENU DE HABILIDADES
    public abstract void abrirMenuHabilidades(Scanner scanner, Personagem alvo, SistemaBatalha batalha);
    // ABRIR MENU DE ITENS
    public abstract void abrirMenuItens(Scanner scanner, SistemaBatalha batalha);
    // MOSTRAR STATUS
    public void mostrarStatus() {
        System.out.println();
        System.out.println("Nome: " + nome);
        System.out.println(">HP: " + vida);
        System.out.println(">Mana: " + mana);
    }
}