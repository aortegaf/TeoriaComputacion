package Main;

import Automata.DFA;
import java.util.ArrayList;


public class main {
    
    public static void main (String[] args){  
        
        ArrayList<String> trialslist = new ArrayList<>();   
        trialslist.add("c a b b");
        trialslist.add("a a b");
        trialslist.add("b b a c");
        
        //Creation
        DFA DFA = new DFA();
        DFA = DFA.Constructor("!#dfa.txt");
        
        //Methods
        //DFA.StringProcessing("a b b c b"); //procesarCadena
        //DFA.DetailedStringProcessing("a b b c b"); //procesarCadenaConDetalles
        //DFA.StringListProcessing(trialslist, "DFAOutput.txt", true); //procesarListaCadenas
        //DFA.String(); //toString
    }
       
       
       
       
        
    
}
