package service;

public class Colors {

    public static final String RESET = "\u001B[0m";

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    public static void colorfulPrint(String text, String color)
    {
        //TODO: Verificar se a cor é válida
        System.out.println(color + text + RESET);
    }
}