package com.company;

import Automata.DPDA;

public class Main {

    public static void main(String[] args) {

        DPDA DPDA = new DPDA();

        DPDA = DPDA.Constructor("!#dpda.txt");

        DPDA.automataToString();

        DPDA.stringProcessing("a a b b");

    }
}
