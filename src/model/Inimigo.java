package model;
import batalha.SistemaBatalha;
import habilidades.*;
import habilidades.fisica.ExecucaoCondenada;
import habilidades.fisica.InvestidaDoVazio;
import habilidades.magica.ChuvaDeMeteoros;
import habilidades.buffsEdebuffs.MarcaDoJulgamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Inimigo extends Personagem{
    List<Habilidades> habilidades = new ArrayList<>();
    Random random = new Random();

    public Inimigo(String nome){
        super(nome,1000,300,3,3,0,50,false);
        ataqueOriginal = ataque;
        defesaOriginal = defesa;
        // Adicionando habilidades à lista de habilidades
        habilidades.add(new InvestidaDoVazio());
        habilidades.add(new ChuvaDeMeteoros());
        habilidades.add(new MarcaDoJulgamento());
        habilidades.add(new ExecucaoCondenada());
    }
    // Menu de habilidade
    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo, SistemaBatalha batalha) { }
    // Menu de itens
    @Override
    public void abrirMenuItens(Scanner scanner) { }
    // Inimigo executa uma ação aleatória a cada turno
    public void executarTurno(Personagem jogadorAtivo, SistemaBatalha batalha){
        int chance = random.nextInt(100) + 1;           // Gera um número aleatório entre 0 e 100
        // 50% - pega a primeira parcela (0 a 50) e verifica
        if(chance <= 50){
            atacar(jogadorAtivo, batalha);
        }
        // 20% - pega a segunda parcela (até 70) e verifica se está entre 51 e 70
        else if (chance <= 70){
            defender();
        }
        // 30% - executa, caso o valor (0 a 100), esteja entre 71 e 100
        else{
            usarHabilidade(jogadorAtivo, batalha);
        }
    }
    // Usando uma habilidade
    private void usarHabilidade(Personagem alvo, SistemaBatalha batalha){
        int indice = random.nextInt(habilidades.size());
        Habilidades habilidade = habilidades.get(indice);
        habilidade.usar(this, alvo, batalha);
    }
    // Lista de Habilidades
    public List<Habilidades> getHabilidades(){ return habilidades; }
}