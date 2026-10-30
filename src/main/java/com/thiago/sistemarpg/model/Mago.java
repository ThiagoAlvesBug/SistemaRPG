package com.thiago.sistemarpg.model;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.habilidades.magica.BarreiraDeSangue;
import com.thiago.sistemarpg.habilidades.magica.BolaDeFogo;
import com.thiago.sistemarpg.habilidades.Habilidades;
import com.thiago.sistemarpg.habilidades.magica.RajadaArcana;
import com.thiago.sistemarpg.menus.MenuItens;
import com.thiago.sistemarpg.menus.MenuMago;
import com.thiago.sistemarpg.model.itens.PocaoCura;
import com.thiago.sistemarpg.model.itens.PocaoMana;
import java.util.*;

public class Mago extends Personagem{
    // Lista de Habilidades
    private List<Habilidades> habilidades = new ArrayList<>();

    // Construtor de Mago
    public Mago(String nome) {
        super(nome, 350, 250,0, 25, false);
        setAtaqueOriginal(getAtaque());
        setDefesaOriginal(getDefesa());
        // 2 Poções de cura
        adicionarItem(new PocaoCura());
        adicionarItem(new PocaoCura());
        // 3 Poções de mana
        adicionarItem(new PocaoMana());
        adicionarItem(new PocaoMana());
        adicionarItem(new PocaoMana());
        // Adicionando habilidades à lista de habilidades
        habilidades.add(new BolaDeFogo());
        habilidades.add(new RajadaArcana());
        habilidades.add(new BarreiraDeSangue());
    }
    // Menu de habilidades
    @Override
    public void abrirMenuHabilidades(Scanner scanner, Personagem alvo, SistemaBatalha batalha)
    { MenuMago.abrir(this, scanner, alvo, batalha); }
    // Menu de itens
    @Override
    public void abrirMenuItens(Scanner scanner, SistemaBatalha batalha) { MenuItens.abrir(this, scanner, batalha); }
    // Habilidades
    @Override
    public List<Habilidades> getHabilidades(){
        return habilidades;
    }
    // Recebendo Dano
    @Override
    public void receberDano(float dano, SistemaBatalha batalha) {
        super.receberDano(dano, batalha);
    }
}