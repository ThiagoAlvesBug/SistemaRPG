package menus;
import batalha.SistemaBatalha;
import model.Personagem;
import model.itens.PocaoMana;
import model.itens.PocaoCura;
import java.util.Scanner;

public class MenuItens {
    public static void abrir(Personagem jogadorAtivo, Scanner scanner, SistemaBatalha batalha) {
        // LAYOUT
        int larguraTopo = 100;
        int meiaLarguraTopo = larguraTopo / 2;
        int larguraCentro = larguraTopo - 6;
        int meiaLarguraCentro = larguraCentro / 2;

        // Cima
        System.out.println("#".repeat(larguraTopo));
        // Poção de cura / Poção de mana
        System.out.println(
                "## "
                        + alinharEsquerda("[1] POÇÃO DE CURA (" + jogadorAtivo.getQuantidadePocaoVida() + ")", meiaLarguraCentro, " ")
                        + alinharDireita("[2] POÇÃO DE MANA (" + jogadorAtivo.getQuantidadePocaoMana() + ")", meiaLarguraCentro, " ")
                        + " ##");
        // Centro vazio
        System.out.println(
                "## "
                        + alinharEsquerda("", meiaLarguraCentro, " ")
                        + alinharDireita("", meiaLarguraCentro, " ")
                        + " ##");
        // Voltar
        System.out.println(
                "## "
                        + alinharCentro("[3] VOLTAR", larguraCentro, " ")
                        + " ##");
        // Baixo
        System.out.println("#".repeat(larguraTopo));

        int opcao;
        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
        } else {
            System.out.println("Digite apenas números!");
            scanner.next(); // limpa entrada inválida
            return;
        }

        switch (opcao) {
            case 1 -> {
                if (!jogadorAtivo.usarItem(PocaoCura.class, batalha)) {
                    System.out.println("Você não possui Poção de Cura.");
                }
            }

            case 2 -> {
                if (!jogadorAtivo.usarItem(PocaoMana.class, batalha)) {
                    System.out.println("Você não possui Poção de Mana.");
                }
            }
            case 3 -> {
                return;
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    // Alinhando à esquerda
    public static String alinharEsquerda(String texto, int largura, String caracterRepetir) {
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0, largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return textoRecortado + preenchimento;
    }

    // Alinhando à direita
    public static String alinharDireita(String texto, int largura, String caracterRepetir) {
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0, largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return preenchimento + textoRecortado;
    }

    // Alinhando no centro
    public static String alinharCentro(String texto, int largura, String caracterRepetir) {
        var textoRecortado = (texto + " ".repeat(largura)).substring(0, largura).trim();
        int espacoTotal = largura - textoRecortado.length();
        int esquerda = espacoTotal / 2;
        int direita = espacoTotal - esquerda;
        //   Preencher à esquerda_________+_________Conteúdo_________+_________Preencher à direita
        return caracterRepetir.repeat(esquerda) + textoRecortado + caracterRepetir.repeat(direita);
    }
}