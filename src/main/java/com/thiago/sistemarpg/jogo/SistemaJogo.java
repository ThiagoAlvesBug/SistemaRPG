package com.thiago.sistemarpg.jogo;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.model.Chefe;
import com.thiago.sistemarpg.model.Guerreiro;
import com.thiago.sistemarpg.model.Inimigo;
import com.thiago.sistemarpg.model.Mago;
import java.util.Scanner;

public class SistemaJogo {

    private final Guerreiro guerreiro;
    private final Mago mago;
    private final Scanner scanner;

    public SistemaJogo(Guerreiro guerreiro, Mago mago){
        this.guerreiro = guerreiro;
        this.mago = mago;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() throws Exception{

        System.out.println("================================");
        System.out.println("=     JOGUINHO DE TERMINAL     =");
        System.out.println("================================");

        System.out.println();
        System.out.println("A aventura se inicia!");
        System.out.println();

        escolherCaminho();
    }

    private void escolherCaminho() throws Exception {

        System.out.println("Você chegou a uma bifurcação.");
        System.out.println();
        System.out.println("[1] Seguir pela floresta");
        System.out.println("[2] Entrar na caverna");
        System.out.println();

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> caminhoFloresta();
            case 2 -> caminhoCaverna();
            default -> {
                System.out.println("Opção inválida.");
                escolherCaminho();
            }
        }
    }

    private void caminhoFloresta() throws Exception {

        System.out.println();
        System.out.println("Você entrou na floresta...");
        System.out.println("Um inimigo apareceu!");
        System.out.println();

        SistemaBatalha batalha =
                new SistemaBatalha(guerreiro, mago, new Inimigo("Nihlathak"));

        batalha.iniciar();
    }

    private void caminhoCaverna() throws Exception {

        System.out.println();
        System.out.println("Você entrou na caverna...");
        System.out.println("Um inimigo apareceu!");
        System.out.println();

        SistemaBatalha batalha =
                new SistemaBatalha(guerreiro, mago, new Chefe("Eredin"));

        batalha.iniciar();
    }

}
