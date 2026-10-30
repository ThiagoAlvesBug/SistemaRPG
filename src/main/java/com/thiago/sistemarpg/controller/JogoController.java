package com.thiago.sistemarpg.controller;

import com.thiago.sistemarpg.model.Guerreiro;
import com.thiago.sistemarpg.model.Mago;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JogoController {

    @GetMapping("/jogo")
    public JogoResponse iniciarJogo(){

        Guerreiro guerreiro = new Guerreiro("Nero");
        Mago mago = new Mago("Magus");

        return new JogoResponse(guerreiro.getNome(), mago.getNome());
    }

}
