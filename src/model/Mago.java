package model;
import batalha.SistemaBatalha;
import habilidades.magica.BarreiraDeSangue;
import habilidades.magica.BolaDeFogo;
import habilidades.Habilidades;
import habilidades.magica.RajadaArcana;
import menus.MenuItens;
import menus.MenuMago;
import model.itens.PocaoCura;
import model.itens.PocaoMana;
import java.util.*;

public class Mago extends Personagem{
    // Lista de Habilidades
    private List<Habilidades> habilidades = new ArrayList<>();

    // Construtor de Mago
    public Mago(String nome) {
        super(nome, 350, 250,40, 20, false);
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
    public void abrirMenuHabilidades(Scanner scanner, Personagem alvo, SistemaBatalha batalha){
        MenuMago.abrir(this, scanner, alvo, batalha);
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