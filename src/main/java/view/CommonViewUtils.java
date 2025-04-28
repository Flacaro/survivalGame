package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// Classe di utilità per input e output comuni
public class CommonViewUtils {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static int readIntChoice(String message) throws IOException {
        while (true) { // Loop until valid input is received
            System.out.println(message);
            try {
                String input = bf.readLine();
                if (input == null) {
                    throw new IOException("Input stream closed unexpectedly.");
                }
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Errore: Inserisci un numero valido.");
            }
        }
    }

    public static String readString(String message) throws IOException {
        System.out.println(message);
        String input = bf.readLine();
        if (input == null) {
            throw new IOException("Input stream closed unexpectedly.");
        }
        return input.trim();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displaySeparator() {
        System.out.println("-----------------------------------------------------");
    }
}