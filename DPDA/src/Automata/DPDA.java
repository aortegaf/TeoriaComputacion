package Automata;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class DPDA {

    private ArrayList<String> states;
    private String initial;
    private ArrayList<String> accepting;
    private ArrayList<String> tapeAlphabet;
    private ArrayList<String> stackAlphabet;
    private HashMap<Pair<String, String>, List<String>> delta;

    public DPDA() {

    }

    public DPDA(ArrayList<String> states, String initial, ArrayList<String> accepting, ArrayList<String> tapeAlphabet, ArrayList<String> stackAlphabet, HashMap<Pair<String, String>, List<String>> delta) {
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

    public static DPDA Constructor(String filename) {

        DPDA DPDA = new DPDA(null, null, null, null, null, null);

        try {

            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String newline;
            newline = br.readLine();

            //System.out.println(newline);

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
                    AdditionalMethods.filldeltaDPDA(delta, newline);
                }

                DPDA.setStates(states);
                DPDA.setInitial(initial);
                DPDA.setAccepting(accepting);
                DPDA.setTapeAlphabet(tapeAlphabet);
                DPDA.setStackAlphabet(stackAlphabet);
                DPDA.setDelta(delta);

            }

        } catch (IOException e) {
        }

        return DPDA;
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

            boolean case1 = (!replacement.equals("$") && topOf.equals(peek) && !peek.equals("$"));
            boolean case2 = (topOf.equals("$") && !replacement.equals("$"));
            boolean case3 = (topOf.equals(peek) && replacement.equals("$"));
            boolean case4 = (topOf.equals("$") && replacement.equals("$"));

            if (case1){
                stack.pop();
                stack.push(replacement);
            }else if (case2){
                stack.push(replacement);
            }else if (case3){
                stack.pop();
            }else if (case4){
                //stack is not affected
            }else {
                aborted = true;
                break;
            }

            actual = destination;

            i++;

        }

        if (this.accepting.contains(actual) && stack.peek().equals("$") && !aborted) System.out.println(" Yes \n");
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
            Stack<String> stack = new Stack<>();
            stack.push("$");
            String[] stringSplit = str.split(" ");

            fw.write(str + "\t");
            for(String StringSplit1 : stringSplit){
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
                    fw.write("("+actual+","+chain+","+holding.substring(1)+")");
                }else{
                    fw.write("("+actual+","+chain+","+holding+")");
                }

                boolean case1 = (!replacement.equals("$") && topOf.equals(peek) && !peek.equals("$"));
                boolean case2 = (topOf.equals("$") && !replacement.equals("$"));
                boolean case3 = (topOf.equals(peek) && replacement.equals("$"));
                boolean case4 = (topOf.equals("$") && replacement.equals("$"));

                if (case1){
                    stack.pop();
                    stack.push(replacement);
                    fw.write("->");
                }else if (case2){
                    stack.push(replacement);
                    fw.write("->");
                }else if (case3){
                    stack.pop();
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

                for (int w = 0; w<stack.size(); w++){
                    holded += stack.elementAt(w);
                }

                if (holded.length()>1){
                    fw.write("("+actual+","+"$"+","+holded.substring(1)+") >>");
                }else{
                    fw.write("("+actual+","+"$"+","+holded+") >>");
                }

            }else{
                fw.write(" >>");
            }

            if (this.accepting.contains(actual) && stack.peek().equals("$") && !aborted) fw.write(" Yes \n");
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
