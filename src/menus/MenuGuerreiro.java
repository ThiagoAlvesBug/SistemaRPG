package menus;
import model.Guerreiro;
import model.Inimigo;
import habilidades.HabilidadeGuerreiro;
import java.util.Scanner;

public class MenuGuerreiro {
    public static void abrir(Guerreiro guerreiro, Scanner scanner, Inimigo inimigo){
        guerreiro.mostrarStatus();
        inimigo.mostrarStatus();
        System.out.println("====================");
        System.out.println("=   Habilidades    =");
        System.out.println("====================");
        System.out.println("[1] Golpe devastador ⚔️");
        System.out.println("[2] Berserker 💢");
        System.out.println("[3] Pele de aço 🦾");
        System.out.println("[4] Voltar");

        int opcao;
        if(scanner.hasNextInt()){
            opcao = scanner.nextInt();
        } else{
            System.out.println("Digite apenas números!");
            scanner.next(); // limpa entrada inválida
            return;
        }
        switch (opcao){
            case 1 -> HabilidadeGuerreiro.GOLPE_DEVASTADOR.usar(guerreiro, inimigo);
            case 2 -> HabilidadeGuerreiro.BERSERKER.usar(guerreiro, inimigo);
            case 3 -> HabilidadeGuerreiro.PELE_DE_ACO.usar(guerreiro, inimigo);
            case 4 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }
}
