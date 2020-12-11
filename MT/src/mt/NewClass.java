/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Marcos
 */
public class NewClass {
    public static void main(String[] args) {
        ArrayList<String> Q = new ArrayList<String>();
        String q0 = "q0";
        ArrayList<String> F = new ArrayList<String>();
        ArrayList<String> sigma = new ArrayList<String>();
        ArrayList<String> gamma = new ArrayList<String>();
        ArrayList<String[]> delta = new ArrayList<String[]>();
        Q.add("qo"); Q.add("q1"); Q.add("q2"); Q.add("q3");// Q.add("q4"); Q.add("q5");
        F.add("q3");
        sigma.add("a"); sigma.add("!"); sigma.add("1"); 
        gamma.add("a"); gamma.add("!"); gamma.add("1"); 
        String[] array = new String[5];
        String nombre;
        Scanner teclado = new Scanner(System.in);
        
        /*for (int i= 0; i<7; i++){
            System.out.println(i + " :");
            delta.add(new String[]{teclado. nextLine(), teclado. nextLine(), teclado. nextLine(), teclado. nextLine(), teclado. nextLine()});
        }*/                                        
        /////////////PRUEBA 1///////////////////////////
        delta.add(new String[]{"q0", "a", "q0", "1", ">"});
        delta.add(new String[]{"q0", "!", "q1", "!", "<"});
        delta.add(new String[]{"q1", "a", "q1", "a", "<"});
        delta.add(new String[]{"q1", "1", "q2", "a", ">"});
        delta.add(new String[]{"q2", "a", "q2", "a", ">"});
        delta.add(new String[]{"q2", "!", "q1", "a", "<"});
        delta.add(new String[]{"q1", "!", "q3", "!", ">"});
        //MT proof1 = new MT(Q, q0, F, sigma, gamma, delta);
        
        /////////////PRUEBA 2///////////////////////////
        
        /*delta.add(new String[]{"q0", "a", "q1", "x", ">"});
        delta.add(new String[]{"q0", "!", "q5", "!", "-"});
        delta.add(new String[]{"q0", "y", "q4", "y", ">"});
        delta.add(new String[]{"q1", "a", "q1", "a", ">"});
        delta.add(new String[]{"q1", "y", "q1", "y", ">"});
        delta.add(new String[]{"q1", "b", "q2", "y", ">"});
        delta.add(new String[]{"q2", "b", "q2", "z", ">"});
        delta.add(new String[]{"q2", "b", "q2", "z", ">"});
        delta.add(new String[]{"q2", "c", "q3", "z", "<"});
        delta.add(new String[]{"q3", "a", "q3", "a", "<"});
        delta.add(new String[]{"q3", "b", "q3", "b", "<"});
        delta.add(new String[]{"q3", "y", "q3", "y", "<"});
        delta.add(new String[]{"q3", "z", "q3", "z", "<"});
        delta.add(new String[]{"q3", "x", "q0", "x", ">"});
        delta.add(new String[]{"q4", "y", "q4", "y", ">"});
        delta.add(new String[]{"q4", "z", "q4", "z", ">"});
        delta.add(new String[]{"q4", "!", "q5", "!", "-"});*/
        
        /////////////PRUEBA 3///////////////////////////
        //Acepta cadenas con número par de ceros, ejercicio MT Pág. 171
        
        /*delta.add(new String[]{"q0", "1", "q0", "1", ">"});
        delta.add(new String[]{"q0", "0", "q1", "0", ">"});
        delta.add(new String[]{"q0", "!", "q2", "!", "-"});
        delta.add(new String[]{"q1", "1", "q1", "1", ">"});
        delta.add(new String[]{"q1", "0", "q0", "!", ">"});*/
        
        
        MT proof1 = new MT(Q, q0, F, sigma, gamma, delta);
        System.out.println(proof1.procesarCadenaConDetalles("aaa"));
        System.out.println(proof1.procesarFunción("aaa"));
        
                
                        
    } 
}
