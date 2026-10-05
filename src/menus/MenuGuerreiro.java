package menus;
import habilidades.Habilidades;
import model.Guerreiro;
import batalha.SistemaBatalha;
import model.Personagem;

import java.util.List;
import java.util.Scanner;

public class MenuGuerreiro {
    public static void abrir(Guerreiro guerreiro, Scanner scanner, Personagem alvo, SistemaBatalha batalha){

        // Listando Habilidades da classa Guerreiro
        List<Habilidades> habilidades = guerreiro.getHabilidades();
        /*__________Menu_De_Habilidades__________*/
        int larguraTopo = 100;
        int meiaLarguraTopo = larguraTopo/2;
        int larguraCentro = larguraTopo - 6;
        int meiaLarguraCentro = larguraCentro/2;
        // Cima
        System.out.println("#".repeat(larguraTopo));
        // Golpe Devastador / Berserker
        System.out.println(
            "## "
            + alinharEsquerda("[1] Golpe Devastador ⚔️", meiaLarguraCentro, " ")
            + alinharDireita("[2] Berserker 💢",meiaLarguraCentro , " ")
            +  " ##");
        // Centro vazio
        System.out.println(
            "## "
            + alinharEsquerda("", meiaLarguraCentro, " ")
            + alinharDireita("", meiaLarguraCentro , " ")
            +  " ##");
        // Pele de Aço / Fugir
        System.out.println(
            "## "
            + alinharEsquerda("[3] Pele de Aço🦾", meiaLarguraCentro, " ")
            + alinharDireita("[4] Voltar ↩",meiaLarguraCentro , " ")
            +  " ##");
        // Baixo
        System.out.println("#".repeat(larguraTopo));

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
        habilidades.get(opcao-1).usar(guerreiro, alvo, batalha);
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
        int espacoTotal = largura - textoRecortado.length();
        int esquerda = espacoTotal/2;
        int direita = espacoTotal - esquerda;
        //   Preencher à esquerda_________+_________Conteúdo_________+_________Preencher à direita
        return caracterRepetir.repeat(esquerda) + textoRecortado + caracterRepetir.repeat(direita);
    }
}