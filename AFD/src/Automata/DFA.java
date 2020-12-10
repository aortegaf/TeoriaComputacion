package Automata;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

public class DFA {

    private ArrayList<String> alphabet;
    private ArrayList<String> states;
    private String initial;
    private ArrayList<String> accepting;
    private HashMap<Pair<String, String>, String> delta;
    
    public DFA() {
    }
    
    public DFA(ArrayList<String> alphabet, ArrayList<String> states, String initial, ArrayList<String> accepting, HashMap<Pair<String, String>, String> delta) {
        this.alphabet = alphabet;
        this.states = states;
        this.initial = initial;
        this.accepting = accepting;
        this.delta = delta;
    }

    public ArrayList<String> getAlfabet() {
        return alphabet;
    }

    public void setAlfabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public ArrayList<String> getAccepting() {
        return accepting;
    }

    public void setAccepting(ArrayList<String> accepting) {
        this.accepting = accepting;
    }

    public HashMap<Pair<String, String>, String> getDelta() {
        return delta;
    }

    public void setDelta(HashMap<Pair<String, String>, String> delta) {
        this.delta = delta;
    }

    public static DFA Constructor(String filename){
        DFA DFA = new DFA(null, null, null, null, null);
        
      try {
        File f = new File(filename);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String newline;
        newline=br.readLine();
        if("#alphabet".equals(newline)){
            ArrayList<String> alphabet = new ArrayList<>();
            while(!"#states".equals(newline=br.readLine())){
                if(newline.length()==3){
                    AdditionalMethods.alphabetrangeadd(alphabet, newline);
                }else{
                    alphabet.add(newline);
                }
            }
            ArrayList<String> states = new ArrayList<>();        
            while(!"#initial".equals(newline=br.readLine())){
                if(!AdditionalMethods.havesemicolon(newline)){
                    states.add(newline);
                }
            }
            String initial = null;
            while(!"#accepting".equals(newline=br.readLine())){
                if(AdditionalMethods.instates(states, newline)){
                    initial = newline;
                }
            }
            ArrayList<String> accepting = new ArrayList<>();
            while(!"#transitions".equals(newline=br.readLine())){
                if(AdditionalMethods.instates(states, newline)){
                    accepting.add(newline);
                }
            }
            HashMap<Pair<String, String>, String> delta = new HashMap<>();
            while((newline=br.readLine())!= null){
                AdditionalMethods.filldeltaDFA(delta, states, alphabet, newline);
            }   
        DFA.setAlfabet(alphabet);
        DFA.setStates(states);
        DFA.setInitial(initial);
        DFA.setAccepting(accepting);
        DFA.setDelta(delta);
        }
      }
      catch(IOException e){
      }
      return DFA;
    }
    
    public void StringProcessing(String str){
        
        String initial = this.initial;
        String actual = initial;
        HashMap<Pair<String, String>, String> delta = this.delta;
        String[] StringSplit = str.split(" ");
        
        
        for (String StringSplit1 : StringSplit) {
            if(actual == null){
                break;
            }
            actual = delta.get(AdditionalMethods.pair(actual, StringSplit1));
        }
    
        if(this.accepting.contains(actual)){
            System.out.print("Accepted \n");
        }else{
            System.out.print("Not Accepted \n");
        }
    } 
    
    public void DetailedStringProcessing(String str){
        String initial = this.initial;
        String actual = initial;
        HashMap<Pair<String, String>, String> delta = this.delta;
        String[] StringSplit = str.split(" ");
        
        System.out.print(str + "\n");
        System.out.print("[" + actual + ", " + str + "]->");
        for(String StringSplit1 : StringSplit){
            if(actual == null){
                System.out.print("\n");
                break;
            }
            actual = delta.get(AdditionalMethods.pair(actual, StringSplit1));
            if(StringSplit.length == 1){
                System.out.print("[" + actual + "] \n");
            }else{
                StringSplit = AdditionalMethods.RemoveFirst(StringSplit);
                System.out.print("[" + actual + "," + AdditionalMethods.StringArraytoString(StringSplit) +  "]->");
            }       
        }
        if(this.accepting.contains(actual)){
            System.out.print("Accepted \n\n");
        }else{
          System.out.print("Not Accepted \n\n");
        }  
    }
 
    public void FileDetailedStringProcessing(String str, FileWriter fw){ 
        try{
            String initial = this.initial;
            String actual = initial;
            HashMap<Pair<String, String>, String> delta = this.delta;
            String[] StringSplit = str.split(" ");
            
            fw.write(str + "\n");
            fw.write("[" + actual + ", " + str + "]->");
            for(String StringSplit1 : StringSplit){
                if(actual == null){
                    fw.write("\n");
                    break;
                }
                actual = delta.get(AdditionalMethods.pair(actual, StringSplit1));
                if(StringSplit.length == 1){
                    fw.write("[" + actual + "] \n");
                }else{
                    StringSplit = AdditionalMethods.RemoveFirst(StringSplit);
                    fw.write("[" + actual + "," + AdditionalMethods.StringArraytoString(StringSplit) +  "]->");
                }       
            }
            if(this.accepting.contains(actual)){
                fw.write("Accepted \n\n");
            }else{
              fw.write("Not Accepted \n\n");
            } 
        }
        catch(IOException e){
      }        
    }
    
    public void StringListProcessing(ArrayList<String> strings, String file, boolean print){
        
        try{
            FileWriter fw = new FileWriter(file);
        
            for(int i=0; i<strings.size(); i++){
               this.FileDetailedStringProcessing(strings.get(i), fw);
            }  
            
           fw.close();
        }
        catch(IOException e){
        } 
        if(print){
            for(int i=0; i<strings.size(); i++){
               this.DetailedStringProcessing(strings.get(i));
            }
        } 
    }
    
