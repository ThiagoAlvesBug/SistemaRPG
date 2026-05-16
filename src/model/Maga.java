package model;

import menus.MenuItens;
import menus.MenuMaga;
import java.util.Random;
import java.util.Scanner;

public class Maga extends Personagem{
    // Queimadura
    private boolean queimadura = false;
    private int turnosQueimadura = 0;
    // Escudo de Energia
    private boolean barreiraAtiva = false;
    private int turnosBarreira = 0;
    // Construtor de Maga
    public Maga(String nome){
        super(nome,350,250,3,3,40,20,false);
        ataqueOriginal = ataque;
        defesaOriginal = defesa;
    }

    // Menu da Classe Maga
    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo){
        MenuMaga.abrir(this, scanner, inimigo);
    }

    @Override
    public void abrirMenuItens(Scanner scanner) { MenuItens.abrir(this, scanner); }
    /*__________Habilidades__________*/

    //   Bola de fogo: lança uma bola de fogo que causa status de queimadura,
    //   perdendo uma pequena quantidade de vida a cada turno (por 4 turnos).
    public void bolaDeFogo(Personagem inimigo){
        float dano = 30;
        // Custo de mana da Bola de Fogo.
        int custoMana = 25;
        if(mana < custoMana){
            System.out.println(nome + " não possui mana suficiente!");
            return;
        }
        mana -= custoMana;

        inimigo.receberDano(dano);
        queimadura = true;
        turnosQueimadura += 4;
        System.out.println("🟠 " + nome + " atacou " + inimigo.nome + " com bola de fogo. 🟠");
        System.out.println(inimigo.nome + " está em chamas! 🔥");
    }
    //  Rajada Arcana: dispara 5 misseis, cada um causando 10 de dano.
    //  Cada disparo tem uma chance de acertar o inimigo (dano causado pode variar de 0 a 50).
    public void rajadaArcana(Personagem inimigo){
        int qtdeDisparos = 5;
        float percentualChangeAcertar = 0.5f; // de 0 até 0.99999
        int danoPorDisparo = 10;
        // Custo de mana de Rajada Arcana
        int custoMana = 40;
        if(mana < custoMana){
            System.out.println(nome + " não possui mana suficiente!");
        }
        mana -= custoMana;
        // Definindo, de maneira aleatória, quantos disparos atingiram o alvo.
        int qtdeDisparosAcertados = 0;
        var random = new Random();
        for (int indiceDisparo = 0;  indiceDisparo < qtdeDisparos; indiceDisparo++){
            // TODO: pesquisar range de retorno de valores do nextFloat (se vai até 1 ou até outro numero)
            if(random.nextFloat() <= percentualChangeAcertar){
                qtdeDisparosAcertados++;
            }
        }
        int dano = qtdeDisparosAcertados * danoPorDisparo;

        inimigo.receberDano(dano);
        System.out.println(nome + " atacou " + inimigo.nome + " com uma rajada de projéteis arcanos.");
        System.out.println("Acertou " + qtdeDisparosAcertados + "/" + qtdeDisparos + " disparos.");
    }
    //  Escudo de Energia: o dano recebido na rodada será redirecionado para a mana.
    //  Jogador perde mana ao invés de vida.
    public void barreiraDeSangue(){
        int dano = 20;
        if(vida < dano){
            System.out.println(nome + " não possui vida suficiente para sacrificar!");
            return;
        }

        if(!barreiraAtiva){
            barreiraAtiva = true;
            vida -= dano;
            turnosBarreira = 3;
            System.out.println(nome + " criou uma barreira de sangue! 🩸" + " -" + dano + " de vida.");
        }
    }
    // Receber dano: Comportamentos diferentes dependendo das habilidades defensivas ativas.
    @Override
    public void receberDano(float dano){
        if(defendendo && barreiraAtiva){
            dano = dano/ 2;
            mana -= dano;

            System.out.println("O dano causado na barreira foi reduzido.");
            System.out.println(nome + " recebeu " + dano + " de dano.");
        }
        else if(barreiraAtiva){
            mana -= dano;

            if(mana < 0){
                mana = 0;
            }
            System.out.println("🩸 A barreira absorveu " + dano + " de dano.");
        } else{
            super.receberDano(dano);
        }
    }

    /*__________Aplicando Efeitos de status__________*/
    public void aplicandoEfeitos(Personagem inimigo){
        // Escudo de Energia
        if(barreiraAtiva){
            turnosBarreira--;
            System.out.println("🩸 Barreira ativa por mais " + turnosBarreira + " turno(s).");

            if(turnosBarreira <= 0){
                barreiraAtiva = false;
                System.out.println(nome + " perdeu sua barreira.");
            }
        }
        // Queimadura por bola de fogo
        if(queimadura){
            turnosQueimadura--;
            System.out.println(turnosQueimadura + " turnos de queimadura restante. 🔥");
            float danoQueimadura = 5;

            inimigo.receberDano(danoQueimadura);
            if(turnosQueimadura <= 0){
                queimadura = false;
                System.out.println(inimigo.nome + " parou de queimar.");
            }
        }
    }
}

