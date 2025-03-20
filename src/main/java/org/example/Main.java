package org.example;

import view.View;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws IOException {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        View view = new View();
        view.playGame();
    }
}