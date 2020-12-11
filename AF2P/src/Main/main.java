package Main;

import Automata.AF2P;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        ArrayList<String> trialslist = new ArrayList<>();
        trialslist.add("a a b b c c");
        trialslist.add("a b c");
        trialslist.add("a b b");

        AF2P AF2P = new AF2P();

        AF2P = AF2P.Constructor("!#msm.txt");

        AF2P.automataToString();

        AF2P.stringProcessing("a a b b c c");

        //AF2P.detailedStringProcessing("a a b b c c");

        // AF2P.StringListProcessing(trialslist, "FP2POutput.txt", true);

    }
}
