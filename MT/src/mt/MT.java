/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashMap;
//import javafx.util.Pair;

/**
 *
 * @author Marcos
 */
public class MT {
    
    public ArrayList<String> Q; //states
    public String q0; //initial
    public ArrayList<String> F; //accepting
    public ArrayList<String> Sigma; //input_alphabet
    public ArrayList<String> Gamma; //tape_alphabet
    //FUNCIÓN DE TRANSICIÓN: Hashmap<Pair<qi, sigma_i>, Pair<qi+1,gamma_i, direction>
    //public HashMap<Pair<String, String>, HashMap<Pair<String, String>, String>> delta;
    public ArrayList<String[]> delta;
    public ArrayList<String> tape = new ArrayList<String>(); 
    
    //---------------------CONSTRUCTORES---------------------------
    public MT(ArrayList<String> states, String initial, ArrayList<String> accepting, ArrayList<String> input_alphabet,
            ArrayList<String> tape_alphabet, ArrayList<String[]> delta){
        this.Q = states;
        this.q0 = initial;
        this.F = accepting;
        this.Sigma = input_alphabet;
        this.Gamma = tape_alphabet; 
        this.delta = delta;              
    }
    
    
    //Inicializar atributos por medio del archivo
    public MT(String nombreArchivo) throws FileNotFoundException, IOException{
        FileReader f = new FileReader(nombreArchivo);
        BufferedReader b = new BufferedReader(f);        
    }
    
    public MT(){
        
    }
    //---------------------GETTERS Y SETTERS---------------------------
    /**
     * @return the Q
     */
    public ArrayList<String> getQ() {
        return Q;
    }

    /**
     * @return the q0
     */
    public String getq0() {
        return q0;
    }

    /**
     * @return the F
     */
    public ArrayList<String> getF() {
        return F;
    }

    /**
     * @return the Sigma
     */
    public ArrayList<String> getSigma() {
        return Sigma;
    }

    /**
     * @return the Gamma
     */
    public ArrayList<String> getGamma() {
        return Gamma;
    }

    /**
     * @return the delta
     */
    //public ArrayList<String>[] getDelta() {
    //    return delta;
    //}

    /**
     * @return the tape
     */
    public ArrayList<String> getTape() {
        return tape;
    }

    /**
     * @param Q the Q to set
     */
    public void setQ(ArrayList<String> Q) {
        this.Q = Q;
    }

    /**
     * @param q0 the q0 to set
     */
    public void setq0(String q0) {
        this.q0 = q0;
    }

    /**
     * @param F the F to set
     */
    public void setF(ArrayList<String> F) {
        this.F = F;
    }

    /**
     * @param Sigma the Sigma to set
     */
    public void setSigma(ArrayList<String> Sigma) {
        this.Sigma = Sigma;
    }

