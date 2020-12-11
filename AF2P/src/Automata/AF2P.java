package Automata;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class AF2P {

    private ArrayList<String> states;
    private String initial;
    private ArrayList<String> accepting;
    private ArrayList<String> tapeAlphabet;
    private ArrayList<String> stackAlphabet;
    private HashMap<Pair<String, String>, List<String>> delta;

    public AF2P() {

    }

    public AF2P(ArrayList<String> states, String initial, ArrayList<String> accepting, ArrayList<String> tapeAlphabet, ArrayList<String> stackAlphabet, HashMap<Pair<String, String>, List<String>> delta) {
        this.states = states;
        this.initial = initial;
        this.accepting = accepting;
        this.tapeAlphabet = tapeAlphabet;
        this.stackAlphabet = stackAlphabet;
        this.delta = delta;
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

    public ArrayList<String> getTapeAlphabet() {
        return tapeAlphabet;
    }

    public void setTapeAlphabet(ArrayList<String> tapeAlphabet) {
        this.tapeAlphabet = tapeAlphabet;
    }

    public ArrayList<String> getStackAlphabet() {
        return stackAlphabet;
    }

    public void setStackAlphabet(ArrayList<String> stackAlphabet) {
        this.stackAlphabet = stackAlphabet;
    }

    public HashMap<Pair<String, String>, List<String>> getDelta() {
        return delta;
    }

    public void setDelta(HashMap<Pair<String, String>, List<String>> delta) {
        this.delta = delta;
    }

    public static AF2P Constructor(String filename) {

        AF2P AF2P = new AF2P(null, null, null, null, null, null);

        try {

            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String newline;
            newline = br.readLine();


            if ("#states".equals(newline)) {
                ArrayList<String> states = new ArrayList<>();
                while (!"#initial".equals(newline = br.readLine())) {
                    if (!AdditionalMethods.havesemicolon(newline)) {
                        states.add(newline);
                    }
                }
                String initial = null;
                while (!"#accepting".equals(newline = br.readLine())) {
                    if (AdditionalMethods.instates(states, newline)) {
                        initial = newline;
                    }
                }
                ArrayList<String> accepting = new ArrayList<>();
                while (!"#tapeAlphabet".equals(newline = br.readLine())) {
                    if (AdditionalMethods.instates(states, newline)) {
                        accepting.add(newline);
                    }
                }
                ArrayList<String> tapeAlphabet = new ArrayList<>();
                while (!"#stackAlphabet".equals(newline = br.readLine())) {
                    if (newline.length() == 3) {
                        AdditionalMethods.alphabetrangeadd(tapeAlphabet, newline);
                    } else {
                        tapeAlphabet.add(newline);
                    }
                }
                ArrayList<String> stackAlphabet = new ArrayList<>();
                while (!"#transitions".equals(newline = br.readLine())) {
                    if (newline.length() == 3) {
                        AdditionalMethods.alphabetrangeadd(stackAlphabet, newline);
                    } else {
                        stackAlphabet.add(newline);
                    }
                }
                HashMap<Pair<String, String>, List<String>> delta = new HashMap<>();
                while ((newline = br.readLine()) != null) {
                    AdditionalMethods.filldeltaAF2P(delta, newline);
                }

                AF2P.setStates(states);
                AF2P.setInitial(initial);
                AF2P.setAccepting(accepting);
                AF2P.setTapeAlphabet(tapeAlphabet);
                AF2P.setStackAlphabet(stackAlphabet);
                AF2P.setDelta(delta);

            }

        } catch (IOException e) {
        }

        return AF2P;
    }

    public void automataToString(){
        System.out.println("Σ = " + this.getTapeAlphabet().toString());
        System.out.println("Γ = " + this.getStackAlphabet().toString());
        System.out.println("Q = " + this.getStates().toString());
        System.out.println("q0 = " + this.getInitial());
        System.out.println("F = " + this.getAccepting().toString());
        System.out.println("δ = " + this.getDelta().toString() + "\n");
    }

    public void stringProcessing(String str){
        int i = 0;
        boolean aborted = false;
        String initial = this.initial;
        String actual = initial;
        String holded = "";
        HashMap<Pair<String, String>, List<String>> delta = this.delta;
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        stack1.push("$");
        stack2.push("$");
        String[] stringSplit = str.split(" ");

        System.out.println(str + "\n");

        for (String StringSplit1 : stringSplit) {

            Pair<String, String> search = new Pair<>(actual, stringSplit[i]);

            String destination = delta.get(search).get(0);
            String topOf = delta.get(search).get(1);
            String topOf2 = delta.get(search).get(2);
            String replacement = delta.get(search).get(1);
            String replacement2 = delta.get(search).get(2);
            String peek1 = stack1.peek();
            String peek2 = stack2.peek();
            String chain = "";
            String holding = "";
            String holding2 = "";
            

            for (int j=i; j<stringSplit.length; j++){
                chain += stringSplit[j];
            }
            for (int u=0; u<stack1.size(); u++){
                holding += stack1.elementAt(u);
            }
            for (int u=0; u<stack2.size(); u++){
                holding2 += stack2.elementAt(u);
            }
            

            
            boolean case1 = (topOf.equals("$")&& topOf2.equals("$") && !replacement.equals("$") && !replacement2.equals("$"));
            boolean case2 = (topOf.equals(peek1)&& topOf2.equals("$") && replacement.equals("$") && replacement2.equals("$"));
            boolean case3 = (topOf.equals("$")&& topOf2.equals(peek2) && replacement.equals("$") && replacement2.equals("$"));
            boolean case4 = (topOf.equals("$") && replacement.equals("$"));


            if (case1){
                stack1.push(replacement);
                stack2.push(replacement);
            }else if (case2){
                stack1.pop();
            }else if (case3){
                stack2.pop();
            }else if (case4){
                //stack is not affected
            }else {
                aborted = true;
                break;
            }

            actual = destination;

            i++;

        }

        if (this.accepting.contains(actual) && stack1.peek().equals("$")&& stack2.peek().equals("$") && !aborted) System.out.println(" Yes \n");
        else System.out.println(" No \n");
    }

    public void detailedStringProcessing(String str) {
        int i = 0;
        boolean aborted = false;
        String initial = this.initial;
        String actual = initial;
        String holded = "";
        HashMap<Pair<String, String>, List<String>> delta = this.delta;
        Stack<String> stack = new Stack<>();
        stack.push("$");
        String[] stringSplit = str.split(" ");

        System.out.println(str + "\n");

        for (String StringSplit1 : stringSplit) {

            Pair<String, String> search = new Pair<>(actual, stringSplit[i]);

            String destination = delta.get(search).get(0);
            String topOf = delta.get(search).get(1);
            String replacement = delta.get(search).get(2);
            String peek = stack.peek();
            String chain = "";
            String holding = "";

            for (int j=i; j<stringSplit.length; j++){
                chain += stringSplit[j];
            }
            for (int u=0; u<stack.size(); u++){
                holding += stack.elementAt(u);
            }

            if (stack.size()>1){
                System.out.print("("+actual+","+chain+","+holding.substring(1)+")");
            }else{
                System.out.print("("+actual+","+chain+","+holding+")");
            }

            boolean case1 = (!replacement.equals("$") && topOf.equals(peek) && !peek.equals("$"));
            boolean case2 = (topOf.equals("$") && !replacement.equals("$"));
            boolean case3 = (topOf.equals(peek) && replacement.equals("$"));
            boolean case4 = (topOf.equals("$") && replacement.equals("$"));

            if (case1){
                stack.pop();
                stack.push(replacement);
                System.out.print("->");
            }else if (case2){
                stack.push(replacement);
                System.out.print("->");
            }else if (case3){
                stack.pop();
                System.out.print("->");
            }else if (case4){
                System.out.print("->");
                //stack is not affected
            }else {
                aborted = true;
                break;
            }

            actual = destination;

            i++;

        }

        if (!aborted){

            for (int w = 0; w<stack.size(); w++){
                holded += stack.elementAt(w);
            }

            if (holded.length()>1){
                System.out.print("("+actual+","+"$"+","+holded.substring(1)+") >>");
            }else{
                System.out.print("("+actual+","+"$"+","+holded+") >>");
            }

        }else{
            System.out.print(" >>");
        }


        if (this.accepting.contains(actual) && stack.peek().equals("$") && !aborted) System.out.println(" Accepted \n");
        else System.out.println(" Rejected \n");

    }

    public void fileDetailedStringProcessing(String str, FileWriter fw){
        try{
            int i = 0;
            boolean aborted = false;
            String initial = this.initial;
            String actual = initial;
            String holded = "";
            HashMap<Pair<String, String>, List<String>> delta = this.delta;
            Stack<String> stack1 = new Stack<>();
            Stack<String> stack2 = new Stack<>();
            stack1.push("$");
            stack2.push("$");
            String[] stringSplit = str.split(" ");

            fw.write(str + "\t");
            for(String StringSplit1 : stringSplit){
                Pair<String, String> search = new Pair<>(actual, stringSplit[i]);

                String destination = delta.get(search).get(0);
                String topOf = delta.get(search).get(1);
                String replacement = delta.get(search).get(2);
                String peek = stack1.peek();
                String chain = "";
                String holding = "";

                for (int j=i; j<stringSplit.length; j++){
                    chain += stringSplit[j];
                }
                for (int u=0; u<stack1.size(); u++){
                    holding += stack1.elementAt(u);
                }

                if (stack1.size()>1){
                    fw.write("("+actual+","+chain+","+holding.substring(1)+")");
                }else{
                    fw.write("("+actual+","+chain+","+holding+")");
                }

                boolean case1 = (!replacement.equals("$") && topOf.equals(peek) && !peek.equals("$"));
                boolean case2 = (topOf.equals("$") && !replacement.equals("$"));
                boolean case3 = (topOf.equals(peek) && replacement.equals("$"));
                boolean case4 = (topOf.equals("$") && replacement.equals("$"));

                if (case1){
                    stack1.pop();
                    stack1.push(replacement);
                    fw.write("->");
                }else if (case2){
                    stack1.push(replacement);
                    fw.write("->");
                }else if (case3){
                    stack1.pop();
                    fw.write("->");
                }else if (case4){
                    fw.write("->");
                    //stack is not affected
                }else {
                    aborted = true;
                    break;
                }

                actual = destination;

                i++;
            }

            if (!aborted){

                for (int w = 0; w<stack1.size(); w++){
                    holded += stack1.elementAt(w);
                }

                if (holded.length()>1){
                    fw.write("("+actual+","+"$"+","+holded.substring(1)+") >>");
                }else{
                    fw.write("("+actual+","+"$"+","+holded+") >>");
                }

            }else{
                fw.write(" >>");
            }

            if (this.accepting.contains(actual) && stack1.peek().equals("$") && !aborted) fw.write(" Yes \n");
            else fw.write(" No \n");
        }
        catch(IOException e){
        }
    }

    public void StringListProcessing(ArrayList<String> strings, String file, boolean print){

        try{
            FileWriter fw = new FileWriter(file);

            for(int i=0; i<strings.size(); i++){
                this.fileDetailedStringProcessing(strings.get(i), fw);
            }

            fw.close();
        }
        catch(IOException e){
        }
        if(print){
            for(int i=0; i<strings.size(); i++){
                this.detailedStringProcessing(strings.get(i));
            }
        }
    }
}
