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
public class Patrones {
    final static String RESERVADA [] = {"abstract","assert","boolean","boolean","byte","case","catch","char","class","continue","default","do","double","else","exports","extends","final","finally","float","for","if","implements","import","instanceof","int","interface","long","module","native","new","package","private","protected","public","requires","return","short","static","super","switch","syncronized","this","throw","throws","transient","try","void","volatile","while","true","null","false","var","const","goto","String"};
    final static String COMILLA = "\"";
    final static String NUMERO = "[+-]?\\d*(\\.\\d+)?";
    final static String LETRA [] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    final static String OPERADORES_ARITMETICOS [] = {"+","-","*","/"};
    final static String OPERADORES_LOGICOS [] = {"=","==","<",">","<=",">=","!"};
    final static String OTROS_SIMBOLOS [] = {"#","$","%","&","'","(",")","*","+",",","-",".",":","|","~"};
    
    public static boolean esReservada(String cadena){
        for (String reservada: RESERVADA) {
            if (reservada.equals(cadena)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean esComilla(String cadena){
        return cadena.equals(COMILLA);
    }
    
    public static boolean esNumero(String cadena){
        return cadena.matches("[+-]?\\d*(\\.\\d+)?");
    }
    
    public static boolean esOperadorLogico(String cadena){
        for (String operador_logico: OPERADORES_LOGICOS) {
            if (operador_logico.equals(cadena)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean esOperadorAritmetico(String cadena){
        for (String operador_aritmetico: OPERADORES_ARITMETICOS) {
            if (operador_aritmetico.equals(cadena)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean esOtroSimbolo(String cadena){
        for (String simbolo: OTROS_SIMBOLOS) {
            if (simbolo.equals(cadena)) {
                return true;
            }
        }
        return false;
    }
    
    
    public static boolean esLetra(String cadena){
        for (String letra: LETRA) {
            if (letra.equals(cadena)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean esIdentificador(String cadena){
        if(esLetra(cadena.charAt(0)+"")){
            for (int i = 1; i < cadena.length(); i++) {
                if (esOtroSimbolo(cadena.charAt(i)+"") || esOperadorLogico(cadena.charAt(i)+"") || esOperadorAritmetico(cadena.charAt(i)+"")) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