    public void String(){
        System.out.println("Σ = " + this.getAlfabet().toString());
        System.out.println("Q = " + this.getStates().toString());
        System.out.println("q0 = " + this.getInitial());
        System.out.println("F = " + this.getAccepting().toString());
        System.out.println("δ = " + this.getDelta().toString() + "\n");
    }
    
    
    //OTROS MÉTODOS 
    public DFA Comp(){ 
        DFA DFAComp = new DFA();
        DFAComp.alphabet = this.alphabet;
        DFAComp.states = this.states;
        DFAComp.initial = this.initial;
        
        ArrayList<String> acceptingcomp = new ArrayList<>();
        for(int i = 0; i<this.states.size();i++){
            if(!this.accepting.contains(DFAComp.states.get(i))){
                acceptingcomp.add(DFAComp.states.get(i));
            }
        }
        DFAComp.accepting = acceptingcomp;
        DFAComp.delta = this.delta;
        
        return DFAComp;
    }
    
    public void CP_and(DFA dfa1, DFA dfa2){
        for(int at1=0; at1<dfa1.states.size();at1++){
            for(int at2=0; at2<dfa2.states.size();at2++){
                for(int alpha=0; alpha<dfa1.alphabet.size();alpha++){
                    if(dfa1.accepting.contains(dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha)))) && dfa2.accepting.contains(dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))))){
                        System.out.println("δ((" + dfa1.states.get(at1) + "," + dfa2.states.get(at2) + ")," + dfa1.alphabet.get(alpha) + ") = " +
                                "(δ1(" + dfa1.states.get(at1) + "," + dfa1.alphabet.get(alpha) + "),δ2(" + dfa2.states.get(at2) + "," + dfa1.alphabet.get(alpha) + ")) = " +
                                        "(" + dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha))) + "," + dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))) + ") -> Accepted");
                    }else{
                        System.out.println("δ((" + dfa1.states.get(at1) + "," + dfa2.states.get(at2) + ")," + dfa1.alphabet.get(alpha) + ") = " +
                                "(δ1(" + dfa1.states.get(at1) + "," + dfa1.alphabet.get(alpha) + "),δ2(" + dfa2.states.get(at2) + "," + dfa1.alphabet.get(alpha) + ")) = " +
                                        "(" + dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha))) + "," + dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))) + ")"); 
                    }
                }
            } 
        }
    }
    
    public void CP_or(DFA dfa1, DFA dfa2){
        for(int at1=0; at1<dfa1.states.size();at1++){
            for(int at2=0; at2<dfa2.states.size();at2++){
                for(int alpha=0; alpha<dfa1.alphabet.size();alpha++){
                    if(dfa1.accepting.contains(dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha)))) || dfa2.accepting.contains(dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))))){
                        System.out.println("δ((" + dfa1.states.get(at1) + "," + dfa2.states.get(at2) + ")," + dfa1.alphabet.get(alpha) + ") = " +
                                "(δ1(" + dfa1.states.get(at1) + "," + dfa1.alphabet.get(alpha) + "),δ2(" + dfa2.states.get(at2) + "," + dfa1.alphabet.get(alpha) + ")) = " +
                                        "(" + dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha))) + "," + dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))) + ") -> Accepted");
                    }else{
                        System.out.println("δ((" + dfa1.states.get(at1) + "," + dfa2.states.get(at2) + ")," + dfa1.alphabet.get(alpha) + ") = " +
                                "(δ1(" + dfa1.states.get(at1) + "," + dfa1.alphabet.get(alpha) + "),δ2(" + dfa2.states.get(at2) + "," + dfa1.alphabet.get(alpha) + ")) = " +
                                        "(" + dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha))) + "," + dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))) + ")"); 
                    }
                }
            } 
        }
    }
    
    public void CP_dif(DFA dfa1, DFA dfa2){
        for(int at1=0; at1<dfa1.states.size();at1++){
            for(int at2=0; at2<dfa2.states.size();at2++){
                for(int alpha=0; alpha<dfa1.alphabet.size();alpha++){
                    if(dfa1.accepting.contains(dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha)))) && !dfa2.accepting.contains(dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))))){
                        System.out.println("δ((" + dfa1.states.get(at1) + "," + dfa2.states.get(at2) + ")," + dfa1.alphabet.get(alpha) + ") = " +
                                "(δ1(" + dfa1.states.get(at1) + "," + dfa1.alphabet.get(alpha) + "),δ2(" + dfa2.states.get(at2) + "," + dfa1.alphabet.get(alpha) + ")) = " +
                                        "(" + dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha))) + "," + dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))) + ") -> Accepted");
                    }else{
                        System.out.println("δ((" + dfa1.states.get(at1) + "," + dfa2.states.get(at2) + ")," + dfa1.alphabet.get(alpha) + ") = " +
                                "(δ1(" + dfa1.states.get(at1) + "," + dfa1.alphabet.get(alpha) + "),δ2(" + dfa2.states.get(at2) + "," + dfa1.alphabet.get(alpha) + ")) = " +
                                        "(" + dfa1.delta.get(AdditionalMethods.pair(dfa1.states.get(at1), dfa1.alphabet.get(alpha))) + "," + dfa2.delta.get(AdditionalMethods.pair(dfa2.states.get(at2), dfa1.alphabet.get(alpha))) + ")"); 
                    }
                }
            } 
        }
    }
    
    
 }

