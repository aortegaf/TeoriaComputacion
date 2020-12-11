package com.company;

import Automata.DPDA;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> trialslist = new ArrayList<>();
        trialslist.add("a a b b");
        trialslist.add("a b");
        trialslist.add("a b b");

        DPDA DPDA = new DPDA();

        DPDA = DPDA.Constructor("!#dpda.txt");

        DPDA.automataToString();

        //DPDA.stringProcessing("a a b b");

        //DPDA.detailedStringProcessing("a a b b");

        //DPDA.StringListProcessing(trialslist, "DPDAOutput.txt", true);

    }
}
