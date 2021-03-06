/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanma
 */
public class AnalizadorLexico {

    private ArrayList<Token> tokens;
    private String codigoFuente;
    private String lexema;
    private int numLine = 1;

    public AnalizadorLexico(String codigoFuente) {
        this.tokens = new ArrayList<>();
        this.codigoFuente = codigoFuente+" ";
        this.lexema = "";
        analyzer();
        printTokens();
    }
    
    public AnalizadorLexico(File codigoFuente) {
        this.tokens = new ArrayList<>();
        this.codigoFuente = getTextFromfile(codigoFuente);        
        this.lexema = "";
        analyzer();
        printTokens();
    }
    
    public String getTextFromfile(File codigoFuente){
        String txt = "";
        try {
            BufferedReader obj = new BufferedReader(new FileReader(codigoFuente));
            String strng;
            try {
                while ((strng = obj.readLine()) != null){
                    txt += strng+" \n ";
                }
            } catch (IOException ex) {
                Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return txt+" ";
    }
    
    private void analyzer(){
        boolean esCadena = false;
        
        for (int i = 0; i < codigoFuente.length()-1; i++) {
            String caracter = codigoFuente.charAt(i)+"";
            String caracterSiguiente = codigoFuente.charAt(i+1)+"";
            
            
            if(" \n".equals(caracter+caracterSiguiente)){
                numLine++;
            }
            
            if (!esCadena) {
                lexema += caracter;
                
                if (caracterSiguiente.equals(" ") || Patrones.esOperadorAritmetico(caracterSiguiente) || Patrones.esOperadorLogico(caracterSiguiente) || Patrones.esOtroSimbolo(caracterSiguiente)) {
                    
                    if(Patrones.esAsignacion(lexema) && !Patrones.esAsignacion(caracterSiguiente)){
                        tokens.add(new Token(lexema, "Asignacion",numLine));
                        lexema = "";
                    }else if(Patrones.esTerminador(lexema)){
                        tokens.add(new Token(lexema, "Terminador",numLine));
                        lexema = "";
                    }else if (Patrones.esReservada(lexema)) {
                        tokens.add(new Token(lexema, "Reservada",numLine));
                        lexema = "";
                    }else if(Patrones.esIdentificador(lexema)){
                        tokens.add(new Token(lexema, "Identificador",numLine));
                        lexema = "";
                    }else if(Patrones.esOperadorLogico(lexema) && !Patrones.esOperadorLogico(lexema+caracterSiguiente)){
                        tokens.add(new Token(lexema, "Operador Logico",numLine));
                        lexema = "";
                    }else if(Patrones.esOperadorAritmetico(lexema)){
                        tokens.add(new Token(lexema, "Operador Aritmetico",numLine));
                        lexema = "";
                    }else if(Patrones.esNumero(lexema)){
                        tokens.add(new Token(lexema, "Numero",numLine));
                        lexema = "";
                    }else if(Patrones.esDelimitador(lexema)){
                        tokens.add(new Token(lexema, "Delimitador",numLine));
                        lexema = "";
                    }
                }else if(Patrones.esComilla(caracter)){
                    esCadena = true;
                }else if(Patrones.esAsignacion(lexema) && !Patrones.esAsignacion(caracterSiguiente)){
                    tokens.add(new Token(lexema, "Asignacion",numLine));
                    lexema = "";
                }else if(Patrones.esOperadorAritmetico(lexema)){
                        tokens.add(new Token(lexema, "Operador Aritmetico",numLine));
                        lexema = "";
                }else if(Patrones.esOperadorLogico(lexema) && !Patrones.esOperadorLogico(lexema+caracterSiguiente)){
                        tokens.add(new Token(lexema, "Operador Logico",numLine));
                        lexema = "";
                }else if(Patrones.esDelimitador(lexema)){
                        tokens.add(new Token(lexema, "Delimitador",numLine));
                        lexema = "";
                }
                
                if(caracter.equals(" ")){
                    lexema = "";
                }
                
            }else{
                lexema += caracter;
                if(Patrones.esComilla(caracter)){
                    tokens.add(new Token(lexema, "Cadena",numLine));
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

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public String getCodigoFuente() {
        return codigoFuente;
    }

    public void setCodigoFuente(String codigoFuente) {
        this.codigoFuente = codigoFuente;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
}
