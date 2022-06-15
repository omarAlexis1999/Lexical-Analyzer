/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

/**
 *
 * @author sanma
 */
public class Main {
    public static void main(String[] args) {
        AnalizadorLexico AnalizadorLexicoPrueba1 = new AnalizadorLexico("boolean state = true;");
        AnalizadorLexico AnalizadorLexicoPrueba2 = new AnalizadorLexico("String cadena = \"Hola mundo\";");
        AnalizadorLexico AnalizadorLexicoPrueba3 = new AnalizadorLexico("int num=2.69;");
        AnalizadorLexico AnalizadorLexicoPrueba4 = new AnalizadorLexico("num=2.6+3.8-5/8*5;");
        AnalizadorLexico AnalizadorLexicoPrueba5 = new AnalizadorLexico("if(5 == 4);");
    }
}