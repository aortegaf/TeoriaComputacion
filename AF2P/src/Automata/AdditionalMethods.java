package Automata;

import java.util.*;
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

    public static void filldeltaAF2P(HashMap<Pair<String, String>, List<String>> delta, String line){
        String whole[] = line.split(">");
        String begin[] = whole[0].split(":", 3);
        String end[] = whole[1].split(":", 2);

        Pair<String, String> bga = pair(begin[0], begin[1]);
        List<String> et = new ArrayList<>();
        et.add(0, end[0]);
        et.add(1,begin[2]);
        et.add(2, end[1]);


        delta.put(bga, et);

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