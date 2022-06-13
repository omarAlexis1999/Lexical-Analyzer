/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.util.ArrayList;

/**
 *
 * @author sanma
 */
public class AnalizadorLexico {

    private ArrayList<Token> tokens;
    private String codigoFuente;
    private int estado;
    private String lexema;

    public AnalizadorLexico(String codigoFuente) {
        this.tokens = new ArrayList<>();
        this.codigoFuente = codigoFuente+" ";
        this.estado = 0;
        this.lexema = "";
        analizador();
    }
    
    private void analizador(){
        boolean esCadena = false;
        
        for (int i = 0; i < codigoFuente.length()-1; i++) {
            String caracter = codigoFuente.charAt(i)+"";
            String caracterSiguiente = codigoFuente.charAt(i+1)+"";
            
            if (!esCadena) {
                if (Patrones.esComilla(caracterSiguiente) || Patrones.esOperadorAritmetico(caracterSiguiente) || Patrones.esOperadorLogico(caracterSiguiente) || Patrones.esOtroSimbolo(caracterSiguiente)) {
                    if (Patrones.esReservada(lexema)) {
                        tokens.add(new Token(lexema, "Reservada"));
                        lexema = "";
                    }else if(Patrones.esIdentificador(lexema)){
                        tokens.add(new Token(lexema, "Identificador"));
                        lexema = "";
                    }else if(Patrones.esOperadorLogico(lexema)){
                        tokens.add(new Token(lexema, "Operador Logico"));
                        lexema = "";
                    }else if(Patrones.esOperadorAritmetico(lexema)){
                        tokens.add(new Token(lexema, "Operador Aritmetico"));
                        lexema = "";
                    }else if(Patrones.esNumero(lexema)){
                        
                    }else if(Patrones.esComilla(lexema)){
                        esCadena = true;
                    }
                }else{
                    lexema += caracter;
                }
            }else{
                lexema += caracter;
                
            }
            
            
            
            
            switch(estado){
                // Inicio | Espacios
                case 0:
                    if (caracter.equals(" ")) {
                        estado = 0;
                    }else if (Patrones.esLetra(caracter)) {
                        estado = 1;
                    }
                    else if(Patrones.esNumero(caracter)){
                        estado = 2;
                    }else if(Patrones.esOperadorAritmetico(caracter)){
                        estado = 3;
                    }else if(Patrones.esOperadorLogico(caracter)){
                        estado = 4;
                    }else if(Patrones.esOtroSimbolo(caracter)){
                        estado = 5;
                    }
                    lexema = caracter;
                break;
                
                // Letras
                case 1:
                    if (caracter.equals(" ")) {
                        estado = 0;
                    }else if (Patrones.esLetra(caracter)) {
                        lexema += caracter;
                        
                        if (caracterSiguiente.equals(" ")) {
                            tokens.add(new Token(lexema, lexema));
                        }
                        estado = 1;
                        
                    }else if(Patrones.esNumero(caracter)){
                        if (caracterSiguiente.equals(" ")) {
                            
                        }
                        estado = 2;
                    }else if(Patrones.esOperadorAritmetico(caracter)){
                        estado = 3;
                    }else if(Patrones.esOperadorLogico(caracter)){
                        estado = 4;
                    }else if(Patrones.esOtroSimbolo(caracter)){
                        estado = 5;
                    }
                break;
                
                // Numeros
                case 2:
                    
                break;
                
                // Operador Aritmetico
                case 3:
                    
                break;
                
                // Operador Logico
                case 4:
                    
                break;
                
                // Otro Simbolo
                case 5:
                    
                break;
            }
        }
    }

    
    
}
