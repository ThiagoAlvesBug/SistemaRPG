package batalha;

import model.*;
import service.Configuracoes;

import java.util.*;

public class SistemaBatalha {
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    // Declarando atributos
    Personagem jogadorAtivo;
    Guerreiro guerreiro;
    Maga maga;
    Inimigo inimigo;

    public SistemaBatalha(Guerreiro guerreiro, Maga maga) {
        this.guerreiro = guerreiro;
        this.maga = maga;
        inimigo = new Inimigo("Andariel");
        // Iniciando com personagem aleatório (nextBoolean retorna true ou false, aleatoriamente)
        if (random.nextBoolean()) {
            jogadorAtivo = guerreiro;
        } else {
            jogadorAtivo = maga;
        }
    }

    /*__________Batalha__________*/

    // Todas as ações de batalha dividas em métodos.
    public void iniciar() {

        System.out.println("Iniciando batalha com: " + jogadorAtivo.nome);
        // Batalha
        while (verificarBatalhaAtiva()) {
            Configuracoes.limparConsole();
            mostrarCabecalho();
            mostrarMenu();
            int opcao = lerAcao();
            // Cabeçalho de Batalha
            ComportamentoAposMenuPrincipal comportamento = executarAcao(opcao);
            if (comportamento == ComportamentoAposMenuPrincipal.COMPLETOU_TURNO) {
                if (verificarFimDeBatalha()) break;
                aplicarEfeitos();
                turnoInimigo();
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

        var infoNomeJogador = "> " + jogadorAtivo.nome;
        var infoNomeInimigo = "> " + inimigo.nome;
        var infoVidaJogador = "> Vida: " + jogadorAtivo.vida;
        var infoVidaInimigo = "> Vida: " + inimigo.vida;
        var infoManaJogador = "> Mana: " + jogadorAtivo.mana;
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
        for (int i = 0; i < 5; i++) {
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
                        + " ##"
        );
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


        switch (escolha) {
            case ATACAR -> {
                jogadorAtivo.atacar(inimigo);
                return ComportamentoAposMenuPrincipal.COMPLETOU_TURNO;
            }
            case ABRIR_MENU_HABILIDADES -> {
                jogadorAtivo.abrirMenuHabilidades(scanner, inimigo);
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
                System.out.println(jogadorAtivo.nome + " fugiu em segurança 💨");
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
            if (maga.vida > 0) {
                jogadorAtivo = maga;
                System.out.println("Personagem alterado para: " + jogadorAtivo.nome);
            } else {
                System.out.println(maga.nome + " está morto.");
            }
        } else {
            if (guerreiro.vida > 0) {
                jogadorAtivo = guerreiro;
                System.out.println("Personagem alterado para: " + jogadorAtivo.nome);
            } else {
                System.out.println(guerreiro.nome + " está morto.");
            }
        }
    }

    // Efeitos de batalha (efeitos de status)
    private void aplicarEfeitos() {
        if (jogadorAtivo instanceof Guerreiro) {
            ((Guerreiro) jogadorAtivo).aplicandoEfeitos();
        }
        if (jogadorAtivo instanceof Maga) {
            ((Maga) jogadorAtivo).aplicandoEfeitos(inimigo);
        }
        inimigo.aplicandoEfeitos(jogadorAtivo);
    }

    // Turno do inimigo
    private void turnoInimigo() {
        System.out.println("|" + "-".repeat(98) + "|");
        if (inimigo.vida > 0) {
            inimigo.executarTurno(jogadorAtivo);
        }
    }

    // Verificando morte de algum personagem e efetuando a troca entre personagens.
    private boolean verificarBatalhaAtiva() {
        // Se vida <= 0, personagem morreu.
        if (jogadorAtivo.vida > 0) {
            return true;
        }

        System.out.println("❌ " + jogadorAtivo.nome + " morreu!");
        System.out.println();

        // Guerreiro troca para Maga
        if (jogadorAtivo == guerreiro && maga.vida > 0) {
            jogadorAtivo = maga;
            System.out.println("⬆️ " + maga.nome + " entrou na batalha.");
            return true;
        }

        // Maga troca para Guerreiro
        if (jogadorAtivo == maga && guerreiro.vida > 0) {
            jogadorAtivo = guerreiro;
            System.out.println("⬆️ " + guerreiro.nome + " entrou na batalha.");
            return true;
        }

        // Todos os jogadores morreram
        System.out.println("❌ " + maga.nome + " e " + guerreiro.nome + " foram derrotados.");
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
}
