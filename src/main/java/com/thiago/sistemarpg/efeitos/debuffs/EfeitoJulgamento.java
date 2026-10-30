package com.thiago.sistemarpg.efeitos.debuffs;
import com.thiago.sistemarpg.batalha.SistemaBatalha;
import com.thiago.sistemarpg.efeitos.EfeitoStatus;
import com.thiago.sistemarpg.model.Personagem;

public class EfeitoJulgamento extends EfeitoStatus {
    private boolean aplicado = false;

    public EfeitoJulgamento(int duracao) {
        super("Marca do Julgamento", duracao);
    }

    @Override
    public void aplicar(Personagem alvo, SistemaBatalha batalha) {
        // aplicando efeito
        if (!aplicado) {
            alvo.setDefesa(alvo.getDefesaOriginal() / 2);
            batalha.adicionarLog(alvo.getNome() + " foi amaldiçoado!");
            aplicado = true;
        }
        batalha.adicionarLog("☠️ Marca do Julgamento: " + duracao + " turno(s) restante(s).");
        reduzirDuracao();
        // removendo efeito
        if(duracaoExpirou()){
            alvo.setDefesa(alvo.getDefesaOriginal());
            batalha.adicionarLog(alvo.getNome() + " não está mais amaldiçoado.");
        }
    }
}