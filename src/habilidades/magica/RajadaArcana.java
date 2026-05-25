package habilidades.magica;

import batalha.SistemaBatalha;
import habilidades.Habilidades;
import model.Personagem;

import java.util.Random;

public class RajadaArcana implements Habilidades {

    @Override
    public String getNome(){
        return "Rajada Arcana";
    }
    @Override
    public int getCustoMana(){
        return 40;
    }

    @Override
    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        int qtdeDisparos = 5;
        float percentualChangeAcertar = 0.5f; // de 0 até 0.99999
        int danoPorDisparo = 10;
        // Custo de mana de Rajada Arcana
        int custoMana = 40;
        if(usuario.getMana() < custoMana){
            System.out.println(usuario.getNome() + " não possui mana suficiente!");
        }
        usuario.setMana(usuario.getMana() - custoMana);
        // Definindo, de maneira aleatória, quantos disparos atingiram o alvo.
        int qtdeDisparosAcertados = 0;
        var random = new Random();
        for (int indiceDisparo = 0;  indiceDisparo < qtdeDisparos; indiceDisparo++){
            // TODO: pesquisar range de retorno de valores do nextFloat (se vai até 1 ou até outro numero)
            if(random.nextFloat() <= percentualChangeAcertar){
                qtdeDisparosAcertados++;
            }
        }
        int dano = qtdeDisparosAcertados * danoPorDisparo;

        batalha.adicionarLog(usuario.getNome() + " atacou " + alvo.getNome() + " com uma rajada de projéteis arcanos.");
        batalha.adicionarLog("Acertou " + qtdeDisparosAcertados + "/" + qtdeDisparos + " disparos.");
        alvo.receberDano(dano, batalha);
    }
}