import batalha.SistemaBatalha;
import jogo.SistemaJogo;
import model.Guerreiro;
import model.Mago;
import service.Colors;

void main() throws Exception{
    Guerreiro guerreiro = new Guerreiro("Conan");
    Mago mago = new Mago("Magus");

    //Colors.colorfulPrint("Testando texto colorido", Colors.BLUE);

    //SistemaBatalha sistemaBatalha = new SistemaBatalha(guerreiro, mago);
    //sistemaBatalha.iniciar();

    SistemaJogo jogo = new SistemaJogo(guerreiro, mago);
    jogo.iniciar();

/*

                         ‾\____________Ideias____________/‾

- Lista de model.itens;
- Melhorar sistema de defesa (redução de dano);
- Equipamentos;
- Atributos, como força, inteligente, defesa física e mágica...

                        ‾\__Atualizações_desde_o_último_commit__/‾

- Privatizados os atributos de Personagem;
- Implementação de itens através de classes, com poções herdando item;
- Métodos de personagem que criam e adicionam itens ao inventário;


|‾‾‾‾‾|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|‾‾‾‾‾|
|     |                                                                           |     |
|     |                                                                           |     |
|     |                                                                           |     |
|     |                           |‾‾‾‾‾|‾‾‾‾‾‾‾|‾‾‾‾‾|                           |     |
|     |                           |     |       |     |                           |     |
|     |                           |     |_______|     |                           |     |
|     |                           |     |       |     |                           |     |
|     |                           |    /         \    |                           |     |
|     |                           |   /           \   |                           |     |
|     |                           |  /             \  |                           |     |
|     |                           | /               \ |                           |     |
|     |                           |/                 \|                           |     |
|     |___________________________|                   |___________________________|     |
|    /                                                                             \    |
|   /                                                                               \   |
|  /                                                                                 \  |
| /                                                                                   \ |
|/                                                                                     \|
|_______________________________________________________________________________________|


________________________________________________________________________________________
|     |v\      /\    /vvvv\    /\    /\        /\     /\      /\      /\         |     |
|     |vv\    /vv\  /vvvvvv\  /vv\  /vv\  /\  /vv\   /vv\    /vv\    /vv\        |     |
|     |vvv\  /vvvv\/vvvv/\vv\/vvvv\/vvvv\/vv\/vvvv\ /vvvv\  /vvvv\  /vvvv\    /\ |     |
|     |vvvv\/vvvvvv\vvv/vv\v/vvvvvv\vvvv/vvvv\vvvvv/vvvvvv\/vvvvvv\/vvvvvv\  /vv\|     |
|     |vvvv/vv||vvvv\v/vvvv\vvvvvvvv\vv/vvvvvv\vvv/\vvvvvvv\vvvvvvv\vvvvvvv\/vvvv|     |                                                               |     |
|     |vvv/vvv||vvvvv//\||vv\vvvvvvvv\/vvv/\vvv\v/vv\v||vvvv\/\vvvvv\/\vvvv/vvvvv|     |
|     |vv/vvv/\|vvvv//vv\|vvv\vvvvvvv/vvv/vv\vvv/vvvv\||vvvv/vv\vvvv/vv\vv/vvvvvv|     |
|     |V/vvv/vv\vvv//vvvv\vvvvv||vvv/vvv/vvvv\v/vvvvvv\|vvv/vvvv\vv/vvvv\/vvvvvvv|     |
|     |    /vvvv\  /vvvvvv\    ||      /vvvvvv\vvvvvvvv\  /vvvvvv\/vvvvvv\    || |     |
|     |      ||      ||        ||       | ||      ||         ||      ||       || |     |
|     |______||______||________||_______| ||______||_________||______||_______||_|     |
|    /                                                                            \    |
|   /                                                                              \   |
|  /                                                                                \  |
| /                                                                                  \ |
|/____________________________________________________________________________________\|








*/

}