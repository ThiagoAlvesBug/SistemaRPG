package service;

// TODO: Implementar uma função para limpar a tela a cada turno.
public class Configuracoes {
    public static void limparConsole() {    try {

        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();

        } else {
            new ProcessBuilder("clear")
                    .inheritIO()
                    .start()
                    .waitFor();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}