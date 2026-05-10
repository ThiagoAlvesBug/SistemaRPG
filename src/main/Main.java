import model.*;
import static service.Configuracoes.*;

/*
Ao atacar:
HP: 100
HP: 100 -15
HP: 85

Menu de Personagens:
> Guerreiro Nero   HP: 120   (Habilidade ativa por +1 turnos: )
  Maga Jade        HP: 100

###################################################
#####  [1] ...   [1] ...   [1] ...   [1] ...  #####
###################################################

TODO: Tentar implementar em forms com C# ou JavaFX
*/

void main() {
    Scanner scanner = new Scanner(System.in);
    Guerreiro guerreiro = new Guerreiro("Nero");
    Maga maga = new Maga("Jade");
    Personagem jogadorAtivo = guerreiro; // começando com o guerreiro

    Inimigo inimigo = new Inimigo("Caranthir");
    boolean inBattle = true;

    System.out.println("=====================");
    System.out.println("=      Batalha      =");
    System.out.println("=====================");
    System.out.println();

    while(inBattle){

        //limpaTela();
        jogadorAtivo.mostrarStatus();
        inimigo.mostrarStatus();

        System.out.println("[1] Atacar");
        System.out.println("[2] Habilidades");
        System.out.println("[3] Defender");
        System.out.println("[4] Alterar Personagem");
        System.out.println("[5] Fugir");

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
                jogadorAtivo.abrirMenuHabilidades(scanner, inimigo);
                break;

            case 3:
                jogadorAtivo.defender();
                break;
            case 4:
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

            case 5:
                System.out.println(jogadorAtivo.nome + " fugiu em segurança.");
                inBattle = false;
                continue;

            default:
                System.out.println("Opção inválida.");
                continue;
        }
        // Verificando morte de inimigo
        if (inimigo.morto()) {
            System.out.println("🌟 !" + inimigo.nome + " derrotado! 🌟");
            System.out.println();
            System.out.println("Batalha encerrada.");
            inBattle = false;
        }

        if(jogadorAtivo instanceof Guerreiro){
            ((Guerreiro) jogadorAtivo).atualizarEfeitos();
        }

        if(jogadorAtivo instanceof Maga){
            ((Maga) jogadorAtivo).atualizarEfeitos(inimigo);
        }

        // Turno do inimigo
        System.out.println("--------------------");
        if(inBattle && inimigo.vida > 0) {
            inimigo.atacar(jogadorAtivo);
        }

        // Se vida <= 0, personagem morreu.
         if(jogadorAtivo.vida <= 0){
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