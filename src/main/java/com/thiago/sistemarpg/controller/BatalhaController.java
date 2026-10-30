package com.thiago.sistemarpg.controller;

import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.service.BatalhaService;
import com.thiago.sistemarpg.habilidades.Habilidades;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batalha")
@CrossOrigin(origins = "http://localhost:5173")
public class BatalhaController {

    private final BatalhaService batalhaService;

    public BatalhaController(BatalhaService batalhaService) {
        this.batalhaService = batalhaService;
    }

    // Início da Batalha
    @GetMapping("/iniciar")
    public BatalhaResponse iniciarBatalha() {

        batalhaService.iniciarBatalha();
        SistemaBatalha batalha = batalhaService.getBatalha();
        return criarResposta(batalha);
    }

    // Atacar
    @PostMapping("/atacar")
    public BatalhaResponse atacar() {
        SistemaBatalha batalha = batalhaService.getBatalha();

        if(batalha.verificarFimDeBatalha()){
            return criarResposta(batalha);
        }

        // turno do jogador
        batalha.getJogadorAtivo().atacar(batalha.getInimigo(), batalha);
        batalha.aplicarEfeitos();

        // turno do inimigo
        if (!batalha.verificarFimDeBatalha()) {
            batalha.turnoInimigo(batalha);
            batalha.verificarFimDeBatalha();
        }
        return criarResposta(batalha);
    }

    // Defender
    @PostMapping("/defender")
    public BatalhaResponse defender() {
        SistemaBatalha batalha = batalhaService.getBatalha();

        if(batalha.verificarFimDeBatalha()){
            return criarResposta(batalha);
        }
        batalha.aplicarEfeitos();

        // turno do inimigo
        if (!batalha.verificarFimDeBatalha()) {
            batalha.turnoInimigo(batalha);
            batalha.verificarFimDeBatalha();
        }
        return criarResposta(batalha);
    }

    // Habilidades
    @PostMapping("/habilidade")
    public BatalhaResponse usarHabilidade(
            @RequestBody HabilidadeRequest request
    ) {
        SistemaBatalha batalha = batalhaService.getBatalha();
        String nomeHabilidade = request.getHabilidade();

        if(batalha.verificarFimDeBatalha()){
            return criarResposta(batalha);
        }

        for (Habilidades habilidade : batalha.getJogadorAtivo().getHabilidades()) {
            if (habilidade.getNome().equalsIgnoreCase(nomeHabilidade)) {
                habilidade.usar(batalha.getJogadorAtivo(), batalha.getInimigo(), batalha);
                batalha.aplicarEfeitos();

                if (!batalha.verificarFimDeBatalha()) {
                    batalha.turnoInimigo(batalha);
                    batalha.verificarFimDeBatalha();
                }
            }
        }
        return criarResposta(batalha);
    }

    // Trocar personagem
    @PostMapping("/trocar")
    public BatalhaResponse trocarPersonagem() {

        SistemaBatalha batalha = batalhaService.getBatalha();
        if(batalha.verificarFimDeBatalha()){
            return criarResposta(batalha);
        }

        batalha.trocarPersonagem();
        return criarResposta(batalha);
    }

    // Atualizando status a cada turno e gerando um Log de batalha
    private BatalhaResponse criarResposta(SistemaBatalha batalha) {

        return new BatalhaResponse(
                batalha.getJogadorAtivo().getNome(),
                batalha.getGuerreiro().getNome(),
                batalha.getGuerreiro().getVida(),
                batalha.getGuerreiro().getMana(),

                batalha.getMago().getNome(),
                batalha.getMago().getVida(),
                batalha.getMago().getMana(),

                batalha.getInimigo().getNome(),
                batalha.getInimigo().getVida(),

                batalha.getLogBatalha()
        );
    }
}