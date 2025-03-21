package view;

import controller.DBController;
import controller.ResourceController;
import controller.StartController;
import model.domain.*;
import services.GameService;
import services.MapServices;
import services.PlayerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class View {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


    public View() {
    }

    public void playGame() throws IOException {
        StartController sc = new StartController();
        GameDomain g = sc.start();

        System.out.println("\nTi svegli sulla spiaggia, confuso e dolorante." + "\n" + "L’aereo è precipitato. Intorno a te, solo mare, sabbia e frammenti del relitto." + "\n" + "Nessun segno di altri sopravvissuti.\n" +
                "\n" +
                "Hai bisogno di cibo, acqua e riparo. La giungla davanti a te è fitta e sconosciuta, ma non hai scelta: devi esplorare." + "\n" + " Il tuo zaino sarà la tua salvezza. Ogni oggetto raccolto potrebbe fare la differenza.\n" +
                "\n" +
                "Non sei solo su quest’isola. Qualcosa si muove tra gli alberi…" + "\n" +
                "Esplora, raccogli e sopravvivi. La tua avventura inizia ora.");

        System.out.println("Benvenuto nella demo! Esplora le aree, raccogli le risorse e crafta una risorsa per completarla e passare al gioco.\n");
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
                    if (crafting(g)) {
                        System.out.println("Demo completata!");
                        continueToPlay = false;
                    }
                    break;
                case 5:
                    continueToPlay = false;
                    System.out.println("Grazie per aver giocato!");
                    DBController dbController = new DBController();
                    dbController.close();
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
                    System.out.println("Inserire l'indice " + counter + " per selezionare la risorsa: " + r.getName());
                    corrisp.put(counter, r);
                    counter = counter + 1;
                }
                String input = bf.readLine();
                String[] selections = input.split(",");
                for (String s : selections) {
                    if (Integer.parseInt(s) < 1 || selections.length > counter || Integer.parseInt(s) > counter) {
                        System.out.println("Input non valido.");
                        return false;
                    }
                }
                CraftedResourceDomain pair = resourceController.compatible(selections, corrisp);
                if (pair != null) {
                    resourceController.combine(selections, corrisp, g.getPlayer(), pair);
                    System.out.println("Hai creato: " + pair.getName());
                    //showInventory(g);
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
                    case 0:
                        System.out.println("Risorsa ignorata");
                        break;
                }
            } else {
                System.out.println("Non hai trovato nulla questa volta.");
            }
        } else {
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
            System.out.println(areaDomain.getDescription());
        } else {
            System.out.println("Non puoi muoverti in quella direzione.");
            move(gameDomain);
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
        List<ResourceQuantityInvDomain> resourcesQuantity = inventory.getResources_quantity();
        if (!inventory.getResources().isEmpty()) {
            System.out.println("Contenuto dell'inventario:");
            for (ResourceDomain r : inventory.getResources()) {
                for (ResourceQuantityInvDomain rqid : resourcesQuantity) {
                    if (Objects.equals(r.getName(), rqid.getResource().getName())) {
                        System.out.println("-" + r.getName() + "  quantità:" + rqid.getQuantity());
                    }
                }
            }
            if (!inventory.getCraftedResourceDomainList().isEmpty()) {
                for (CraftedResourceDomain r : inventory.getCraftedResourceDomainList()) {
                    System.out.println("-" + r.getName() + "  quantità:" + r.getQuantity());
                }
            }
        } else {
            if (inventory.getResources().isEmpty()) {
                if (!inventory.getCraftedResourceDomainList().isEmpty()) {
                    System.out.println("Contenuto dell'inventario:");
                    for (CraftedResourceDomain r : inventory.getCraftedResourceDomainList()) {
                        System.out.println("-" + r.getName() + " quantità: " + r.getQuantity());
                    }
                    return;
                }
                System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
            }
        }
    }

    public int showMainMenu() throws IOException {
        System.out.println("");
        System.out.println(" Cosa vuoi fare?");
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


    public void foundedResource(String risorsa) {
        System.out.println("Hai trovato " + risorsa + ", la vuoi prendere?\n" +
                "Inserisci 1 per raccoglierla\n" +
                "inserisci 0 per ignorarla");
    }


}



