package menus;
import habilidades.Habilidades;
import model.Inimigo;
import model.Mago;
import batalha.SistemaBatalha;
import java.util.*;

public class MenuMago {

    public static void abrir(Mago mago, Scanner scanner, Inimigo inimigo, SistemaBatalha batalha){
        // Listando Habilidades da classe Mago
        List<Habilidades> habilidades = mago.getHabilidades();
        /*__________Menu_De_Habilidades__________*/
        int larguraTopo = 100;
        int meiaLarguraTopo = larguraTopo/2;
        int larguraCentro = larguraTopo - 6;
        int meiaLarguraCentro = larguraCentro/2;
        // Cima
        System.out.println("#".repeat(larguraTopo));
        // habilidades de cima
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
        // habilidade de baixo / voltar
        System.out.println(
            "## "
            + alinharEsquerda("[3] Barreira de Sangue 🩸", meiaLarguraCentro, " ")
            + alinharDireita("[4] Voltar ↩",meiaLarguraCentro , " ")
            +  " ##");
        // Baixo
        System.out.println("#".repeat(larguraTopo));

        /*__________Habilidades__________*/
        if(!scanner.hasNextInt()){
            System.out.println("Digite apenas números!");
            scanner.next();
            return;
        }

        int opcao = scanner.nextInt();
        // Voltar
        if(opcao == habilidades.size() + 1){
            return;
        }
        // Validação
        if (opcao < 1 || opcao > habilidades.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        // Executando a Habilidade
        habilidades.get(opcao-1).usar(mago, inimigo, batalha);

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