    /**
     * @param Gamma the Gamma to set
     */
    public void setGamma(ArrayList<String> Gamma) {
        this.Gamma = Gamma;
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(ArrayList<String[]> delta) {
        this.delta = delta;
    }

    /**
     * @param tape the tape to set
     */
    public void setTape(ArrayList<String> tape) {
        this.tape = tape;
    }
    
    
    //---------------------DESARROLLO MÉTODOS---------------------------
    public boolean procesarCadena(String cadena){
        for(int i=0; i<delta.size(); i++){
            //System.out.println(Arrays.toString(delta.get(i)));//%
        }
        //System.out.println();
        String actual = this.q0;
        int indexOfTape = 0;
        //String cadenawq = transicion.replaceAll("[?]", ":");
        //String transicióon.split(":");
        String[] cad = new String[cadena.length()];
        cad = cadena.split("");

        for (int i=0; i<cad.length; i++){
            this.tape.add(cad[i]);
            //System.out.println(this.tape.get(i));
        }
           int aux = this.tape.size();//utilizado para las iteraciones de la MT
        //for (int i=0; i<cadena.length(); i++){        
            //if (this.q0.equals(this.delta.get(0)[0])){//Verificar que el q0 sea el mismo q0 en transición
            for(int j=0; j<aux; j++){
                if(this.F.contains(actual)){
                    //System.out.println("Retornando True"); //%
                   return true;        //si llega a un estado de aceptación retorno true                      
                }
                //System.out.println('\n'); //%
                //System.out.println("iteración" + j + '\n'); //%
                    //Verifica la función de transición para procesar la cadena
                    //problema que no vuelve a recorre la FT completa!!!!!
                for(int k=0; k<delta.size(); k++){    
                    if((actual.equals(this.delta.get(k)[0]) && (this.tape.get(indexOfTape).equals(this.delta.get(k)[1])))){
                        aux++;//Cada vez que se recorra un paso se añade 1 al auxiliar para facilitar la iteración y evaluar la cadena
                        //Verifica y sobreescribe caracteres en la cinta
                        /*System.out.println("Entra en la F-Transición " + j);//%
                        System.out.println("Estado actual " + actual);//%
                        System.out.println("caracter de entrada " + this.tape.get(indexOfTape));//%
                        System.out.println("caracter de cinta " + this.delta.get(k)[3]);//%*/
                        //System.out.println("delta fecha " + this.delta.get(j)[4]);//%
                        if("<".equals(this.delta.get(k)[4])){
                            //System.out.println("Entra en el delta <" + j);//%
                            if(indexOfTape==0){
                               //System.out.println("Entra en el if 1 <" + j);//%
                                this.tape.set(indexOfTape ,this.delta.get(k)[3]);//escribir caracter en la cinta
                                this.tape.add(0 ,"!");//Añadir ! en la cinta al lado izquierdo
                                //indexOfTape    
                            }
                            else{
                                //System.out.println("Entra en el if 2 <" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                indexOfTape--;
                            }

                        }
                        else if (">".equals(this.delta.get(k)[4])){
                            //System.out.println("Entra en el delta >: " + j);//%
                            if(indexOfTape==this.tape.size()-1){
                                //System.out.println("Entra en el if 1 >" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                this.tape.add("!");//Añadir ! a la cinta a la derecha
                                indexOfTape++;    
                            }
                            else{
                                //System.out.println("Entra en el if 2 >" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                indexOfTape++;    
                            }
                        }
                        else{
                            //System.out.println("Entra en el delta -?" + j);//%
                            this.tape.set(indexOfTape,this.delta.get(k)[3]);
                        }              
                        //System.out.print("Tape: ");//%    
                        for(int i=0; i<tape.size(); i++){
                            //System.out.print(tape.get(i));//%
                        }
                        //System.out.println("");//% 
                        //System.out.println("IndexofTape: "+ indexOfTape);//% 
                        actual = this.delta.get(k)[2];//cambiar estado actual al siguiente en la Ftransición
                        //System.out.println("Actual: " + actual);//%
                        /*if(j == this.tape.size()-1)
                            System.out.println("Se acabó");*/
                        break;
                    }                    
                }    
            } 
        /*//%%%%%%%%%%%%%%%    
        System.out.print("Tape fin: ");//%    
        for(int i=0; i<tape.size(); i++){
            System.out.print(tape.get(i));//%
        }
        System.out.println("");//% */
        if (this.F.contains(actual))
            return true;
    
        return false;
    }
    
    public boolean procesarCadenaConDetalles(String cadena){        
        
        for(int i=0; i<delta.size(); i++){
            //System.out.println(Arrays.toString(delta.get(i)));//%
        }
        //System.out.println();
        String actual = this.q0;
        int indexOfTape = 0;
        //String cadenawq = transicion.replaceAll("[?]", ":");
        //String transicióon.split(":");
        String[] cad = new String[cadena.length()];
        cad = cadena.split("");
        
        
        for (int i=0; i<cad.length; i++){
            this.tape.add(cad[i]);
           // System.out.println(this.tape.get(i));
        }
        ArrayList<String> detalles = new ArrayList<>(this.tape);
        int aux = this.tape.size();//utilizado para las iteraciones de la MT
        
        String state = actual.replace(actual, "(" + actual + ")");                        
        String move = this.delta.get(0)[4].replace(this.delta.get(0)[4], "-" + this.delta.get(0)[4]);                          
        detalles.add(indexOfTape,state);                        
        detalles.add(move);        
        
        //for (int i=0; i<cadena.length(); i++){        
            //if (this.q0.equals(this.delta.get(0)[0])){//Verificar que el q0 sea el mismo q0 en transición
            for(int j=0; j<aux; j++){
                if(this.F.contains(actual)){
                    //System.out.println("Retornando True"); //%
                    System.out.println("Detalles del procesamiento: " + '\n');
                    for(int i = 0; i<detalles.size(); i++){
                        System.out.print(detalles.get(i));
                    }  
                    System.out.println("");
                   return true;        //si llega a un estado de aceptación retorno true                      
                }
                //System.out.println('\n'); //%
                //System.out.println("iteración" + j + '\n'); //%
                    //Verifica la función de transición para procesar la cadena
                    //problema que no vuelve a recorre la FT completa!!!!!
                  
                for(int k=0; k<delta.size(); k++){    
                    if((actual.equals(this.delta.get(k)[0]) && (this.tape.get(indexOfTape).equals(this.delta.get(k)[1])))){
                        aux++;//Cada vez que se recorra un paso se añade 1 al auxiliar para facilitar la iteración y evaluar la cadena
                        //Verifica y sobreescribe caracteres en la cinta                                           
                        
                       /* System.out.println("Entra en la F-Transición " + j);//%
                        System.out.println("Estado actual " + actual);//%
                        System.out.println("caracter de entrada " + this.tape.get(indexOfTape));//%
                        System.out.println("caracter de cinta " + this.delta.get(k)[3]);//%*/
                        //System.out.println("delta fecha " + this.delta.get(j)[4]);//%
                        if("<".equals(this.delta.get(k)[4])){
                            //System.out.println("Entra en el delta <" + j);//%
                            if(indexOfTape==0){
                                //System.out.println("Entra en el if 1 <" + j);//%
                                this.tape.set(indexOfTape ,this.delta.get(k)[3]);//escribir caracter en la cinta
                                this.tape.add(0 ,"!");//Añadir ! en la cinta al lado izquierdo
                                //indexOfTape    
                            }
                            else{
                                //System.out.println("Entra en el if 2 <" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                indexOfTape--;
                            }

                        }
                        else if (">".equals(this.delta.get(k)[4])){
                            //System.out.println("Entra en el delta >: " + j);//%
                            if(indexOfTape==this.tape.size()-1){
                               // System.out.println("Entra en el if 1 >" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                this.tape.add("!");//Añadir ! a la cinta a la derecha
                                indexOfTape++;    
                            }
                            else{
                               // System.out.println("Entra en el if 2 >" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                indexOfTape++;    
                            }
                        }
                        else{
                          //  System.out.println("Entra en el delta -?" + j);//%
                            this.tape.set(indexOfTape,this.delta.get(k)[3]);
                        }              
                       // System.out.print("Tape: ");//%    
                        for(int i=0; i<tape.size(); i++){
                           // System.out.print(tape.get(i));//%
                        }
                      //  System.out.println("");//% 
                      //  System.out.println("IndexofTape: "+ indexOfTape);//% 
                        actual = this.delta.get(k)[2];//cambiar estado actual al siguiente en la Ftransición
                      //  System.out.println("Actual: " + actual);//%
                      //  System.out.println("Move: " + this.delta.get(k)[4]);//%
                        //Guarda el detalle del procesamiento
                        
                        state = actual.replace(actual, "(" + actual + ")");                        
                        move = this.delta.get(k)[4].replace(this.delta.get(k)[4], "-" + this.delta.get(k)[4]);
                        ArrayList<String> auxList = new ArrayList<>(this.tape);                        
                        auxList.add(indexOfTape, state);
                        auxList.add(move);
                        for(int i=0; i<auxList.size(); i++){
                            detalles.add(auxList.get(i));
                        }
                            
                        
                        break;
                    }                    
                }    
            } 
        //%%%%%%%%%%%%%%%    
       // System.out.print("Tapen: ");//%    
        for(int i=0; i<this.tape.size(); i++){
          //  System.out.print(tape.get(i));//%
        }
       // System.out.println("");//% 
        //%%%%%%%%%%%%%%%    
        //}
       // System.out.println("Detalles del procesamiento: " + '\n' );
        for(int i = 0; i<detalles.size(); i++){
         //   System.out.print(detalles.get(i));
        }         
       // System.out.println("");
        if (this.F.contains(actual)){
       //     System.out.println("Detalles del procesamiento: " + '\n');
            for(int i = 0; i<detalles.size(); i++){
         //       System.out.print(detalles.get(i));
            }  
       // System.out.println("");
            return true;        
        }
        return false;
    }    
    
    public String procesarFunción(String cadena){
        for(int i=0; i<delta.size(); i++){
            //System.out.println(Arrays.toString(delta.get(i)));//%
        }
        //System.out.println();
        String actual = this.q0;
        int indexOfTape = 0;
        //String cadenawq = transicion.replaceAll("[?]", ":");
        //String transicióon.split(":");
        String[] cad = new String[cadena.length()];
        cad = cadena.split("");
        
        String ultimaCI = "";
        for (int i=0; i<cad.length; i++){
            this.tape.add(cad[i]);
           // System.out.println(this.tape.get(i));
        }
        ArrayList<String> detalles = new ArrayList<>(this.tape);
        int aux = this.tape.size();//utilizado para las iteraciones de la MT
        
        String state = actual.replace(actual, "(" + actual + ")");                        
        String move = this.delta.get(0)[4].replace(this.delta.get(0)[4], "-" + this.delta.get(0)[4]);                          
        detalles.add(indexOfTape,state);                        
        detalles.add(move);        
        
        //for (int i=0; i<cadena.length(); i++){        
            //if (this.q0.equals(this.delta.get(0)[0])){//Verificar que el q0 sea el mismo q0 en transición
            for(int j=0; j<aux; j++){
                if(this.F.contains(actual)){
                    //System.out.println("Retornando True"); //%
                    //System.out.println("Detalles del procesamiento: " + '\n');
                    for(int i = 0; i<tape.size()/2; i++){
                        System.out.print(this.tape.get(i));
                    }  
                    System.out.println("");
                   return ultimaCI;        //si llega a un estado de aceptación retorno true                      
                }
                //System.out.println('\n'); //%
                //System.out.println("iteración" + j + '\n'); //%
                    //Verifica la función de transición para procesar la cadena
                    //problema que no vuelve a recorre la FT completa!!!!!
                  
                for(int k=0; k<delta.size(); k++){    
                    if((actual.equals(this.delta.get(k)[0]) && (this.tape.get(indexOfTape).equals(this.delta.get(k)[1])))){
                        aux++;//Cada vez que se recorra un paso se añade 1 al auxiliar para facilitar la iteración y evaluar la cadena
                        //Verifica y sobreescribe caracteres en la cinta                                           
                        
                       /* System.out.println("Entra en la F-Transición " + j);//%
                        System.out.println("Estado actual " + actual);//%
                        System.out.println("caracter de entrada " + this.tape.get(indexOfTape));//%
                        System.out.println("caracter de cinta " + this.delta.get(k)[3]);//%*/
                        //System.out.println("delta fecha " + this.delta.get(j)[4]);//%
                        if("<".equals(this.delta.get(k)[4])){
                            //System.out.println("Entra en el delta <" + j);//%
                            if(indexOfTape==0){
                                //System.out.println("Entra en el if 1 <" + j);//%
                                this.tape.set(indexOfTape ,this.delta.get(k)[3]);//escribir caracter en la cinta
                                this.tape.add(0 ,"!");//Añadir ! en la cinta al lado izquierdo
                                //indexOfTape    
                            }
                            else{
                                //System.out.println("Entra en el if 2 <" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                indexOfTape--;
                            }

                        }
                        else if (">".equals(this.delta.get(k)[4])){
                            //System.out.println("Entra en el delta >: " + j);//%
                            if(indexOfTape==this.tape.size()-1){
                               // System.out.println("Entra en el if 1 >" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                this.tape.add("!");//Añadir ! a la cinta a la derecha
                                indexOfTape++;    
                            }
                            else{
                               // System.out.println("Entra en el if 2 >" + j);//%
                                this.tape.set(indexOfTape,this.delta.get(k)[3]);//escribir caracter en la cinta                            
                                indexOfTape++;    
                            }
                        }
                        else{
                          //  System.out.println("Entra en el delta -?" + j);//%
                            this.tape.set(indexOfTape,this.delta.get(k)[3]);
                        }              
                       // System.out.print("Tape: ");//%    
                        for(int i=0; i<tape.size(); i++){
                           // System.out.print(tape.get(i));//%
                        }
                      //  System.out.println("");//% 
                      //  System.out.println("IndexofTape: "+ indexOfTape);//% 
                        actual = this.delta.get(k)[2];//cambiar estado actual al siguiente en la Ftransición
                      //  System.out.println("Actual: " + actual);//%
                      //  System.out.println("Move: " + this.delta.get(k)[4]);//%
                        //Guarda el detalle del procesamiento
                        
                        state = actual.replace(actual, "(" + actual + ")");                        
                        move = this.delta.get(k)[4].replace(this.delta.get(k)[4], "-" + this.delta.get(k)[4]);
                        ArrayList<String> auxList = new ArrayList<>(this.tape);                        
                        auxList.add(indexOfTape, state);
                        auxList.add(move);
                        for(int i=0; i<auxList.size(); i++){
                            detalles.add(auxList.get(i));
                        }
                            
                        
                        break;
                    }                    
                }    
            } 
        //%%%%%%%%%%%%%%%    
       // System.out.print("Tapen: ");//%    
        for(int i = 0; i<tape.size()/2; i++){
            System.out.print(this.tape.get(i));
        }  
        System.out.println("");//% 
        //%%%%%%%%%%%%%%%    
        //}
       // System.out.println("Detalles del procesamiento: " + '\n' );
        for(int i = 0; i<detalles.size(); i++){
         //   System.out.print(detalles.get(i));
        }         
       // System.out.println("");
        if (this.F.contains(actual)){
       //     System.out.println("Detalles del procesamiento: " + '\n');
            for(int i = 0; i<detalles.size(); i++){
         //       System.out.print(detalles.get(i));
            }  
       // System.out.println("");
            return ultimaCI;        
        }
        return ultimaCI;
    }
    
    public void procesarListaCadenas(ArrayList<String> listaCadenas /*,nombreArchivo*/, boolean imprimirPantalla){
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }   
    
}
