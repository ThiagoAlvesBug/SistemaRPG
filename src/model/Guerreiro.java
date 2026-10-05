package model;
import batalha.SistemaBatalha;
import habilidades.fisica.GolpeDevastador;
import habilidades.Habilidades;
import habilidades.buffsEdebuffs.ModoBerserker;
import habilidades.buffsEdebuffs.PeleDeAco;
import menus.MenuGuerreiro;
import menus.MenuItens;
import model.itens.PocaoCura;
import model.itens.PocaoMana;
import java.util.*;

public class Guerreiro extends Personagem{
    // Lista de Habilidades
    private List<Habilidades> habilidades = new ArrayList<>();

    // Construtor de Guerreiro
    public Guerreiro(String nome){
        super(nome,400,200,20,50,false);
        setAtaqueOriginal(getAtaque());
        setDefesaOriginal(getDefesa());
        // 3 Poções de cura
        adicionarItem(new PocaoCura());
        adicionarItem(new PocaoCura());
        adicionarItem(new PocaoCura());
        // 2 Poções de mana
        adicionarItem(new PocaoMana());
        adicionarItem(new PocaoMana());
        // Adicionando habilidades à lista de habilidades
        habilidades.add(new GolpeDevastador());
        habilidades.add(new ModoBerserker());
        habilidades.add(new PeleDeAco());
    }
    // Menu de habilidade
    @Override
    public void abrirMenuHabilidades(Scanner scanner, Personagem alvo, SistemaBatalha batalha){
        MenuGuerreiro.abrir(this, scanner, alvo, batalha);
    }
    // Menu de model.itens
    @Override
    public void abrirMenuItens(Scanner scanner, SistemaBatalha batalha) { MenuItens.abrir(this, scanner, batalha); }
    // Habilidades
    public List<Habilidades> getHabilidades(){
        return habilidades;
    }
    // Recebendo Dano
    @Override
    public void receberDano(float dano, SistemaBatalha batalha) {
        super.receberDano(dano, batalha);
    }
}