/*package org.example;

import controller.MoveController;
import controller.StartController;
import model.*;
import model.domain.GameDomain;
import model.domain.PlayerDomain;
import model.domain.ResourceDomain;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
//        //restituisce una risorsa dobbiamo fargliela vedere e chiedere cosa fare
//        ResourceDomain resourceDomain=gameService.triggerEvent(g.getPlayer().getIdArea(), g);
//        System.out.println("Hai trovato " + resourceDomain.getName()+ ", la vuoi prendere?\n"+ "Inserisci 1 per raccoglierla\n"+
//                "inserisci 0 per ignorarla");
//        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
//        int choice= Integer.parseInt(bf.readLine());
//        switch (choice){
//            case 1:
//                //controlla capienza inventario
//                //aggiungi se c'è posto
//                //rifiuta se non c'è postp
//                System.out.println("Risorsa aggiunta all'inventario");
//                break;
//            case 2:
//                System.out.println("Risorsa ignorata");
//                break;
//        }
        PlayerDomain pd=g.getPlayer();
        System.out.println("Dove vuoi spostarti?\n"+"Inserisci 0 per andare a nord\n"+"Inserisci 1 per andare a est\n"
                +"Inserisci 2 per andare a sud\n"+"Inserisci 3 per andare a ovest\n");
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        int move= Integer.parseInt(bf.readLine());
        MoveController moveController= new MoveController();
        System.out.println(moveController.move(move,g));


    }


}*/
/*import controller.StartController;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.domain.MapDomain;
import model.domain.ModeDomain;
import model.domain.ResourceDomain;
import services.AreaService;
import services.GameService;
import services.MapServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int MAX_INVENTORY_SIZE = 10;

    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        MapServices mapServices = new MapServices();

        List<ResourceDomain> inventory = new ArrayList<>();
        AreaDomain currentArea = null;

        // Ottieni la mappa dal GameDomain
        MapDomain mapDomain = g.getMapDomain();
        List<AreaDomain> areas = mapDomain.getAreas();

        // Imposta l'area iniziale del giocatore (ad esempio, la prima area nella lista)
        if (!areas.isEmpty()) {
            currentArea = areas.get(0);
            System.out.println("Ti trovi in: " + currentArea.getName());
        } else {
            System.out.println("Nessuna area trovata.");
            return;
        }

        boolean continuaAGiocare = true;
        while (continuaAGiocare) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Esplora");
            System.out.println("2. Inventario");
            System.out.println("3. Cambia area");
            System.out.println("4. Esci");

            int scelta = leggiInputValido(bf, "Inserisci un numero:");

            switch (scelta) {
                case 1:
                    ResourceDomain resourceDomain = gameService.triggerEvent(currentArea.getId(), g);

                    if (resourceDomain != null) {
                        System.out.println("Hai trovato " + resourceDomain.getName() + ", la vuoi prendere?\n" +
                                "Inserisci 1 per raccoglierla\n" +
                                "inserisci 0 per ignorarla");

                        int choice = leggiInputValido(bf, "Inserisci 1 o 0:");

                        switch (choice) {
                            case 1:
                                if (inventory.size() < MAX_INVENTORY_SIZE) {
                                    inventory.add(resourceDomain);
                                    System.out.println("Risorsa aggiunta all'inventario.");
                                } else {
                                    System.out.println("L'inventario è pieno! Non puoi raccogliere altre risorse.");
                                }
                                break;
                            case 0:
                                System.out.println("Risorsa ignorata.");
                                break;
                        }
                    } else {
                        System.out.println("Non hai trovato nulla questa volta.");
                    }
                    break;
                case 2:
                    System.out.println("Inventario:");
                    if (inventory.isEmpty()) {
                        System.out.println("Vuoto");
                    } else {
                        for (ResourceDomain r : inventory) {
                            System.out.println("- " + r.getName());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Scegli la nuova area:");
                    for (int i = 0; i < areas.size(); i++) {
                        System.out.println((i + 1) + ". " + areas.get(i).getName());
                    }

                    int nuovaAreaScelta = leggiInputValido(bf, "Inserisci un numero:");
                    if (nuovaAreaScelta >= 1 && nuovaAreaScelta <= areas.size()) {
                        currentArea = areas.get(nuovaAreaScelta - 1);
                        System.out.println("Ti sei spostato in: " + currentArea.getName());
                    } else {
                        System.out.println("Scelta non valida.");
                    }
                    break;
                case 4:
                    continuaAGiocare = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }

        System.out.println("Grazie per aver giocato!");
    }

    private static int leggiInputValido(BufferedReader bf, String messaggio) throws IOException {
        while (true) {
            try {
                System.out.println(messaggio);
                String input = bf.readLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
        }
    }
}*/
/*package org.example;

import controller.MoveController;
import controller.StartController;
import model.domain.GameDomain;
import model.domain.PlayerDomain;
import model.domain.ResourceDomain;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        PlayerDomain pd = g.getPlayer();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        boolean running = true;
        while (running) {
            System.out.println("Dove vuoi spostarti?\n" +
                    "Inserisci 0 per andare a nord\n" +
                    "Inserisci 1 per andare a est\n" +
                    "Inserisci 2 per andare a sud\n" +
                    "Inserisci 3 per andare a ovest\n" +
                    "Inserisci 4 per uscire");

            int move = Integer.parseInt(bf.readLine());
            if (move == 4) {
                running = false;
                continue;
            }

            MoveController moveController = new MoveController();
            boolean moved = moveController.move(move, g);

            if (moved) {
                System.out.println("Ti sei spostato nella cella: " + pd.getIdArea());

                ResourceDomain resourceDomain = gameService.triggerEvent(pd.getIdArea(), g);
                if (resourceDomain != null) {
                    System.out.println("Hai trovato " + resourceDomain.getName() + ", la vuoi prendere?\n" +
                            "Inserisci 1 per raccoglierla\n" +
                            "Inserisci 0 per ignorarla");
                    int choice = Integer.parseInt(bf.readLine());
                    if (choice == 1) {
                        if (pd.getInventory().add(resourceDomain)) {
                            System.out.println("Risorsa aggiunta all'inventario");
                        } else {
                            System.out.println("Inventario pieno, risorsa non raccolta");
                        }
                    } else {
                        System.out.println("Risorsa ignorata");
                    }
                } else {
                    System.out.println("Non hai trovato risorse nella cella " + pd.getIdArea() + "."); // Aggiunta questa riga
                }
            } else {
                System.out.println("Non puoi andare fuori dalla mappa.");
            }
        }
        System.out.println("Grazie per aver giocato!");
    }
}*/
/*package org.example;

import controller.MoveController;
import controller.StartController;
import model.domain.GameDomain;
import model.domain.PlayerDomain;
import model.domain.ResourceDomain;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        PlayerDomain pd = g.getPlayer();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        boolean running = true;
        while (running) {
            System.out.println("Dove vuoi spostarti?\n" +
                    "Inserisci 0 per andare a nord\n" +
                    "Inserisci 1 per andare a est\n" +
                    "Inserisci 2 per andare a sud\n" +
                    "Inserisci 3 per andare a ovest\n" +
                    "Inserisci 4 per uscire");

            int move = Integer.parseInt(bf.readLine());
            if (move == 4) {
                running = false;
                continue;
            }

            MoveController moveController = new MoveController();
            boolean moved = moveController.move(move, g);

            if (moved) {
                System.out.println("Ti sei spostato nella cella: " + pd.getIdArea());

                ResourceDomain resourceDomain = gameService.triggerEvent(pd.getIdArea(), g);
                if (resourceDomain != null) {
                    System.out.println("Hai trovato " + resourceDomain.getName() + ", la vuoi prendere?\n" +
                            "Inserisci 1 per raccoglierla\n" +
                            "Inserisci 0 per ignorarla");
                    int choice = Integer.parseInt(bf.readLine());
                    if (choice == 1) {
                        if (pd.getInventory().add(resourceDomain)) {
                            System.out.println("Risorsa aggiunta all'inventario");
                        } else {
                            System.out.println("Inventario pieno, risorsa non raccolta");
                        }
                    } else {
                        System.out.println("Risorsa ignorata");
                    }
                } else {
                    System.out.println("Non hai trovato risorse nella cella " + pd.getIdArea() + ".");
                }
            } else {
                System.out.println("Non puoi andare fuori dalla mappa.");
            }
        }
        System.out.println("Grazie per aver giocato!");
    }
}*/
/*import controller.MoveController;
import controller.StartController;
import model.domain.*;
import services.AreaService;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int MAX_INVENTORY_SIZE = 10;

    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        AreaService areaService = new AreaService(); // Aggiunto AreaService

        List<ResourceDomain> inventory = new ArrayList<>();
        AreaDomain currentArea = null;

        // Ottieni la mappa dal GameDomain
        MapDomain mapDomain = g.getMapDomain();
        List<AreaDomain> areas = mapDomain.getAreas();

        // Imposta l'area iniziale del giocatore (ad esempio, la prima area nella lista)
        if (!areas.isEmpty()) {
            PlayerDomain player = g.getPlayer();
            currentArea = areaService.areaDomainMapper(areaService.areaMapper(g.getMap().getAreas().get(0)));
            player.setIdArea(currentArea.getId()); // Imposta l'ID dell'area iniziale nel giocatore
            System.out.println("Ti trovi in: " + currentArea.getName());
        } else {
            System.out.println("Nessuna area trovata.");
            return;
        }

        boolean continuaAGiocare = true;
        while (continuaAGiocare) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Esplora");
            System.out.println("2. Inventario");
            System.out.println("3. Muoviti");
            System.out.println("4. Esci");

            int scelta = leggiInputValido(bf, "Inserisci un numero:");

            switch (scelta) {
                case 1:
                    ResourceDomain resourceDomain = gameService.triggerEvent(currentArea.getId(), g);

                    if (resourceDomain != null) {
                        System.out.println("Hai trovato " + resourceDomain.getName() + ", la vuoi prendere?\n" +
                                "Inserisci 1 per raccoglierla\n" +
                                "inserisci 0 per ignorarla");

                        int choice = leggiInputValido(bf, "Inserisci 1 o 0:");

                        switch (choice) {
                            case 1:
                                if (inventory.size() < MAX_INVENTORY_SIZE) {
                                    inventory.add(resourceDomain);
                                    System.out.println("Risorsa aggiunta all'inventario.");
                                } else {
                                    System.out.println("L'inventario è pieno! Non puoi raccogliere altre risorse.");
                                }
                                break;
                            case 0:
                                System.out.println("Risorsa ignorata.");
                                break;
                        }
                    } else {
                        System.out.println("Non hai trovato nulla questa volta.");
                    }
                    break;
                case 2:
                    System.out.println("Inventario:");
                    if (inventory.isEmpty()) {
                        System.out.println("Vuoto");
                    } else {
                        for (ResourceDomain r : inventory) {
                            System.out.println("- " + r.getName());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Dove vuoi spostarti?\n" +
                            "Inserisci 0 per andare a nord\n" +
                            "Inserisci 1 per andare a est\n" +
                            "Inserisci 2 per andare a sud\n" +
                            "Inserisci 3 per andare a ovest\n");

                    int move = leggiInputValido(bf, "Inserisci un numero:");
                    MoveController moveController = new MoveController();
                    boolean moved = moveController.move(move, g);

                    if (moved) {
                        PlayerDomain player = g.getPlayer();
                        currentArea = areaService.areaDomainMapper(areaService.areaMapper(g.getMap().getAreas().get((int) player.getIdArea())));
                        System.out.println("Ti sei spostato in: " + currentArea.getName());
                    } else {
                        System.out.println("Non puoi muoverti in quella direzione.");
                    }
                    break;
                case 4:
                    continuaAGiocare = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }

        System.out.println("Grazie per aver giocato!");
    }

    private static int leggiInputValido(BufferedReader bf, String messaggio) throws IOException {
        while (true) {
            try {
                System.out.println(messaggio);
                String input = bf.readLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
        }
    }
}*/
/*import controller.MoveController;
import controller.StartController;
import model.domain.*;
import services.AreaService;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        AreaService areaService = new AreaService();

        AreaDomain currentArea = null;

        // Ottieni la mappa dal GameDomain
        MapDomain mapDomain = g.getMapDomain();
        List<AreaDomain> areas = mapDomain.getAreas();

        // Imposta l'area iniziale del giocatore
        if (!areas.isEmpty()) {
            PlayerDomain player = g.getPlayer();
            currentArea = areaService.areaDomainMapper(areaService.areaMapper(g.getMap().getAreas().get(0)));
            player.setIdArea(currentArea.getId());
            System.out.println("Ti trovi in: " + currentArea.getName());
        } else {
            System.out.println("Nessuna area trovata.");
            return;
        }

        boolean continuaAGiocare = true;
        while (continuaAGiocare) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Esplora");
            System.out.println("2. Inventario");
            System.out.println("3. Muoviti");
            System.out.println("4. Esci");

            int scelta = leggiInputValido(bf, "Inserisci un numero:");

            switch (scelta) {
                case 1:
                    ResourceDomain resourceDomain = gameService.triggerEvent(currentArea.getId(), g);

                    if (resourceDomain != null) {
                        System.out.println("Hai trovato " + resourceDomain.getName() + ", la vuoi prendere?\n" +
                                "Inserisci 1 per raccoglierla\n" +
                                "inserisci 0 per ignorarla");

                        int choice = leggiInputValido(bf, "Inserisci 1 o 0:");

                        switch (choice) {
                            case 1:
                                // Usa l'inventario del giocatore per aggiungere la risorsa
                                if (g.getPlayer().getInventory().add(resourceDomain)) {
                                    System.out.println("Risorsa aggiunta all'inventario.");
                                } else {
                                    System.out.println("L'inventario è pieno! Non puoi raccogliere altre risorse.");
                                }
                                break;
                            case 0:
                                System.out.println("Risorsa ignorata.");
                                break;
                        }
                    } else {
                        System.out.println("Non hai trovato nulla questa volta.");
                    }
                    break;
                case 2:
                    System.out.println("Inventario:");
                    if (g.getPlayer().getInventory().getResources() == null || g.getPlayer().getInventory().getResources().isEmpty()) {
                        System.out.println("Vuoto");
                    } else {
                        for (ResourceDomain r : g.getPlayer().getInventory().getResources()) {
                            System.out.println("- " + r.getName());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Dove vuoi spostarti?\n" +
                            "Inserisci 0 per andare a nord\n" +
                            "Inserisci 1 per andare a est\n" +
                            "Inserisci 2 per andare a sud\n" +
                            "Inserisci 3 per andare a ovest\n");

                    int move = leggiInputValido(bf, "Inserisci un numero:");
                    MoveController moveController = new MoveController();
                    boolean moved = moveController.move(move, g);

                    if (moved) {
                        PlayerDomain player = g.getPlayer();
                        currentArea = areaService.areaDomainMapper(areaService.areaMapper(g.getMap().getAreas().get((int) player.getIdArea())));
                        System.out.println("Ti sei spostato in: " + currentArea.getName());
                    } else {
                        System.out.println("Non puoi muoverti in quella direzione.");
                    }
                    break;
                case 4:
                    continuaAGiocare = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }

        System.out.println("Grazie per aver giocato!");
    }

    private static int leggiInputValido(BufferedReader bf, String messaggio) throws IOException {
        while (true) {
            try {
                System.out.println(messaggio);
                String input = bf.readLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
        }
    }
}*/
/*import controller.MoveController;
import controller.StartController;
import model.domain.*;
import services.AreaService;
import services.GameService;
import java.util.List; // Aggiungi questa riga
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        AreaService areaService = new AreaService();

        AreaDomain currentArea = null;

        // Ottieni la mappa dal GameDomain
        MapDomain mapDomain = g.getMapDomain();
        List<AreaDomain> areas = mapDomain.getAreas();

        // Imposta l'area iniziale del giocatore
        if (!areas.isEmpty()) {
            PlayerDomain player = g.getPlayer();
            currentArea = areaService.areaDomainMapper(areaService.areaMapper(g.getMap().getAreas().get(0)));
            player.setIdArea(currentArea.getId());
            System.out.println("Ti trovi in: " + currentArea.getName());
        } else {
            System.out.println("Nessuna area trovata.");
            return;
        }

        boolean continuaAGiocare = true;
        while (continuaAGiocare) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Esplora");
            System.out.println("2. Inventario");
            System.out.println("3. Muoviti");
            System.out.println("4. Esci");

            int scelta = leggiInputValido(bf, "Inserisci un numero:");

            switch (scelta) {
                case 1:
                    ResourceDomain resourceDomain = gameService.triggerEvent(currentArea.getId(), g);

                    if (resourceDomain != null) {
                        System.out.println("Hai trovato " + resourceDomain.getName() + ", la vuoi prendere?\n" +
                                "Inserisci 1 per raccoglierla\n" +
                                "inserisci 0 per ignorarla");

                        int choice = leggiInputValido(bf, "Inserisci 1 o 0:");

                        switch (choice) {
                            case 1:
                                // Usa l'inventario del giocatore per aggiungere la risorsa
                                if (g.getPlayer().getInventory().add(resourceDomain)) {
                                    System.out.println("Risorsa aggiunta all'inventario.");
                                } else {
                                    System.out.println("L'inventario è pieno! Non puoi raccogliere altre risorse.");
                                }
                                break;
                            case 0:
                                System.out.println("Risorsa ignorata.");
                                break;
                        }
                    } else {
                        System.out.println("Non hai trovato nulla questa volta.");
                    }
                    break;
                case 2:
                    System.out.println("Inventario:");
                    if (g.getPlayer().getInventory().getResources() == null || g.getPlayer().getInventory().getResources().isEmpty()) {
                        System.out.println("Vuoto");
                    } else {
                        for (ResourceDomain r : g.getPlayer().getInventory().getResources()) {
                            System.out.println("- " + r.getName());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Dove vuoi spostarti?\n" +
                            "Inserisci 0 per andare a nord\n" +
                            "Inserisci 1 per andare a est\n" +
                            "Inserisci 2 per andare a sud\n" +
                            "Inserisci 3 per andare a ovest\n");

                    int move = leggiInputValido(bf, "Inserisci un numero:");
                    MoveController moveController = new MoveController();
                    boolean moved = moveController.move(move, g);

                    if (moved) {
                        PlayerDomain player = g.getPlayer();
                        currentArea = areaService.areaDomainMapper(areaService.areaMapper(g.getMap().getAreas().get((int) player.getIdArea())));
                        System.out.println("Ti sei spostato in: " + currentArea.getName());
                    } else {
                        System.out.println("Non puoi muoverti in quella direzione.");
                    }
                    break;
                case 4:
                    continuaAGiocare = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }

        System.out.println("Grazie per aver giocato!");
    }

    private static int leggiInputValido(BufferedReader bf, String messaggio) throws IOException {
        while (true) {
            try {
                System.out.println(messaggio);
                String input = bf.readLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
        }
    }
}*/package org.example;

import controller.StartController;
import model.domain.GameDomain;
import org.example.view.View;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        StartController sc = new StartController();
        GameDomain gameDomain = sc.start();

        View view = new View();
        view.avviaGioco(gameDomain);
    }
}