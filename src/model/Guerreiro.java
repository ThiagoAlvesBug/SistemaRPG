package model;

// TODO: Fazer 3 habilidades para a classe guerreiro.
//  Duas ofensivas e uma habilidade voltada para defesa.

import java.util.Scanner;

public class Guerreiro extends Personagem{

    private boolean berserkerAtivo = false;
    private int turnosBerserker = 0;

    private boolean peleAcoAtivo = false;
    private int turnosPeleAço = 0;

    private float ataqueOriginal;
    private float defesaOriginal;

    public Guerreiro(String nome){
        super(nome,150,100,25,25,false);

        ataqueOriginal = ataque;
        defesaOriginal = defesa;
    }
    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo){
        mostrarStatus();
        inimigo.mostrarStatus();
        System.out.println("====================");
        System.out.println("=   Habilidades    =");
        System.out.println("====================");
        System.out.println("[1] Golpe devastador ⚔️");
        System.out.println("[2] Berserker 💢");
        System.out.println("[3] Pele de aço 🦾");
        System.out.println("[4] Voltar");

        int opcao;
        if(scanner.hasNextInt()){
            opcao = scanner.nextInt();
        } else{
            System.out.println("Digite apenas números!");
            scanner.next(); // limpa entrada inválida
            return;
        }
        switch (opcao){
            case 1 -> golpeDevastador(inimigo);

            case 2 -> berserker();

            case 3 -> peleDeAco();

            case 4 -> {
                return;
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    // TODO: Golpe devastador: um ataque que causa 200% do dano de ataque.
    public void golpeDevastador(Personagem inimigo){
        float dano = (float) (ataque * 2.0);
        System.out.println("⚔️ " + nome + " atingiu " + inimigo.nome + " com um ataque devastador! ⚔️");
        inimigo.receberDano(dano);
    }

    // TODO: Berserker: Aumenta o dano do ataque, mas também o dano recebido.
    public void berserker() {
        if (!berserkerAtivo) {
            berserkerAtivo = true;
            ataque = ataqueOriginal * 1.5f;
            defesa = defesaOriginal * 0.7f;
            turnosBerserker = 3;
            System.out.println("💢 " + nome + " entrou em modo Berserker! 💢");
        } else if(peleAcoAtivo){
            System.out.println("Berserker não pode ser usado junto com Pele de aço.");
        }
        else {
            System.out.println(nome + " já está no modo Berserker");
        }
    }

    public void peleDeAco(){
        if(!peleAcoAtivo){
            peleAcoAtivo = true;
            defesa = defesaOriginal * 2.0f;
            turnosPeleAço = 3;
            System.out.println(nome + " revestiu sua pele com aço! 🦾");
        }
    }

    // TODO: Fazer um Método separado para contagem de turnos restantes até que o modo berserker acabe.
    public void atualizarEfeitos(){
        if(berserkerAtivo){
            turnosBerserker--;
            System.out.println(turnosBerserker + " turnos de modo Berserker restante(s).");

            if(turnosBerserker <= 0){
                berserkerAtivo = false;
                ataque = ataqueOriginal;
                defesa = defesaOriginal;
                System.out.println(nome + " saiu do modo Berserker.");
            }
        }

        if(peleAcoAtivo){
            turnosPeleAço--;
            System.out.println(turnosPeleAço + " turnos de Pele de Aço restante(s).");

            if(turnosPeleAço <= 0){
                peleAcoAtivo = false;
                defesa = defesaOriginal;
                System.out.println(nome + " voltou a sentir o peso dos golpes");
            }
        }

    }


}