package menus;
import model.Inimigo;
import model.Maga;
import habilidades.HabilidadesMaga;
import java.util.Scanner;

public class MenuMaga {
    public static void abrir(Maga maga, Scanner scanner, Inimigo inimigo){
        maga.mostrarStatus();
        inimigo.mostrarStatus();
        System.out.println("====================");
        System.out.println("=   Habilidades    =");
        System.out.println("====================");
        System.out.println("[1] Bola de fogo 🔥");
        System.out.println("[2] Rajada arcana 🪄");
        System.out.println("[3] Barreira de Sangue 🩸");
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
            case 1 -> HabilidadesMaga.BOLA_DE_FOGO.usar(maga, inimigo);
            case 2 -> HabilidadesMaga.RAJADA_ARCANA.usar(maga, inimigo);
            case 3 -> HabilidadesMaga.BARREIRA_DE_SANGUE.usar(maga, inimigo);
            case 4 -> { return; }
            default -> System.out.println("Opção inválida.");
        }
    }
}
