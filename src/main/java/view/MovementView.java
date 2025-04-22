/*package view;

import controller.DBController;
import model.entity.Area;
import model.entity.Game;
import model.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;

public class MovementView {
    private final BufferedReader bf;*/

   /* public MovementView(BufferedReader bf) {
        this.bf = bf;
    }

    public void move(Game game) throws IOException {
        Player pd = game.getPlayer();
        int move = readDirectionChoice();
        DBController dbController = new DBController();
        boolean moved = dbController.move(move, game);
        Area area = dbController.getAreasById(pd.getIdArea());
        if (moved) {
            System.out.println("Ti sei spostato in: " + area.getName());
            System.out.println(area.getDescription());
        } else {
            System.out.println("Non puoi muoverti in quella direzione.");
            move(game);
        }
    }

    public int readDirectionChoice() throws IOException {
        System.out.println("Dove vuoi spostarti?\n" +
                "Inserisci 0 per andare a nord\n" +
                "Inserisci 1 per andare a est\n" +
                "Inserisci 2 per andare a sud\n" +
                "Inserisci 3 per andare a ovest\n");

        return readChoice("Inserisci un numero:");
    }

    public int readChoice(String message) throws IOException {
        try {
            System.out.println(message);
            String input = bf.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserisci un numero.");
            return 0;
        }
    }
}*/
package view;

import model.entity.Area;

import java.io.IOException;

public class MovementView {
    public int getDirectionChoice() throws IOException {
        CommonViewUtils.displayMessage("\nDove vuoi spostarti?");
        CommonViewUtils.displayMessage("0. Nord");
        CommonViewUtils.displayMessage("1. Est");
        CommonViewUtils.displayMessage("2. Sud");
        CommonViewUtils.displayMessage("3. Ovest");
        // Aggiungere validazione per 0-3
        return CommonViewUtils.readIntChoice("Inserisci un numero (0-3):");
    }

    public void displayMovementResult(boolean success, Area newArea) {
        if (success && newArea != null) {
            CommonViewUtils.displayMessage("Ti sei spostato in: " + newArea.getName());
            CommonViewUtils.displayMessage(newArea.getDescription());
        } else {
            CommonViewUtils.displayMessage("Non puoi muoverti in quella direzione.");
        }
    }
}
