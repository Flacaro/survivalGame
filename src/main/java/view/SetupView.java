package view;
import java.io.IOException;

public class SetupView {
    public int getDifficultyChoice() throws IOException {
        CommonViewUtils.displayMessage("\nScegli una modalita':");
        CommonViewUtils.displayMessage("1. Facile");
        CommonViewUtils.displayMessage("2. Media");
        CommonViewUtils.displayMessage("3. Difficile");
        int choice=CommonViewUtils.readIntChoice("Inserisci 1, 2 o 3:");
        //solo la modalità facile è stata implementata
        while (choice<=0 || choice>3 || choice!=1){
            System.out.println("Errore: Inserisci un numero valido.");
            choice=CommonViewUtils.readIntChoice("Inserisci 1, 2 o 3:");
        }
        return choice;
    }

    public String getNickname() throws IOException {
        return CommonViewUtils.readString("\nScegli un nickname:");
    }

    public void displayWelcomeMessage() {
        CommonViewUtils.displayMessage("\nTi svegli sulla spiaggia, confuso e dolorante." + "\n" + "L’aereo è precipitato. Intorno a te, solo mare, sabbia e frammenti del relitto." + "\n" + "Nessun segno di altri sopravvissuti.\n" +
                "\n" +
                "Hai bisogno di cibo, acqua e riparo. La giungla davanti a te è fitta e sconosciuta, ma non hai scelta: devi esplorare." + "\n" + " Il tuo zaino sarà la tua salvezza. Ogni oggetto raccolto potrebbe fare la differenza.\n" +
                "\n" +
                "Non sei solo su quest’isola. Qualcosa si muove tra gli alberi…" + "\n" +
                "Esplora, raccogli e sopravvivi. La tua avventura inizia ora.");
        CommonViewUtils.displayMessage("\nBenvenuto nella demo! Esplora le aree, raccogli le risorse e crafta una risorsa per completarla e passare al gioco.\n");
    }
}
