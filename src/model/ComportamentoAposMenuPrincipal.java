package model;

public enum ComportamentoAposMenuPrincipal {

    COMPLETOU_TURNO(1),
    NADA(2),
    FUGIU(3);

    private final int valor;

    ComportamentoAposMenuPrincipal(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

    public static ComportamentoAposMenuPrincipal fromInt(int valor){
        for(ComportamentoAposMenuPrincipal op : values()){

            if(op.valor == valor){
                return op;
            }
        }
        return null;
    }
}
