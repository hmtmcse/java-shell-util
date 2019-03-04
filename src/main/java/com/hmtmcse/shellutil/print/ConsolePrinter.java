package com.hmtmcse.shellutil.print;

public class ConsolePrinter {

    public static void printLine(Object object, String colorCode){
        System.out.print(colorCode);
        System.out.print(object);
        System.out.println(ColorCode.RESET);
    }

    public static void printLine(Object object){
        System.out.println(object);
    }

    public static void errorPrint(Object object){
        printLine(object, ColorCode.RED_BOLD.toString());
    }

    public static void debugPrint(Object object){
        printLine(object, ColorCode.YELLOW_BOLD.toString());
    }

    public static void successPrint(Object object){
        printLine(object, ColorCode.GREEN_BOLD.toString());
    }

    public static void printEmptyLine(){
        System.out.println("");
    }
}
