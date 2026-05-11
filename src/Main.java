import Batalha.SistemaBatalha;
import model.*;

void main() {
    SistemaBatalha batalha = new SistemaBatalha();
    batalha.iniciar();
}

/*
    Ao atacar:
    HP: 100
    HP: 100 -15
    HP: 85

    Menu de Personagens:
    > Guerreiro Nero   HP: 120   (Habilidade ativa por +1 turnos: )
      Maga Jade        HP: 100

    ###################################################
    #####     [1] ...                 [2] ...     #####
    #####                 [3] ...                 #####
    #####     [4] ...                 [5] ...     #####
    ###################################################
*/