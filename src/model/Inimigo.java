package model;
import java.util.Random;
import java.util.Scanner;

public class Inimigo extends Personagem{

    Random random = new Random();
    boolean marcaAtiva = false;
    int turnosMarca = 0;

    public Inimigo(String nome){
        super(nome,1000,300,3,3,0,30,false);
        ataqueOriginal = ataque;
        defesaOriginal = defesa;
    }

    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo) { }
    @Override
    public void abrirMenuItens(Scanner scanner) { }

    public void executarTurno(Personagem jogadorAtivo){
        int chance = random.nextInt(100) + 1;           // números de 0 a 100
        // 50% - pega a primeira parcela (0 a 50) e verifica
        if(chance <= 50){
            atacar(jogadorAtivo);
        }
        // 20% - pega a segunda parcela (até 70) e verifica se está entre 51 e 70
        else if (chance <= 70){
            defender();
        }
        // 30% - executa, caso o valor (0 a 100), esteja entre 71 e 100
        else{
            usarHabilidade(jogadorAtivo);
        }
    }

    public void usarHabilidade(Personagem jogadorAtivo){
        int habilidade = random.nextInt(4); // gera 0, 1, 2 ou 3 (25%)
        switch (habilidade){
            case 0 -> chuvaDeMeteoros(jogadorAtivo);
            case 1 -> investidaDoVazio(jogadorAtivo);
            case 2 -> marcaDoJulgamento(jogadorAtivo);
            case 3 -> execucaoCondenada(jogadorAtivo);
        }
    }

    // Marca do Julgamento -> Aplica uma marca no inimigo por x turnos, reduzindo sua defesa.
    public void marcaDoJulgamento(Personagem jogadorAtivo){
        int custoMana = 20;
        if (mana < custoMana){
            System.out.println(nome + " não possui mana o bastante.");
        }
        if(!marcaAtiva){
            mana-= custoMana;
            marcaAtiva = true;
            turnosMarca = 4;
            jogadorAtivo.defesa = defesaOriginal / 2;
            System.out.println(jogadorAtivo.nome + " foi amaldiçoado (" + turnosMarca + " turno(s)).");
        }
        else{
            System.out.println(nome + " tentou usar: Marca do Julgamento em " + jogadorAtivo.nome + "\n, mas " + jogadorAtivo + " já foi marcado.");
        }
    }

    // Execução Condenada -> causa dano baixo. Se o inimigo estiver sob efeito de Marca do Julgamento, o dano é triplicado.
    public void execucaoCondenada(Personagem jogadorAtivo){
        int danoBase = 50;
        int danoAprimorado = danoBase * 3;
        int custoMana = 25;
        if(mana < custoMana){
            System.out.println(nome + " tentou usar Execução Condenada, mas não possui mana.");
        }
        mana -= custoMana;
        if(marcaAtiva){
            jogadorAtivo.receberDano(danoAprimorado);
            System.out.println("O efeito da marca fez com que "
                + jogadorAtivo.nome + " recebesse o triplo de dano! (" + danoAprimorado + " de dano!" );
            marcaAtiva = false;
        }
        else{
            jogadorAtivo.receberDano(danoBase);
        }
    }

    public void investidaDoVazio(Personagem jogadorAtivo){
        int dano = 70;
        int custoMana = 30;
        if(mana < custoMana){
            System.out.println(nome + " tentou usar Investida Do Vazio, mas não possui mana.");
        }
        mana -= custoMana;
        jogadorAtivo.receberDano(dano);
        System.out.println(jogadorAtivo.nome + " foi atingido por Investida Do Vazio. 🕳️");
        System.out.println(nome + " deixou um rastro de destruição.");
    }

    public void chuvaDeMeteoros(Personagem jogadorAtivo){
        int qtDeMeteoros = 10;
        float percentualChangeAcertar = 0.5f; // de 0 até 0.99999
        int danoPorMeteoro = 20;
        // Custo de mana de Chuva de Meteoros
        int custoMana = 40;
        if(mana < custoMana){
            System.out.println(nome + " não possui mana suficiente!");
        }
        mana -= custoMana;
        // Definindo, de maneira aleatória, quantos meteoros atingiram o alvo.
        int qtDeMeteorosAcertados = 0;
        var random = new Random();
        for (int indiceDisparo = 0;  indiceDisparo < qtDeMeteoros; indiceDisparo++){
            // TODO: pesquisar range de retorno de valores do nextFloat (se vai até 1 ou até outro numero)
            if(random.nextFloat() <= percentualChangeAcertar){
                qtDeMeteorosAcertados++;
            }
        }
        int dano = qtDeMeteorosAcertados * danoPorMeteoro;

        jogadorAtivo.receberDano(dano);
        System.out.println(nome + " usou: Chuva de Meteoros! ☄️☄️");
        System.out.println("Uma cascata de meteoros desabou sobre " + jogadorAtivo.nome);
        System.out.println(jogadorAtivo.nome + " foi atingido por " + qtDeMeteorosAcertados + " meteoros!");
    }

    public void aplicandoEfeitos(Personagem jogadorAtivo){
        if(marcaAtiva){
            turnosMarca--;
            System.out.println(turnosMarca + " turno(s) restantes de: Marca do Julgamento");

            if(turnosMarca <= 0 ){
                marcaAtiva = false;
                System.out.println("A maldição de " + jogadorAtivo.nome + " se desfez.");
            }
        }
    }

    @Override
    public void mostrarStatus(){
        System.out.println();
        System.out.println("Inimigo: " + nome);
        System.out.println(">HP: " + vida + "\n");
    }
}