package Automata;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

public class AdditionalMethods {
        
    public static void alphabetrangeadd(ArrayList<String> alphabet, String line){
        if(line.charAt(1) == '-'){
            char start = line.charAt(0);
            char end = line.charAt(2);
        
             for(int i=(int)start; i<=(int)end ;i++){
                alphabet.add(String.valueOf((char)i));
             }
        }else{
            alphabet.add(line);
        } 
    }
    
    public static boolean instates(ArrayList<String> states, String state){
        for(int i=0; i<states.size(); i++){
            if(state.equals(states.get(i))){
                return true;
            }
        }
        return false;
    }
    
    public static boolean havesemicolon(String line){
        for(int i=0; i<line.length();i++){
            if(line.charAt(i) == ';'){
                return true;
            }
        }
        return false;
    }
    
    public static Pair<String, String> pair(String a, String b){
           Pair<String, String> keys = new Pair<>(a,b);
           return keys;
    }
    
    public static void filldeltaDFA(HashMap<Pair<String, String>, String> delta, ArrayList<String> states, ArrayList<String> alphabet, String line){
        String departure[] = line.split(":");
        String string[] = departure[1].split(">");
               
        Pair<String, String> pair = pair(departure[0], string[0]);
        delta.put(pair, string[1]);
    }
    
    public static String StringArraytoString(String[] array){
        String toString = "";
        for(int i=0; i<array.length-1; i++){
            toString = toString.concat(array[i] + " ");
        }
        toString = toString.concat(array[array.length-1]);
        
        return toString;
    }
    
    public static String[] RemoveFirst(String[] array){
        if(array.length == 1){
            String[] newarray = new String[array.length];
            newarray[0] = " ";
            return newarray;
        }else{
            String[] newarray = new String[array.length-1];
            for(int i=1;i<array.length;i++){
                newarray[i-1]=array[i];
            } 
            return newarray;
        }
       
        
    }
    
    
}
