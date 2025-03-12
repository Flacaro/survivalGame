package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import controller.DBController;
import controller.ResourceController;
import controller.StartController;
import model.domain.*;
import services.GameService;
import services.InventoryService;
import services.MapServices;
import services.PlayerService;

public class View {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));



    public View() {
    }

    public void playGame() throws IOException {
        StartController sc = new StartController();
        GameDomain g = sc.start();

        System.out.println("Ti svegli sulla spiaggia, confuso e dolorante." + "\n" + "L’aereo è precipitato. Intorno a te, solo mare, sabbia e frammenti del relitto." + "\n" + "Nessun segno di altri sopravvissuti.\n" +
                "\n" +
                "Hai bisogno di cibo, acqua e riparo. La giungla davanti a te è fitta e sconosciuta, ma non hai scelta: devi esplorare." + "\n" + " Il tuo zaino sarà la tua salvezza. Ogni oggetto raccolto potrebbe fare la differenza.\n" +
                "\n" +
                "Non sei solo su quest’isola. Qualcosa si muove tra gli alberi…" + "\n" +
                "Esplora, raccogli e sopravvivi. La tua avventura inizia ora.");

        boolean continueToPlay = true;

        while (continueToPlay) {
            int choice = showMainMenu();

            switch (choice) {
                case 1:
                    exploreArea(g);
                    break;
                case 2:
                    showInventory(g);
                    break;
                case 3:
                    move(g);
                    break;
                case 4:
//                    crafting(g);
                    if (crafting(g)){
                        System.out.println("Demo completata!");
                        continueToPlay = false;
                    }
                    break;
                case 5:
                    continueToPlay = false;
                    System.out.println("Grazie per aver giocato!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

    }

    private boolean crafting(GameDomain g) {
        DBController dbController = new DBController();
        ResourceController resourceController = new ResourceController();
        PlayerDomain playerDomain = dbController.getGame().getPlayerDomain();
        InventoryDomain inventory = playerDomain.getInventory();
        List<CraftedResourceDomain> craft = dbController.getCraftedResources();
        System.out.println("Combinazioni possibili per il crafting degli oggetti");
        System.out.println("-----------------------------------------------------");
        for (CraftedResourceDomain r : craft) {
            System.out.println("Per creare " + r.getName() + " seleziona " + r.getDescription());
            System.out.println("-----------------------------------------------------");
        }
        if (inventory.getResources().isEmpty()) {
            System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
            return false;
        } else {
            try {
                System.out.println("Inserisci l'indice delle risorse da combinare separate tramite virgola (es. 0,1)");
                int counter = 1;
                HashMap<Integer, ResourceDomain> corrisp = new HashMap<>();
                for (ResourceDomain r : inventory.getResources()) {
                    System.out.println("Inserire l'indice " + counter +" per selezionare la risorsa: "+ r.getName());
                    corrisp.put(counter, r);
                    counter = counter + 1;
                }
                String input = bf.readLine();
                String[] selections = input.split(",");
                for (String s : selections) {
                    if (Integer.parseInt(s) < 1 || Integer.parseInt(s) > counter) {
                        System.out.println("Input non valido.");
                        break;
                    }
                }
                if (resourceController.compatible(selections, corrisp)) {
                    CraftedResourceDomain resD = resourceController.checkSelections(selections, corrisp);
                    resourceController.combine(selections, corrisp, g.getPlayer(), resD);
                    InventoryService inventoryService = new InventoryService();
                    System.out.println("Hai creato: " + resourceController.checkSelections(selections, corrisp).getName());
                    showInventory(g);
                    return true;
                } else {
                    System.out.println("Input non valido.");
                    return false;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Input non valido.");
                return false;
            }
        }
    }

    private void exploreArea(GameDomain gameDomain) throws IOException {
        long currentIdArea = gameDomain.getPlayer().getIdArea();
        GameService gameService = new GameService();
        ResourceDomain resourceDomain = gameService.triggerEvent(currentIdArea, gameDomain);
        MapServices ms = new MapServices();
        PlayerService playerService = new PlayerService();
        DBController dbController = new DBController();
        AreaDomain currentArea = dbController.getAreasById(currentIdArea);

        if (resourceDomain != null) {
            if (currentArea.getIdEvent() == resourceDomain.getId()) {
                foundedResource(resourceDomain.getName());
                int choice = readChoice("Inserisci 1 o 0:");
                switch (choice) {
                    case 1:
                        //pickup
                        if (playerService.pickUp(resourceDomain, gameDomain.getPlayer())) {
                            ms.updateMap(gameDomain.getMap(), resourceDomain);
                            System.out.println("Risorsa aggiunta all'inventario");
                        } else {
                            System.out.println("L'inventario e' pieno, non puoi aggiungere la risorsa");
                        }
                        break;
                    case 2:
                        System.out.println("Risorsa ignorata");
                        break;
                }
            }
        }
        else {
            System.out.println("Non hai trovato nulla questa volta.");
        }
    }

    private void move(GameDomain gameDomain) throws IOException {
        PlayerDomain pd = gameDomain.getPlayer();
        int move = readDirectionChoice();
        DBController dbController = new DBController();
        boolean moved = dbController.move(move, gameDomain);
        AreaDomain areaDomain = dbController.getAreasById(pd.getIdArea());
        if (moved) {
            System.out.println("Ti sei spostato in: " + areaDomain.getName());
        } else {
            System.out.println("Non puoi muoverti in quella direzione.");
        }
    }

    public int readChoice(String message) throws IOException {
        try {
            System.out.println(message);
            String input = bf.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserisci un numero.");
        }
        return 0;
    }

    public void showInventory(GameDomain g) {
        DBController dbController = new DBController();
        PlayerDomain pd = g.getPlayer();
        InventoryDomain inventory = dbController.showInventory(pd);

        if (!inventory.getResources().isEmpty()) {
            System.out.println("Contenuto dell'inventario:");
            for (ResourceDomain r : inventory.getResources()) {
                System.out.println("-" + r.getName() + " " + r.getQuantity());
            }
            if (!inventory.getResourcesSelected().isEmpty()) {
                for (CraftedResourceDomain r : inventory.getResourcesSelected()) {
                    System.out.println("-" + r.getName());
                }
            }
        }
        else {
        if (inventory.getResources().isEmpty()) {
            if(!inventory.getResourcesSelected().isEmpty()){
                System.out.println("Contenuto dell'inventario:");
                for (CraftedResourceDomain r : inventory.getResourcesSelected()) {
                    System.out.println("-" + r.getName());
                }
                return;
            }
            System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
        }
        }
    }

    public int showMainMenu() throws IOException {
        System.out.println("Cosa vuoi fare?");
        System.out.println("1. Esplora l'area in cui ti trovi");
        System.out.println("2. Visualizza l'inventario");
        System.out.println("3. Muoviti");
        System.out.println("4. Crafting risorse");
        System.out.println("5. Esci");
        return readChoice("Inserisci un numero:");
    }

    public int readDirectionChoice() throws IOException {
        System.out.println("Dove vuoi spostarti?\n" +
                "Inserisci 0 per andare a nord\n" +
                "Inserisci 1 per andare a est\n" +
                "Inserisci 2 per andare a sud\n" +
                "Inserisci 3 per andare a ovest\n");

        return readChoice("Inserisci un numero:");
    }

    public int leggiSceltaRisorsa() throws IOException {
        return readChoice("Inserisci 1 o 0:");
    }

    public void foundedResource(String risorsa) {
        System.out.println("Hai trovato " + risorsa + ", la vuoi prendere?\n" +
                "Inserisci 1 per raccoglierla\n" +
                "inserisci 0 per ignorarla");
    }

    public void areaCorrente(String area) {
        System.out.println("Ti sei spostato in: " + area);
    }
}



