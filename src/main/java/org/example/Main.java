package org.example;

import controller.StartController;
import model.*;


public class Main {


    public static void main(String[] args) {

        StartController sc = new StartController();
        Mode m = new Mode(1L);
        sc.start(m);

    }


}