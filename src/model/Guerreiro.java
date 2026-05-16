package model;

import menus.MenuGuerreiro;
import menus.MenuItens;

import java.util.Scanner;

public class Guerreiro extends Personagem{
    // Berserker
    private boolean berserkerAtivo = false;
    private int turnosBerserker = 0;
    // Pele de Aço
    private boolean peleAcoAtivo = false;
    private int turnosPeleAço = 0;
    // Construtor de Guerreiro
    public Guerreiro(String nome){
        super(nome,400,200,3,3,20,50,false);
        ataqueOriginal = ataque;
        defesaOriginal = defesa;
    }
    // Menu da Classe Guerreiro
    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo){
        MenuGuerreiro.abrir(this, scanner, inimigo);
    }
    @Override
    public void abrirMenuItens(Scanner scanner) { MenuItens.abrir(this, scanner); }
    /*__________Habilidades__________*/

    // Golpe devastador: um ataque que causa 200% do dano de ataque.
    public void golpeDevastador(Personagem inimigo){
        int custoMana = 20;
        if(mana < custoMana){
            System.out.println(nome + " não possui mana suficiente!");
            return;
        }
        mana -= custoMana;
        float dano = (float) (ataque * 2.0);
        System.out.println("⚔️ " + nome + " atingiu " + inimigo.nome + " com um ataque devastador! ⚔️");
        inimigo.receberDano(dano);
    }

    // Berserker: Aumenta o dano do ataque, mas também o dano recebido.
    public void berserker() {
        int custoMana = 25;
        if(mana < custoMana){
            System.out.println(nome + " não possui mana suficiente!");
            return;
        }
        if (!berserkerAtivo) {
            berserkerAtivo = true;
            mana -= custoMana;
            ataque = ataqueOriginal * 1.5f;
            defesa = defesaOriginal * 0.7f;
            turnosBerserker = 3;
            System.out.println("💢 " + nome + " entrou em modo Berserker! 💢");
        } else if(peleAcoAtivo){
            System.out.println("Berserker não pode ser usado junto com Pele de aço.");
        }
        else {
            System.out.println(nome + " já está no modo Berserker");
        }
    }
    // Pele de Aço: Dobra o valor de defesa do guerreiro.
    public void peleDeAco(){
        int custoMana = 35;
        if(mana < custoMana){
            System.out.println(nome + " não possui mana suficiente!");
            return;
        }
        if(!peleAcoAtivo){
            peleAcoAtivo = true;
            mana -= custoMana;
            defesa = defesaOriginal * 2.0f;
            turnosPeleAço = 3;
            System.out.println(nome + " revestiu sua pele com aço! 🦾");
        }
    }

    /*__________Aplicando Efeitos de status__________*/
    public void aplicandoEfeitos(){
        // Berserker
        if(berserkerAtivo){
            turnosBerserker--;
            System.out.println(turnosBerserker + " turnos de modo Berserker restante(s).");
            if(turnosBerserker <= 0){
                berserkerAtivo = false;
                ataque = ataqueOriginal;
                defesa = defesaOriginal;
                System.out.println(nome + " saiu do modo Berserker.");
            }
        }
        // Pele de Aço
        if(peleAcoAtivo){
            turnosPeleAço--;
            System.out.println(turnosPeleAço + " turnos de Pele de Aço restante(s).");
            if(turnosPeleAço <= 0){
                peleAcoAtivo = false;
                defesa = defesaOriginal;
                System.out.println(nome + " voltou a sentir o peso dos golpes.");
            }
        }
    }
}