/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sanma
 */
public class AnalizadorSintactico {

    private HashMap<String, String> TablaSimbolos = new HashMap<String, String>();
    private String mensaje="";
    private ArrayList<Token> tokens;

    public AnalizadorSintactico(File doc) {
        AnalizadorLexico AnalizadorLexico = new AnalizadorLexico(doc);
        this.tokens = AnalizadorLexico.getTokens();
        analizar();
        imprimir();
    }

    public void analizar() {
        int index = 0;
        while (index < tokens.size()) {
            
            if (tokens.get(index).getTipo().equals("Reservada")) {

                if (index + 1 < tokens.size() && tokens.get(index + 1).getTipo() == "Identificador") {
                    if (index + 2 < tokens.size() && tokens.get(index + 2).getTipo() == "Terminador") {
                        this.mensaje += "Linea " + tokens.get(index).getLine() + " Correcta \n";
                        index = index + 2;
                        
                        TablaSimbolos.put(tokens.get(index+1).getLexema(),indentificarLexema(index));
                        
                    } else if (index + 4 < tokens.size() && tokens.get(index + 2).getTipo() == "Asignacion" && tokens.get(index + 4).getTipo() == "Terminador") {
                        if (tokens.get(index + 3).getTipo() == "Cadena" || tokens.get(index + 3).getTipo() == "Numero" || tokens.get(index + 3).getTipo() == "Reservada" && tokens.get(index + 3).getLexema() == "true" && tokens.get(index + 3).getLexema() == "false") {
                            this.mensaje += "Linea " + tokens.get(index).getLine() + " Correcta \n";
                            index = index + 4;
                            //
                            TablaSimbolos.put(tokens.get(index+1).getLexema(),indentificarLexema(index));
                        } else {
                            this.mensaje += "Error en linea " + tokens.get(index).getLine() + ", tipo de dato no es compatible";
                            break;
                        }
                    } else {
                        this.mensaje += "Error en linea " + tokens.get(index).getLine();
                        break;
                    }
                } else {
                    this.mensaje += "Error en linea " + tokens.get(index).getLine();
                    break;
                }
            } else if (tokens.get(index).getTipo().equals("Identificador")) {
                if (index + 1 < tokens.size() && tokens.get(index + 1).getTipo() == "Asignacion") {
                    if (index + 2 < tokens.size() && tokens.get(index + 2).getTipo() == "Numero" || tokens.get(index + 2).getTipo() == "Cadena") {
                                              
                        if (TablaSimbolos.get(tokens.get(index + 2).getLexema()) == tokens.get(index + 2).getTipo()) {
                            this.mensaje += "Linea " + tokens.get(index).getLine() + " Correcta \n";
                            index = index + 2;
                        } else if (index + 4 < tokens.size() && tokens.get(index + 2).getTipo() == "Numero" && tokens.get(index + 3).getTipo() == "Operador Aritmetico" && tokens.get(index + 4).getTipo() == "Numero") {
                            this.mensaje += "Linea " + tokens.get(index).getLine() + " Correcta \n";
                            index = index + 2;
                        } else if (index + 4 < tokens.size() && tokens.get(index + 2).getTipo() == "Cadena" && tokens.get(index + 3).getLexema() == "+" && tokens.get(index + 4).getTipo() == "Cadena") {
                            this.mensaje += "Linea " + tokens.get(index).getLine() + " Correcta \n";
                            index = index + 2;
                        } else {
                            this.mensaje += "Error en linea " + tokens.get(index).getLine() + ", tipo de datos";
                            break;
                        }
                    }
                } else {
                    this.mensaje += "Error en linea " + tokens.get(index).getLine() + ", cerca de la asignacion";
                    break;
                }
            }
            index++;
        }

    }
    
    public String indentificarLexema(int index){
        String lexemaVariable = tokens.get(index).getLexema();
        if (lexemaVariable=="String") {
            return "Cadena";
        }else if(lexemaVariable=="int"){
            return "Numero";
        }
        return "";
    }
    
    public void imprimir(){
        System.out.println("--------------Analisis Sintactico--------------");
        System.out.println(this.mensaje);
        System.out.println("-----------------------------------------------");
    }

}
