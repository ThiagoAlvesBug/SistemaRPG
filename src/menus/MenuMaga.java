package menus;
import model.Inimigo;
import model.Maga;
import habilidades.HabilidadesMaga;
import java.util.Scanner;

public class MenuMaga {
    public static void abrir(Maga maga, Scanner scanner, Inimigo inimigo){
        // maga.mostrarStatus();
        // inimigo.mostrarStatus();
        int larguraTopo = 100;
        int meiaLarguraTopo = larguraTopo/2;
        int larguraCentro = larguraTopo - 6;
        int meiaLarguraCentro = larguraCentro/2;
        // Cima
        System.out.println("#".repeat(larguraTopo));
        // Bola de Fogo / Rajada Arcana
        System.out.println(
            "## "
            + alinharEsquerda("[1] Bola de Fogo 🔥", meiaLarguraCentro, " ")
            + alinharDireita("[2] Rajada Arcana 🪄",meiaLarguraCentro , " ")
            +  " ##");
        // Centro vazio
        System.out.println(
            "## "
            + alinharEsquerda("", meiaLarguraCentro, " ")
            + alinharDireita("", meiaLarguraCentro , " ")
            +  " ##");
        // Barreira de Sangue / Voltar
        System.out.println(
            "## "
            + alinharEsquerda("[3] Barreira de Sangue 🩸", meiaLarguraCentro, " ")
            + alinharDireita("[4] Voltar ↩",meiaLarguraCentro , " ")
            +  " ##");
        // Baixo
        System.out.println("#".repeat(larguraTopo));

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
    // Alinhando à esquerda
    public static String alinharEsquerda(String texto, int largura, String caracterRepetir){
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0,largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return textoRecortado + preenchimento;
    }
    // Alinhando à direita
    public static String alinharDireita(String texto, int largura, String caracterRepetir){
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0,largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return preenchimento + textoRecortado;
    }
    // Alinhando no centro
    public static String alinharCentro(String texto, int largura, String caracterRepetir){
        var textoRecortado = (texto + " ".repeat(largura)).substring(0, largura).trim();
        int espaçoTotal = largura - textoRecortado.length();
        int esquerda = espaçoTotal/2;
        int direita = espaçoTotal - esquerda;
        //   Preencher à esquerda_________+_________Conteúdo_________+_________Preencher à direita
        return caracterRepetir.repeat(esquerda) + textoRecortado + caracterRepetir.repeat(direita);
    }
}
