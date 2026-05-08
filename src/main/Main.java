import model.*;
import static service.Configuracoes.*;

void main() {
    Scanner scanner = new Scanner(System.in);
    Guerreiro guerreiro = new Guerreiro("Nero");
    Maga maga = new Maga("Jade");
    Personagem jogadorAtivo = guerreiro; // começando com o guerreiro

    Inimigo inimigo = new Inimigo("Imlerith");
    boolean inBattle = true;

    System.out.println("_____BATALHA_INICIADA_____");
    // ✅ TODO: mover prints de linha vazia para dentro dos métodos,
    //     podendo utilizar '\n' como alternativa

    while(inBattle){

        //limpaTela();
        jogadorAtivo.mostrarStatus();
        inimigo.mostrarStatus();

        System.out.println("O que deseja fazer?");
        System.out.println("[1] Atacar");
        System.out.println("[2] Defender");
        System.out.println("[3] Alterar Personagem");
        System.out.println("[4] Fugir");

        // ✅ TODO: Tratar opções inválidas não numéricasint opcao;
        int opcao;

        if(scanner.hasNextInt()){
            opcao = scanner.nextInt();
        } else{
            System.out.println("Digite apenas números!");

            scanner.next(); // limpa entrada inválida
            continue;
        }
        // Ações
        System.out.println();
        switch (opcao){
            case 1:
                jogadorAtivo.atacar(inimigo);
                break;

            case 2:
                jogadorAtivo.defender();
                break;

            case 3:
                if(jogadorAtivo == guerreiro){
                    if(maga.vida > 0){ // Guerreiro só troca para Maga se ela estiver viva.
                        jogadorAtivo = maga;
                        System.out.println("Personagem alterado para: " + jogadorAtivo.nome);
                    } else{
                        System.out.println(maga.nome + " está morto.");
                    }
                } else{
                    if(guerreiro.vida > 0){
                        jogadorAtivo = guerreiro;
                        System.out.println("Personagem alterado para: " + jogadorAtivo.nome);
                    } else{
                        System.out.println(guerreiro.nome + " está morto.");
                    }
                }
                continue;

            case 4:
                System.out.println(jogadorAtivo.nome + " fugiu em segurança.");
                inBattle = false;
                continue;

            default:
                System.out.println("Opção inválida.");
                continue;
        }

        // Turno do inimigo
        if(inimigo.vida > 0){
            System.out.println("--------------------");
            inimigo.atacar(jogadorAtivo);
        }
        // Verificando morte de inimigo
        if (inimigo.vida <= 0) {
            System.out.println("🌟 " + inimigo.nome + " derrotado!2 🌟");
            System.out.println();
            System.out.println("Batalha encerrada.");
            inBattle = false;
        } // Se vida <= 0, personagem morreu.
        else if(jogadorAtivo.vida <= 0){
            System.out.println("❌ " + jogadorAtivo.nome + " morreu!");
            System.out.println();
            // Guerreiro troca para Maga
            if(jogadorAtivo == guerreiro && maga.vida > 0){
                jogadorAtivo = maga;
                System.out.println("⬆️ " + maga.nome + " entrou na batalha.");

            } // Maga troca para guerreiro
            else if(jogadorAtivo == maga && guerreiro.vida > 0){
                jogadorAtivo = guerreiro;
                System.out.println("⬆️ " + guerreiro.nome + " entrou na batalha.");

            } // Todos os jogadores morreram
            else {
                System.out.println("❌ " + maga.nome + "e" + guerreiro.nome + " foram derrotados.");
                inBattle = false;
            }
        }
    }
}