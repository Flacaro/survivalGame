package org.example;

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

        // Imposta l'area iniziale del giocatore
        if (!areas.isEmpty()) {
            PlayerDomain player = gameDomain.getPlayer();
            currentArea = areaService.areaDomainMapper(areaService.areaMapper(gameDomain.getMap().getAreas().get(0)));
            player.setIdArea(currentArea.getId());
            mostraMessaggio("Ti trovi in: " + currentArea.getName());
        } else {
            mostraMessaggio("Nessuna area trovata.");
            return;
        }

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
                    mostraMessaggio("Scelta non valida.");
            }
        }

        mostraMessaggio("Grazie per aver giocato!");
    }

    private void esplora(GameDomain gameDomain, AreaDomain currentArea) throws IOException {
        ResourceDomain resourceDomain = gameService.triggerEvent(currentArea.getId(), gameDomain);

        if (resourceDomain != null) {
            risorsaTrovata(resourceDomain.getName());
            int choice = leggiSceltaRisorsa();

            switch (choice) {
                case 1:
                    if (gameDomain.getPlayer().getInventory().add(resourceDomain)) {
                        mostraMessaggio("Risorsa aggiunta all'inventario.");
                    } else {
                        mostraMessaggio("L'inventario è pieno! Non puoi raccogliere altre risorse.");
                    }
                    break;
                case 0:
                    mostraMessaggio("Risorsa ignorata.");
                    break;
            }
        } else {
            mostraMessaggio("Non hai trovato nulla questa volta.");
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
            mostraMessaggio("Ti sei spostato in: " + currentArea.getName());
        } else {
            mostraMessaggio("Non puoi muoverti in quella direzione.");
        }
    }

    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio);
    }

    public int leggiScelta(String messaggio) throws IOException {
        return leggiInputValido(bf, messaggio);
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
        mostraMessaggio("Cosa vuoi fare?");
        mostraMessaggio("1. Esplora");
        mostraMessaggio("2. Inventario");
        mostraMessaggio("3. Muoviti");
        mostraMessaggio("4. Esci");
        return leggiScelta("Inserisci un numero:");
    }

    private int leggiInputValido(BufferedReader bf, String messaggio) throws IOException {
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

    public int leggiSceltaDirezione() throws IOException {
        mostraMessaggio("Dove vuoi spostarti?\n" +
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
        mostraMessaggio("Hai trovato " + risorsa + ", la vuoi prendere?\n" +
                "Inserisci 1 per raccoglierla\n" +
                "inserisci 0 per ignorarla");
    }

    public void areaCorrente(String area) {
        mostraMessaggio("Ti sei spostato in: " + area);
    }
}