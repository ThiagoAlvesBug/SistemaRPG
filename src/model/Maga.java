package model;

import java.util.Random;
import java.util.Scanner;

public class Maga extends Personagem{

    private boolean queimadura = false;
    private int turnosQueimadura = 0;

    public Maga(String nome){
        super(nome,100,200,10,20,false);
    }

    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo){
            mostrarStatus();
            inimigo.mostrarStatus();
            System.out.println("====================");
            System.out.println("=   Habilidades    =");
            System.out.println("====================");
            System.out.println("[1] Bola de fogo 🔥");
            System.out.println("[2] Rajada arcana 🪄");
            System.out.println("[3] Escudo de energia 🔵");
            System.out.println("[4] Voltar");

            int opcao;
            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            } else{
                System.out.println("Digite apenas números!");

                scanner.next(); // limpa entrada inválida
                return;
            }
        switch (opcao) {

            case 1 -> bolaDeFogo(inimigo);

            case 2 -> rajadaArcana(inimigo);

            case 3 -> escudoDeEnergia();

            case 4 -> {
                return;
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    //  TODO: Bola de fogo, com uma pequena chance(%) de deixar o inimigo queimando,
    //   perdendo uma pequena quantidade de vida a cada turno (por 3 turnos).
    public void bolaDeFogo(Personagem inimigo){
        float dano = 30;
        inimigo.receberDano(dano);
        queimadura = true;
        turnosQueimadura += 4;
        System.out.println("🟠 " + nome + " atacou " + inimigo.nome + " com bola de fogo. 🟠");
        System.out.println(inimigo.nome + " está em chamas! 🔥");
    }

    // TODO: Rajada Arcana: dispara 5 misseis, cada um causando 10 de dano.
    //  Cada disparo tem uma chance de acertar o inimigo (dano causado pode variar de 0 a 50).
    //  sout precisa informar quantos disparos acertaram o inimigo.
    public void rajadaArcana(Personagem inimigo){
        int qtdeDisparos = 5;
        float percentualChangeAcertar = 0.5f; // de 0 até 0.99999
        int danoPorDisparo = 10;

        int qtdeDisparosAcertados = 0;
        var rand = new Random();

        for (int indiceDisparo = 0;  indiceDisparo < qtdeDisparos; indiceDisparo++){
            // TODO: pesquisar range de retorno de valores do nextFloat (se vai até 1 ou até outro numero)
            if(rand.nextFloat() <= percentualChangeAcertar){
                qtdeDisparosAcertados++;
            }
        }

        int dano = qtdeDisparosAcertados * danoPorDisparo;
        inimigo.receberDano(dano);
        System.out.println(nome + " atacou " + inimigo.nome + " com uma rajada de projéteis arcanos.");
        System.out.println("Acertou " + qtdeDisparosAcertados + "/" + qtdeDisparos + " disparos.");
    }

    // TODO: Escudo de Energia: o dano recebido na rodada será redirecionado para a mana.
    //  Jogador perde mana ao invés de vida.
    public void escudoDeEnergia(){
        System.out.println("Em breve.");
    }

    public void atualizarEfeitos(Personagem inimigo){
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

