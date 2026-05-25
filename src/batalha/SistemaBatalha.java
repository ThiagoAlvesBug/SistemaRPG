package batalha;
import menus.ComportamentoAposMenuPrincipal;
import menus.OpcaoMenuPrincipal;
import model.*;
import service.Configuracoes;
import java.util.*;

public class SistemaBatalha {
    List<String> logBatalha = new ArrayList<>();
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    // Declarando atributos
    Personagem jogadorAtivo;
    Guerreiro guerreiro;
    Mago mago;
    Inimigo inimigo;

    public SistemaBatalha(Guerreiro guerreiro, Mago mago) {
        this.guerreiro = guerreiro;
        this.mago = mago;
        inimigo = new Inimigo("Andariel");
        // Iniciando com personagem aleatório (nextBoolean retorna true ou false, aleatoriamente)
        if (random.nextBoolean()) {
            jogadorAtivo = guerreiro;
        } else {
            jogadorAtivo = mago;
        }
    }

    /*__________Batalha__________*/

    // Todas as ações de batalha dividas em métodos.
    public void iniciar() throws InterruptedException {

        System.out.println("Iniciando batalha com: " + jogadorAtivo.getNome());
        // Batalha
        while (verificarBatalhaAtiva()) {
            Configuracoes.limparConsole();
            // Redesenha a tela a cada turno
            renderizarTela();
            // Ler ação do jogador
            int opcao = lerAcao();
            ComportamentoAposMenuPrincipal comportamento = executarAcao(opcao);
            if (comportamento == ComportamentoAposMenuPrincipal.COMPLETOU_TURNO) {
                if (verificarFimDeBatalha()) break;
                aplicarEfeitos();
                turnoInimigo(this);
                esperar(1000);
            }
            if (comportamento == ComportamentoAposMenuPrincipal.FUGIU) break;
        }
    }

    // Alinhando à esquerda
    public String alinharEsquerda(String texto, int largura, String caracterRepetir) {
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0, largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return textoRecortado + preenchimento;
    }

    // Alinhando à direita
    public String alinharDireita(String texto, int largura, String caracterRepetir) {
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0, largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return preenchimento + textoRecortado;
    }

    // Alinhando no centro
    public String alinharCentro(String texto, int largura, String caracterRepetir) {
        var textoRecortado = (texto + " ".repeat(largura)).substring(0, largura).trim();
        int espaçoTotal = largura - textoRecortado.length();
        int esquerda = espaçoTotal / 2;
        int direita = espaçoTotal - esquerda;
        //   Preencher à esquerda_________+_________Conteúdo_________+_________Preencher à direita
        return caracterRepetir.repeat(esquerda) + textoRecortado + caracterRepetir.repeat(direita);
    }

    // Alinhando dois itens no centro
    public String alinharDoisItensCentro(String texto1, String texto2, int largura, String caracterRepetir) {
        texto1 = texto1.trim();
        texto2 = texto2.trim();
        int tamanhoTextos = texto1.length() + texto2.length();
        int espacoLivre = largura - tamanhoTextos;
        if (espacoLivre <= 0) {
            return (texto1 + texto2).substring(0, largura);
        }
        int esquerda = espacoLivre / 3;
        int meio = espacoLivre / 3;
        int direita = espacoLivre - esquerda - meio;
        //     Preencher à direita_____+_____Conteúdo 1_____+_____Preencher meio_____+_____Conteúdo 2_____+_____Preencher à direita
        return caracterRepetir.repeat(esquerda) + texto1 + caracterRepetir.repeat(meio) + texto2 + caracterRepetir.repeat(direita);
    }

    // Cabeçalho
    private void mostrarCabecalho() {
        int largura = 96;
        int meiaLargura = largura / 2;
        // Parte de cima
        System.out.println("|‾" + "‾".repeat(largura) + "‾|");

        var infoNomeJogador = "> " + jogadorAtivo.getNome();
        var infoNomeInimigo = "> " + inimigo.getNome();
        var infoVidaJogador = "> Vida: " + jogadorAtivo.getVida();
        var infoVidaInimigo = "> Vida: " + inimigo.getVida();
        var infoManaJogador = "> Mana: " + jogadorAtivo.getMana();
        // Linha com nome
        System.out.println(
                "| "
                        + alinharEsquerda(infoNomeJogador, meiaLargura, " ")
                        + alinharDireita(infoNomeInimigo, meiaLargura, " ")
                        + " |");
        // Linha com vida
        System.out.println(
                "| "
                        + alinharEsquerda(infoVidaJogador, meiaLargura, " ")
                        + alinharDireita(infoVidaInimigo, meiaLargura, " ")
                        + " |");
        // Linha com mana
        System.out.println(
                "| "
                        + alinharEsquerda(infoManaJogador, largura, " ")
                        + " |");
        // 5 linhas em sequência
        for (int i = 0; i < 4; i++) {
            System.out.println("|" + " ".repeat(largura + 2) + "|");
        }
        // Parte de baixo
        System.out.println("|" + "_".repeat(largura + 2) + "|");
    }

