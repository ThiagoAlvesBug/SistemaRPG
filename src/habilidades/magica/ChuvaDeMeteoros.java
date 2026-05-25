package habilidades.magica;

import batalha.SistemaBatalha;
import habilidades.Habilidades;
import model.Personagem;

import java.util.Random;

public class ChuvaDeMeteoros implements Habilidades {
    @Override
    public String getNome(){
        return "Chuva de Meteoros";
    }

    @Override
    public int getCustoMana(){
        return 40;
    }

    @Override
    public void usar(Personagem usuario, Personagem alvo, SistemaBatalha batalha) {
        int qtDeMeteoros = 10;
        float percentualChangeAcertar = 0.5f; // de 0 até 0.99999
        int danoPorMeteoro = 20;
        // Custo de mana de Chuva de Meteoros
        int custoMana = 40;
        if(usuario.getMana() < custoMana){
            System.out.println(usuario.getNome() + " não possui mana suficiente!");
        }
        usuario.setMana(usuario.getMana() - custoMana);
        // Definindo, de maneira aleatória, quantos meteoros atingiram o alvo.
        int qtDeMeteorosAcertados = 0;
        var random = new Random();
        for (int indiceDisparo = 0;  indiceDisparo < qtDeMeteoros; indiceDisparo++){
            if(random.nextFloat() <= percentualChangeAcertar){
                qtDeMeteorosAcertados++;
            }
        }
        int dano = qtDeMeteorosAcertados * danoPorMeteoro;

        batalha.adicionarLog(usuario.getNome() + " usou: Chuva de Meteoros! ☄️☄️");
        batalha.adicionarLog("Uma cascata de meteoros desabou sobre " + alvo.getNome());
        batalha.adicionarLog(alvo.getNome() + " foi atingido por " + qtDeMeteorosAcertados + " meteoros!");
        alvo.receberDano(dano, batalha);
    }
}