package Batalha;
import model.*;
import java.util.*;

public class SistemaBatalha {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    // Declarando atributos
    private Guerreiro guerreiro;
    private Maga maga;
    private Personagem jogadorAtivo;
    private Inimigo inimigo;
    boolean inBattle = true;

    /*__________Batalha__________*/

    // Todas as ações de batalha dividas em métodos.
    public void iniciar(){
        // Instanciando objetos
        guerreiro = new Guerreiro("Nero");
        maga = new Maga("Jade");
        inimigo = new Inimigo("Eredin");
        // Iniciando com personagem aleatório (nextBoolean retorna true ou false, aleatoriamente)
        if(random.nextBoolean()){jogadorAtivo = guerreiro;}   else{jogadorAtivo = maga;}
        // Cabeçalho de Batalha
        mostrarCabeçalho();
        System.out.println("Iniciando batalha com: " + jogadorAtivo.nome);
        // Batalha
        while(inBattle){
            mostrarMenu();

            int opcao = lerAcao();
            executarAcao(opcao);

            verificarFimDeBatalha();
            aplicarEfeitos();
            turnoInimigo();
            verificarMorteJogador();
        }
    }
    // Cabeçalho
    private void mostrarCabeçalho(){
        System.out.println("=====================");
        System.out.println("=      Batalha      =");
        System.out.println("=====================");
        System.out.println();
    }
    // Menu de batalha
    private void mostrarMenu(){
        jogadorAtivo.mostrarStatus();
        inimigo.mostrarStatus();
        System.out.println("[1] Atacar");
        System.out.println("[2] Habilidades");
        System.out.println("[3] Defender");
        System.out.println("[4] Alterar Personagem");
        System.out.println("[5] Fugir");
    }
    // Lendo input do jogador
    private int lerAcao(){
        int opcao;

        if(scanner.hasNextInt()){
            opcao = scanner.nextInt();
        }else {
            System.out.println("Digite apenas números!");
            scanner.next();
            return -1;
        }
        return opcao;
    }
    // Executando a ação com base na opção selecionada
    private void executarAcao(int opcao){
        switch (opcao){
            case 1 -> jogadorAtivo.atacar(inimigo);
            case 2 -> jogadorAtivo.abrirMenuHabilidades(scanner, inimigo);
            case 3 -> jogadorAtivo.defender();
            case 4 -> trocarPersonagem();
            case 5 -> {
                System.out.println(jogadorAtivo.nome + "Fugiu em segurança 💨");
                inBattle = false;
            }
            default -> System.out.println("Opção inválida.");
        }
    }
    // Troca de personagens
    private void trocarPersonagem() {
        if(jogadorAtivo == guerreiro){
            if(maga.vida > 0){
                jogadorAtivo = maga;
                System.out.println("Personagem alterado para: " + jogadorAtivo.nome);
            } else{
                System.out.println(maga.nome + " está morto.");
            }
        } else {
            if(guerreiro.vida > 0){
                jogadorAtivo = guerreiro;
                System.out.println("Personagem alterado para: " + jogadorAtivo.nome);
            } else{
                System.out.println(guerreiro.nome + " está morto.");
            }
        }
    }
    // Efeitos de batalha (efeitos de status)
    private void aplicarEfeitos(){
        if(jogadorAtivo instanceof Guerreiro){
            ((Guerreiro) jogadorAtivo).aplicandoEfeitos();
        }
        if(jogadorAtivo instanceof Maga){
            ((Maga) jogadorAtivo).aplicandoEfeitos(inimigo);
        }
    }
    // Turno do inimigo
    private void turnoInimigo() {
        System.out.println("--------------------");
        if(inBattle && inimigo.vida > 0) {
            inimigo.atacar(jogadorAtivo);
        }
    }
    // Verificando morte de algum personagem e efetuando a troca entre personagens.
    private void verificarMorteJogador(){
        // Se vida <= 0, personagem morreu.
        if(jogadorAtivo.vida <= 0){
            System.out.println("❌ " + jogadorAtivo.nome + " morreu!");
            System.out.println();
            // Guerreiro troca para Maga
            if(jogadorAtivo == guerreiro && maga.vida > 0){
                jogadorAtivo = maga;
                System.out.println("⬆️ " + maga.nome + " entrou na batalha.");
            }
            // Maga troca para guerreiro
            else if(jogadorAtivo == maga && guerreiro.vida > 0){
                jogadorAtivo = guerreiro;
                System.out.println("⬆️ " + guerreiro.nome + " entrou na batalha.");
            }
            // Todos os jogadores morreram
            else {
                System.out.println("❌ " + maga.nome + "e" + guerreiro.nome + " foram derrotados.");
                inBattle = false;
            }
        }
    }
    // Verificando o fim da batalha
    public void verificarFimDeBatalha(){
        if (inimigo.morto()) {
            System.out.println("🌟 !" + inimigo.nome + " derrotado! 🌟");
            System.out.println("Batalha encerrada.");
            inBattle = false;
        }
    }
}
