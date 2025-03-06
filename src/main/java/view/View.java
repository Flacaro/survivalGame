package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import controller.DBController;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.domain.MapDomain;
import model.domain.PlayerDomain;
import model.domain.ResourceDomain;
import services.AreaService;
import services.GameService;

public class View {

    private BufferedReader bf;
    private GameService gameService;
    private AreaService areaService;

    public View() {
        this.bf = new BufferedReader(new InputStreamReader(System.in));
        this.gameService = new GameService();
        this.areaService = new AreaService();
    }

    public void avviaGioco(GameDomain gameDomain) throws IOException {
        AreaDomain currentArea = null;

        // Ottieni la mappa dal GameDomain
        MapDomain mapDomain = gameDomain.getMapDomain();
        List<AreaDomain> areas = mapDomain.getAreas();
        PlayerDomain playerDomain= new PlayerDomain();
        // Imposta l'area iniziale del giocatore
        System.out.println("Ti svegli sulla spiaggia, confuso e dolorante. L’aereo è precipitato. Intorno a te, solo mare, sabbia e frammenti del relitto. Nessun segno di altri sopravvissuti.\n" +
                "\n" +
                "Hai bisogno di cibo, acqua e riparo. La giungla davanti a te è fitta e sconosciuta, ma non hai scelta: devi esplorare. Il tuo zaino sarà la tua salvezza. Ogni oggetto raccolto potrebbe fare la differenza.\n" +
                "\n" +
                "Non sei solo su quest’isola. Qualcosa si muove tra gli alberi…"+"\n" +
                "Esplora, raccogli e sopravvivi. La tua avventura inizia ora.");

        boolean continuaAGiocare = true;
        while (continuaAGiocare) {
            int scelta = mostraMenuPrincipale();

            switch (scelta) {
                case 1:
                    esplora(gameDomain, currentArea);
                    break;
                case 2:
                    mostraInventario(gameDomain.getPlayer().getInventory().getResources());
                    break;
                case 3:
                    muovi(gameDomain, currentArea);
                    break;
                case 4:
                    continuaAGiocare = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

        System.out.println("Grazie per aver giocato!");
    }

    private void esplora(GameDomain gameDomain, AreaDomain currentArea) throws IOException {
        ResourceDomain resourceDomain = gameService.triggerEvent(currentArea.getId(), gameDomain);

        if (resourceDomain != null) {
            risorsaTrovata(resourceDomain.getName());
            int choice = leggiScelta("Inserisci 1 o 0:");

            switch (choice) {
                case 1:
//                    if (gameDomain.getPlayer().getInventory().add(resourceDomain)) {
//                        System.out.println("Risorsa aggiunta all'inventario.");
//                    } else {
//                        System.out.println("L'inventario è pieno! Non puoi raccogliere altre risorse.");
//                    }

                    break;
                case 0:
                    System.out.println("Risorsa ignorata.");
                    break;
            }
        } else {
            System.out.println("Non hai trovato nulla questa volta.");
        }
    }

    private void muovi(GameDomain gameDomain, AreaDomain currentArea) throws IOException {
        int move = leggiSceltaDirezione();
        //MoveController moveController = new MoveController();
        DBController dbController=new DBController();
        boolean moved = dbController.move(move, gameDomain);

        if (moved) {
            PlayerDomain player = gameDomain.getPlayer();
            currentArea = areaService.areaDomainMapper(areaService.areaMapper(gameDomain.getMap().getAreas().get((int) player.getIdArea())));
            System.out.println("Ti sei spostato in: " + currentArea.getName());
        } else {
            System.out.println("Non puoi muoverti in quella direzione.");
        }
    }



    public int leggiScelta(String messaggio) throws IOException {
            try {
                System.out.println(messaggio);
                String input = bf.readLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
            return 0;
    }

    public void mostraInventario(List<ResourceDomain> risorse) {
        System.out.println("Inventario:");
        if (risorse == null || risorse.isEmpty()) {
            System.out.println("Vuoto");
        } else {
            for (ResourceDomain r : risorse) {
                System.out.println("- " + r.getName());
            }
        }
    }

    public int mostraMenuPrincipale() throws IOException {
        System.out.println("Cosa vuoi fare?");
        System.out.println("1. Esplora l'area in cui ti trovi");
        System.out.println("2. Visualizza l'inventario");
        System.out.println("3. Muoviti");
        System.out.println("4. Crafting risorse");
        System.out.println("5. Esci");
        return leggiScelta("Inserisci un numero:");
    }


    public int leggiSceltaDirezione() throws IOException {
        System.out.println("Dove vuoi spostarti?\n" +
                "Inserisci 0 per andare a nord\n" +
                "Inserisci 1 per andare a est\n" +
                "Inserisci 2 per andare a sud\n" +
                "Inserisci 3 per andare a ovest\n");

        return leggiScelta("Inserisci un numero:");
    }

    public int leggiSceltaRisorsa() throws IOException {
        return leggiScelta("Inserisci 1 o 0:");
    }

    public void risorsaTrovata(String risorsa) {
        System.out.println("Hai trovato " + risorsa + ", la vuoi prendere?\n" +
                "Inserisci 1 per raccoglierla\n" +
                "inserisci 0 per ignorarla");
    }

    public void areaCorrente(String area) {
        System.out.println("Ti sei spostato in: " + area);
    }
}