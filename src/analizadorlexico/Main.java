/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.File;

/**
 *
 * @author sanma
 */
public class Main {
    public static void main(String[] args) {
        File doc = new File("D:\\Documentos\\10 Ciclo\\Compiladores\\fibonacci.txt");
        //AnalizadorLexico AnalizadorLexicoPrueba1 = new AnalizadorLexico("boolean state = true; \n int num=2.69;");
        //AnalizadorLexico AnalizadorLexicoPrueba2 = new AnalizadorLexico(doc);
        AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico(doc);
        //AnalizadorLexico AnalizadorLexicoPrueba2 = new AnalizadorLexico("String cadena = \"Hola mundo\";");
        //AnalizadorLexico AnalizadorLexicoPrueba3 = new AnalizadorLexico("int num=2.69;");
        //AnalizadorLexico AnalizadorLexicoPrueba4 = new AnalizadorLexico("num=2.6+3.8-5/8*5;");
        //AnalizadorLexico AnalizadorLexicoPrueba5 = new AnalizadorLexico("if(5 == 4);");
        

    }
}