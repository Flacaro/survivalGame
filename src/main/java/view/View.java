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
import services.AreaService;
import services.GameService;
import services.MapServices;
import services.PlayerService;

public class View {

    private BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));;


    public View() {
    }

    public void avviaGioco() throws IOException {
        StartController sc = new StartController();
        GameDomain g = sc.start();

        System.out.println("Ti svegli sulla spiaggia, confuso e dolorante."+"\n"+"L’aereo è precipitato. Intorno a te, solo mare, sabbia e frammenti del relitto."+"\n"+"Nessun segno di altri sopravvissuti.\n" +
                "\n" +
                "Hai bisogno di cibo, acqua e riparo. La giungla davanti a te è fitta e sconosciuta, ma non hai scelta: devi esplorare."+"\n"+" Il tuo zaino sarà la tua salvezza. Ogni oggetto raccolto potrebbe fare la differenza.\n" +
                "\n" +
                "Non sei solo su quest’isola. Qualcosa si muove tra gli alberi…"+"\n" +
                "Esplora, raccogli e sopravvivi. La tua avventura inizia ora.");

        boolean continuaAGiocare = true;

        while (continuaAGiocare) {
            int scelta = mostraMenuPrincipale();

            switch (scelta) {
                case 1:
                    esploraArea(g);
                    break;
                case 2:
                    mostraInventario(g);
                    break;
                case 3:
                    muovi(g);
                    break;
                case 4:
                    crafting(g);
                    break;
                case 5:
                    continuaAGiocare = false;
                    System.out.println("Grazie per aver giocato!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

    }

    private void crafting(GameDomain g) {
        DBController dbController=new DBController();
        ResourceController resourceController=new ResourceController();
        PlayerDomain playerDomain=dbController.getGame().getPlayerDomain();
        InventoryDomain inventory=playerDomain.getInventory();
        List<CraftedResourceDomain> craft=dbController.getCraftedResources();
        System.out.println("Combinazioni possibili");
        for(CraftedResourceDomain r: craft){
            System.out.println(r.getName()+ " combina "+r.getDescription());
        }

        if (inventory.getResources().size()==0) {
            System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
        }
        else {
            try {
            System.out.println("Inserisci il numero delle risorse da combinare (es. 0,1)");
            int counter=1;
            HashMap<Integer,ResourceDomain> corrisp= new HashMap<>();
            for(ResourceDomain r: inventory.getResources()){
                System.out.println(counter + r.getName());
                corrisp.put(counter,r);
                counter=counter+1;
            }
                String input = bf.readLine();
            //gestire gli input sbagliati
                String[] selections=input.split(",");
                if (selections.length!=1){
                    //se è vero checkSelections allora genera la risorsa ed aggiorna l'inventario
                    System.out.println("Hai creato: "+resourceController.checkSelections(selections,corrisp).getName());
                    resourceController.combine(selections,corrisp,g.getPlayer(),resourceController.checkSelections(selections,corrisp));}
                else {
                    System.out.println("Input non valido.");
                    mostraMenuPrincipale();
                }
            }
            catch (NumberFormatException | IOException e) {
                System.out.println("Input non valido.");
            }
        }
    }

    private void esploraArea(GameDomain gameDomain) throws IOException {
        long currentArea= gameDomain.getPlayer().getIdArea();
        GameService gameService=new GameService();
        ResourceDomain resourceDomain = gameService.triggerEvent(currentArea, gameDomain);
        MapServices ms= new MapServices();
        PlayerService playerService=new PlayerService();

        if (resourceDomain != null) {
            risorsaTrovata(resourceDomain.getName());
            int choice = leggiScelta("Inserisci 1 o 0:");
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
         else {
            System.out.println("Non hai trovato nulla questa volta.");
        }
    }

    private void muovi(GameDomain gameDomain) throws IOException {
        PlayerDomain pd = gameDomain.getPlayer();
        int move = leggiSceltaDirezione();
        //MoveController moveController = new MoveController();
        DBController dbController=new DBController();
        boolean moved = dbController.move(move, gameDomain);
        AreaDomain areaDomain=dbController.getAreasById(pd.getIdArea());
        if (moved) {
            System.out.println("Ti sei spostato in: " + areaDomain.getName());
        } else {
            System.out.println("Non puoi muoverti in quella direzione.");
        }
    }

    public int leggiScelta(String messaggio) throws IOException {
            try {
                System.out.println(messaggio);
                String input =bf.readLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
            return 0;
    }

    public void mostraInventario(GameDomain g) {
        DBController dbController=new DBController();
        PlayerDomain pd=g.getPlayer();
        InventoryDomain inventory=dbController.showInventory(pd);

        if (inventory.getResources()== null && inventory.getResourcesSelected()==null) {
            System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
        } else {
            System.out.println("Contenuto dell'inventario:");
            if(inventory.getResources()!= null){
            for (ResourceDomain r : inventory.getResources()) {
                System.out.println("-" + r.getName());
            }}
            if(inventory.getResourcesSelected()!=null){
                for (CraftedResourceDomain r : inventory.getResourcesSelected()) {
                    System.out.println("-" + r.getName());
                }
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



