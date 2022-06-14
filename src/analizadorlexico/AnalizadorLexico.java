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
        analyzer();
        printTokens();
    }
    
    private void analyzer(){
        boolean esCadena = false;
        
        for (int i = 0; i < codigoFuente.length()-1; i++) {
            String caracter = codigoFuente.charAt(i)+"";
            String caracterSiguiente = codigoFuente.charAt(i+1)+"";
            
            if (!esCadena) {
                lexema += caracter;
                System.out.println(lexema);
                
                if (caracterSiguiente.equals(" ") || Patrones.esOperadorAritmetico(caracterSiguiente) || Patrones.esOperadorLogico(caracterSiguiente) || Patrones.esOtroSimbolo(caracterSiguiente)) {
                    
                    if(Patrones.esAsignacion(lexema) && !Patrones.esAsignacion(caracterSiguiente)){
                        tokens.add(new Token(lexema, "Asignacion"));
                        lexema = "";
                    }else if(Patrones.esTerminador(lexema)){
                        tokens.add(new Token(lexema, "Terminador"));
                        lexema = "";
                    }else if (Patrones.esReservada(lexema)) {
                        tokens.add(new Token(lexema, "Reservada"));
                        lexema = "";
                    }else if(Patrones.esIdentificador(lexema)){
                        tokens.add(new Token(lexema, "Identificador"));
                        lexema = "";
                    }else if(Patrones.esOperadorLogico(lexema) && !Patrones.esOperadorLogico(lexema+caracterSiguiente)){
                        tokens.add(new Token(lexema, "Operador Logico"));
                        lexema = "";
                    }else if(Patrones.esOperadorAritmetico(lexema)){
                        tokens.add(new Token(lexema, "Operador Aritmetico"));
                        lexema = "";
                    }else if(Patrones.esNumero(lexema)){
                        tokens.add(new Token(lexema, "Numero"));
                        lexema = "";
                    }else if(Patrones.esDelimitador(lexema)){
                        tokens.add(new Token(lexema, "Delimitador"));
                        lexema = "";
                    }
                }else if(Patrones.esComilla(caracter)){
                    esCadena = true;
                }else if(Patrones.esAsignacion(lexema) && !Patrones.esAsignacion(caracterSiguiente)){
                    tokens.add(new Token(lexema, "Asignacion"));
                    lexema = "";
                }else if(Patrones.esOperadorAritmetico(lexema)){
                        tokens.add(new Token(lexema, "Operador Aritmetico"));
                        lexema = "";
                }else if(Patrones.esOperadorLogico(lexema) && !Patrones.esOperadorLogico(lexema+caracterSiguiente)){
                        tokens.add(new Token(lexema, "Operador Logico"));
                        lexema = "";
                }else if(Patrones.esDelimitador(lexema)){
                        tokens.add(new Token(lexema, "Delimitador"));
                        lexema = "";
                }
                
                if(caracter.equals(" ")){
                    lexema = "";
                }
                
            }else{
                lexema += caracter;
                if(Patrones.esComilla(caracter)){
                    tokens.add(new Token(lexema, "Cadena"));
                    lexema = "";
                    esCadena = false;
                }
            }
        }
    }
    
    public void printTokens(){
        System.out.println("------------------Codigo Fuente------------------");
        System.out.println(codigoFuente);
        System.out.println("------------------TOKENS------------------");
        for(Token token : tokens){
            System.out.println(token);
        }    
    }

    
    
}
