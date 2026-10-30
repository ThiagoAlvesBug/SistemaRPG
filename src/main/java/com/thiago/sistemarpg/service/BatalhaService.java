package com.thiago.sistemarpg.service;

import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.model.Guerreiro;
import com.thiago.sistemarpg.model.Mago;
import com.thiago.sistemarpg.model.Inimigo;
import com.thiago.sistemarpg.model.Chefe;
import org.springframework.stereotype.Service;

@Service
public class BatalhaService {

    private SistemaBatalha batalha;

    public void iniciarBatalha(){
        Guerreiro guerreiro = new Guerreiro("Nero");
        Mago mago = new Mago("Magus");
        Inimigo inimigo = new Inimigo("Andariel");

        batalha = new SistemaBatalha(guerreiro, mago, inimigo);
    }

    public SistemaBatalha getBatalha(){
        return batalha;
    }

}
