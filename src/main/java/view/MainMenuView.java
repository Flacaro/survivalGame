package view;

import java.io.IOException;

public class MainMenuView {
    public int showMainMenu() throws IOException {
        CommonViewUtils.displayMessage("\nCosa vuoi fare?");
        CommonViewUtils.displayMessage("1. Esplora l'area in cui ti trovi");
        CommonViewUtils.displayMessage("2. Visualizza l'inventario");
        CommonViewUtils.displayMessage("3. Muoviti");
        CommonViewUtils.displayMessage("4. Crafting risorse");
        CommonViewUtils.displayMessage("5. Esci");
        int choice = CommonViewUtils.readIntChoice("Inserisci un numero (1-5):");
        while (choice <= 0 || choice > 5) {
            System.out.println("Errore: Inserisci un numero valido.");
            choice = CommonViewUtils.readIntChoice("Inserisci un numero (1-5):");
        }
        return choice;
    }
}