    // Menu de batalha
    private void mostrarMenu() {
        int larguraTopo = 100;
        int meiaLarguraTopo = larguraTopo / 2;
        int larguraCentro = larguraTopo - 6;
        int meiaLarguraCentro = larguraCentro / 2;

        // Cima
        System.out.println("#".repeat(larguraTopo));
        // Atacar / Habilidades
        System.out.println(
                "## "
                        + alinharEsquerda("[1] ATACAR", meiaLarguraCentro, " ")
                        + alinharDireita("[2] HABILIDADES", meiaLarguraCentro, " ")
                        + " ##");
        // Defender
        System.out.println(
                "## "
                        + alinharDoisItensCentro("[3] DEFENDER", "[4] ITENS", larguraCentro, " ")
                        + " ##");
        // Alterar Personagem/ Fugir
        System.out.println(
                "## "
                        + alinharEsquerda("[5] ALTERAR PERSONAGEM", meiaLarguraCentro, " ")
                        + alinharDireita("[6] FUGIR", meiaLarguraCentro, " ")
                        + " ##");
        // Baixo
        System.out.println("#".repeat(larguraTopo));
    }

    // Lendo input do jogador
    private int lerAcao() {
        int opcao;

    //    TODO: Implementar loop para ser executado até que uma opção válida seja informada.
        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
        } else {
            System.out.println("Digite apenas números!");
            scanner.next();
            return -1;
        }
        return opcao;
    }

    // Executando a ação com base na opção selecionada
    private ComportamentoAposMenuPrincipal executarAcao(int opcao) {

        OpcaoMenuPrincipal escolha = OpcaoMenuPrincipal.fromInt(opcao);

        if(escolha == null){
            System.out.println("Opção inválida.");
            return ComportamentoAposMenuPrincipal.NADA;
        }

        switch (escolha) {
            case ATACAR -> {
                jogadorAtivo.atacar(inimigo,this);
                return ComportamentoAposMenuPrincipal.COMPLETOU_TURNO;
            }
            case ABRIR_MENU_HABILIDADES -> {
                jogadorAtivo.abrirMenuHabilidades(scanner, inimigo, this);
                return ComportamentoAposMenuPrincipal.COMPLETOU_TURNO;
            }
            case DEFENDER -> {
                jogadorAtivo.defender();
                return ComportamentoAposMenuPrincipal.COMPLETOU_TURNO;
            }
            case ABRIR_MENU_ITENS -> {
                jogadorAtivo.abrirMenuItens(scanner);
                return ComportamentoAposMenuPrincipal.COMPLETOU_TURNO;
            }
            case TROCAR_PERSONAGEM -> {
                trocarPersonagem();
                return ComportamentoAposMenuPrincipal.COMPLETOU_TURNO;
            }
            case FUGIR -> {
                logBatalha.add(jogadorAtivo.getNome() + " fugiu em segurança 💨");
                return ComportamentoAposMenuPrincipal.FUGIU;
            }
            default -> {
                System.out.println("Opção inválida.");
                return ComportamentoAposMenuPrincipal.NADA;
            }
        }
    }

    // Troca de personagens
    private void trocarPersonagem() {
        if (jogadorAtivo == guerreiro) {
            if (mago.getVida() > 0) {
                jogadorAtivo = mago;
                System.out.println("Personagem alterado para: " + jogadorAtivo.getNome());
            } else {
                System.out.println(mago.getNome() + " está morto.");
            }
        } else {
            if (guerreiro.getVida() > 0) {
                jogadorAtivo = guerreiro;
                System.out.println("Personagem alterado para: " + jogadorAtivo.getNome());
            } else {
                System.out.println(guerreiro.getNome() + " está morto.");
            }
        }
    }

    // Efeitos de Status
    private void aplicarEfeitos() {
        jogadorAtivo.aplicarEfeitos(this);
        inimigo.aplicarEfeitos(this);
    }

    // Turno do inimigo
    private void turnoInimigo(SistemaBatalha batalha) {
        System.out.println("|" + "-".repeat(98) + "|");
        System.out.println();
        if (inimigo.vida > 0) {
            inimigo.executarTurno(jogadorAtivo,batalha);
        }
    }

    // Verificando morte de algum personagem e efetuando a troca entre personagens.
    private boolean verificarBatalhaAtiva() {
        // Se vida <= 0, personagem morreu.
        if (jogadorAtivo.vida > 0) {
            return true;
        }

        System.out.println("❌ " + jogadorAtivo.getNome() + " morreu!");
        System.out.println();

        // Guerreiro troca para Maga
        if (jogadorAtivo == guerreiro && mago.getVida() > 0) {
            jogadorAtivo = mago;
            System.out.println("⬆️ " + mago.nome + " entrou na batalha.");
            return true;
        }

        // Maga troca para Guerreiro
        if (jogadorAtivo == mago && guerreiro.getVida() > 0) {
            jogadorAtivo = guerreiro;
            System.out.println("⬆️ " + guerreiro.getNome() + " entrou na batalha.");
            return true;
        }

        // Todos os jogadores morreram
        System.out.println("❌ " + mago.nome + " e " + guerreiro.nome + " foram derrotados.");
        return false;
    }

    // Verificando o fim da batalha
    public boolean verificarFimDeBatalha() {
        if (inimigo.morto()) {
            System.out.println("🌟 !" + inimigo.nome + " derrotado! 🌟");
            System.out.println("Batalha encerrada.");
            return true;
        }
        return false;
    }
    // Intervalo entre turnos
    public void esperar(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void renderizarTela(){
        mostrarCabecalho();
        mostrarLog();
        mostrarMenu();
    }

    public void adicionarLog(String mensagem){
        logBatalha.add(mensagem);
    }

    private void mostrarLog(){
        System.out.println();
        int maxLinhas = 6;
        int start = Math.max(0, logBatalha.size() - maxLinhas);
        for (int i = start; i < logBatalha.size(); i++) {
            System.out.println(logBatalha.get(i));
        }
    }
}
