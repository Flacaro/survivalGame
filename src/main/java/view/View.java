package view;

import controller.DBController;
import controller.ResourceController;
import controller.StartController;
import model.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class View {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


    public View() {
    }

    public void playGame() throws IOException {
        StartController sc = new StartController();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("scegli una modalita'\n");
        System.out.println("Scegli 1 per la modalita' facile, 2 per quella media, 3 per quella difficile'\n");
        int mode = Integer.parseInt(bf.readLine());
        System.out.println("scegli un nickname\n");
        String nickname = bf.readLine();

        Game g = sc.start(mode, nickname);

        System.out.println("\nTi svegli sulla spiaggia, confuso e dolorante." + "\n" + "L’aereo è precipitato. Intorno a te, solo mare, sabbia e frammenti del relitto." + "\n" + "Nessun segno di altri sopravvissuti.\n" +
                "\n" +
                "Hai bisogno di cibo, acqua e riparo. La giungla davanti a te è fitta e sconosciuta, ma non hai scelta: devi esplorare." + "\n" + " Il tuo zaino sarà la tua salvezza. Ogni oggetto raccolto potrebbe fare la differenza.\n" +
                "\n" +
                "Non sei solo su quest’isola. Qualcosa si muove tra gli alberi…" + "\n" +
                "Esplora, raccogli e sopravvivi. La tua avventura inizia ora.");

        System.out.println("Benvenuto nella demo! Esplora le aree, raccogli le risorse e crafta una risorsa per completarla e passare al gioco.\n");
        boolean continueToPlay = true;
        DBController dbController = new DBController();
        while (continueToPlay) {
            int choice = showMainMenu();
            g = dbController.getGame();
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
                    dbController.close();
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

    }

    private boolean crafting(Game g) {
        DBController dbController = new DBController();
        ResourceController resourceController = new ResourceController();
        Player player = g.getPlayer();
        Inventory inventory = dbController.showInventory(player);
        ArrayList<CraftedResource> craft = dbController.getCraftedResources();
        System.out.println("Combinazioni possibili per il crafting degli oggetti");
        System.out.println("-----------------------------------------------------");
        for (CraftedResource r : craft) {
            String recipe="Per creare " + r.getName() + " seleziona ";
            for (SimpleResource s : r.getComponents()){
                recipe+=s.getName()+ " ";
            }
            System.out.println(recipe);
            System.out.println("-----------------------------------------------------");
        }

        if (inventory.getResources().isEmpty()) {
            System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
            return false;
        } else {
            try {
                System.out.println("Inserisci l'indice delle risorse da combinare separate tramite virgola (es. 1,2)");
                int counter = 0;
                HashMap<Integer, SimpleResource> corrisp = new HashMap<>();
                for (SimpleResource r : inventory.getResources()) {
                    counter = counter + 1;
                    System.out.println("Inserire l'indice " + counter + " per selezionare la risorsa: " + r.getName());
                    corrisp.put(counter, r);
                }
                String input = bf.readLine();
                String[] selections = input.split(",");
                for (String s : selections) {
                    if (Integer.parseInt(s) < 1 || selections.length > corrisp.size() || Integer.parseInt(s) > corrisp.size()) {
                        System.out.println("Input non valido.");
                        return false;
                    }
                }
                CraftedResource pair = resourceController.compatible(selections, corrisp, craft);
                if (pair != null) {
                    player.setInventory(resourceController.combine(selections, corrisp, inventory, pair));
                    System.out.println("Hai creato: " + pair.getName());
                    dbController.updatePlayer(player);
                    g.setPlayer(player);
                    dbController.updateGame(g);
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

    private void exploreArea(Game game) throws IOException {
        long currentIdArea = game.getPlayer().getIdArea();
        SimpleResource resource = game.triggerEvent(currentIdArea, game);
        DBController dbController = new DBController();
        Area currentArea = dbController.getAreasById(currentIdArea);

        if (resource != null) {
            if (currentArea.getIdEvent() == resource.getId()) {
                foundedResource(resource.getName());
                int choice = readChoice("Inserisci 1 o 0:");
                switch (choice) {
                    case 1:
                        //pickup
                        if (game.pickUp(resource, game.getPlayer())) {
                            dbController.updateMap(game.getMap(), resource);
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

    private void move(Game game) throws IOException {
        Player pd = game.getPlayer();
        int move = readDirectionChoice();
        DBController dbController = new DBController();
        //non so dove mettere il move qua non sta bene nel dbController
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

    public void showInventory(Game g) {
        DBController dbController = new DBController();
        Player pd = g.getPlayer();
        Inventory inventory = dbController.showInventory(pd);
        List<ResourceQuantityInv> resourcesQuantity = inventory.getResources_quantity();
        if (!inventory.getResources().isEmpty()) {
            System.out.println("Contenuto dell'inventario:");
            for (SimpleResource r : inventory.getResources()) {
                for (ResourceQuantityInv rqid : resourcesQuantity) {
                    if (Objects.equals(r.getName(), rqid.getResource().getName())) {
                        System.out.println("-" + r.getName() + "  quantità:" + rqid.getQuantity());
                    }
                }
            }
            if (!inventory.getCraftedResourceList().isEmpty()) {
                for (CraftedResource r : inventory.getCraftedResourceList()) {
                    System.out.println("-" + r.getName() + "  quantità:" + r.getQuantity());
                }
            }
        } else {
            if (inventory.getResources().isEmpty()) {
                if (!inventory.getCraftedResourceList().isEmpty()) {
                    System.out.println("Contenuto dell'inventario:");
                    for (CraftedResource r : inventory.getCraftedResourceList()) {
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



