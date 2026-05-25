package model;
import java.util.*;
import batalha.SistemaBatalha;
import habilidades.magica.BarreiraDeSangue;
import habilidades.magica.BolaDeFogo;
import habilidades.Habilidades;
import habilidades.magica.RajadaArcana;
import menus.MenuItens;
import menus.MenuMago;

public class Mago extends Personagem{
    // Lista de Habilidades
    private List<Habilidades> habilidades = new ArrayList<>();

    // Construtor de Mago
    public Mago(String nome) {
        super(nome, 350, 250, 3, 3, 40, 20, false);
        ataqueOriginal = ataque;
        defesaOriginal = defesa;
        // Adicionando habilidades à lista de habilidades
        habilidades.add(new BolaDeFogo());
        habilidades.add(new RajadaArcana());
        habilidades.add(new BarreiraDeSangue());
    }
    // Menu de habilidades
    @Override
    public void abrirMenuHabilidades(Scanner scanner, Inimigo inimigo, SistemaBatalha batalha){
        MenuMago.abrir(this, scanner, inimigo, batalha);
    }
    // Menu de itens
    @Override
    public void abrirMenuItens(Scanner scanner) { MenuItens.abrir(this, scanner); }
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