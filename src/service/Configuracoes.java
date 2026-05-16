package service;

// TODO: Implementar uma função para limpar a tela a cada turno.
public class Configuracoes {
    public static void limparConsole() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}