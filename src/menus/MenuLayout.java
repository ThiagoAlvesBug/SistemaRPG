package menus;

public class MenuLayout {
    // Alinhando à esquerda
    public String alinharEsquerda(String texto, int largura, String caracterRepetir){
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0,largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return textoRecortado + preenchimento;
    }
    // Alinhando à direita
    public String alinharDireita(String texto, int largura, String caracterRepetir){
        var textoPreenchido = texto + " ".repeat(largura);
        var textoRecortado = textoPreenchido.substring(0,largura).trim();
        int larguraPreenchimento = largura - textoRecortado.length();
        var preenchimento = caracterRepetir.repeat(larguraPreenchimento);
        return preenchimento + textoRecortado;
    }
    // Alinhando no centro
    public String alinharCentro(String texto, int largura, String caracterRepetir){
        var textoRecortado = (texto + " ".repeat(largura)).substring(0, largura).trim();
        int espaçoTotal = largura - textoRecortado.length();
        int esquerda = espaçoTotal/2;
        int direita = espaçoTotal - esquerda;
        //   Preencher à esquerda_________+_________Conteúdo_________+_________Preencher à direita
        return caracterRepetir.repeat(esquerda) + textoRecortado + caracterRepetir.repeat(direita);
    }
    // Alinhando dois itens no centro
    public String alinharDoisItensCentro(String texto1, String texto2, int largura, String caracterRepetir) {
        texto1 = texto1.trim();
        texto2 = texto2.trim();
        int tamanhoTextos = texto1.length() + texto2.length();
        int espacoLivre = largura - tamanhoTextos;
        if (espacoLivre <= 0) {
            return (texto1 + texto2).substring(0, largura);
        }

        int esquerda = espacoLivre / 3;
        int meio = espacoLivre / 3;
        int direita = espacoLivre - esquerda - meio;
        //     Preencher à direita_____+_____Conteúdo 1_____+_____Preencher meio_____+_____Conteúdo 2_____+_____Preencher à direita
        return caracterRepetir.repeat(esquerda) + texto1 + caracterRepetir.repeat(meio) + texto2 + caracterRepetir.repeat(direita);
    }
}
