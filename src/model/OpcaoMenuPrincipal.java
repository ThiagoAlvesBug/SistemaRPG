package model;

public enum OpcaoMenuPrincipal {

    ATACAR(1),
    ABRIR_MENU_HABILIDADES(2),
    DEFENDER(3),
    ABRIR_MENU_ITENS(4),
    TROCAR_PERSONAGEM(5),
    FUGIR(6);

    private final int valor;

    OpcaoMenuPrincipal(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

    public static OpcaoMenuPrincipal fromInt(int valor){
        for(OpcaoMenuPrincipal op : values()){

            if(op.valor == valor){
                return op;
            }
        }
        return null;
    }
}
