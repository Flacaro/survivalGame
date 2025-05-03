package controller;

import model.entity.*;

import model.entity.fight.Fight;
import view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameController {

    // Controllers di Logica/Dati
    private final StartController startController;
    private final DBController dbController;
    private final ResourceController resourceController;
    private final FightController fightController;

    // Views
    private final SetupView setupView;
    private final MainMenuView mainMenuView;
    private final ExplorationView explorationView;
    private final InventoryView inventoryView;
    private final MovementView movementView;
    private final CraftingView craftingView;

    // Stato del gioco
    private Game game;

    public GameController() {
        // Istanzia i controller necessari
        this.startController = new StartController();
        this.dbController = new DBController();
        this.resourceController = new ResourceController();
        this.fightController=new FightController();

        // Istanzia le view
        this.setupView = new SetupView();
        this.mainMenuView = new MainMenuView();
        this.explorationView = new ExplorationView();
        this.inventoryView = new InventoryView();
        this.movementView = new MovementView();
        this.craftingView = new CraftingView();
    }

    public void playGame() {
        try {
            // 1. Setup iniziale
            int mode = setupView.getDifficultyChoice();
            String nickname = setupView.getNickname();
            this.game = startController.start(mode, nickname); // Crea il gioco

            if (this.game == null || this.game.getPlayer() == null) {
                CommonViewUtils.displayMessage("Errore durante l'inizializzazione del gioco.");
                return;
            }

            setupView.displayWelcomeMessage();

            // 2. Game Loop Principale
            boolean continueToPlay = true;
            while (continueToPlay) {
                // Ricarica lo stato corrente se necessario (opzionale, dipende da come gestisci lo stato)
                // this.game = dbController.getGame(); // Potrebbe non essere necessario ad ogni ciclo

                int choice = mainMenuView.showMainMenu();

                switch (choice) {
                    case 1:
                        handleExplore();
                        break;
                    case 2:
                        handleShowInventory();
                        break;
                    case 3:
                        handleMove();
                        break;
                    case 4:
                        if (handleCrafting()) {
                            CommonViewUtils.displayMessage("Demo completata!");
                            continueToPlay = false; // Esce dopo crafting riuscito (come da logica originale)
                        }
                        break;
                    case 5:
                        continueToPlay = false; // Uscita scelta dall'utente
                        break;
                    default:
                        CommonViewUtils.displayMessage("Scelta non valida.");
                        break;
                }
            }

            CommonViewUtils.displayMessage("Grazie per aver giocato!");

        } catch (IOException e) {
            CommonViewUtils.displayMessage("Si è verificato un errore di input/output: " + e.getMessage());
            // Potresti voler loggare l'errore
        } catch (Exception e) {
            CommonViewUtils.displayMessage("Si è verificato un errore imprevisto: " + e.getMessage());
            e.printStackTrace(); // Utile per il debug
        } finally {
//            // 3. Cleanup
            dbController.close();
            CommonViewUtils.displayMessage("Gioco terminato.");
        }
    }

    private void handleExplore() throws IOException {
        Player player = game.getPlayer();
        long currentAreaId = player.getIdArea();

        // Logica di gioco per l'evento
        Event event = game.triggerEvent(currentAreaId, game);
        Area currentArea = dbController.getAreasById(currentAreaId); // Ottieni l'area per contesto

        if (event != null && currentArea != null ) {
            switch (currentArea.getCategory()){
                case "RISORSA":
                    SimpleResource resource= (SimpleResource) event;
                    explorationView.displayFoundResource(resource.getName());
                    int pickupChoice = explorationView.getPickupChoice();
                    if (pickupChoice == 1) {
                        if (game.pickUp(resource, player)) { // La logica di pickup aggiorna il player
                            dbController.updateMap(game.getMap(), resource); // Aggiorna la mappa nel DB
                            dbController.updatePlayer(player); // Salva lo stato aggiornato del player (inventario)
                            explorationView.displayResourcePickedUp();
                        } else {
                            explorationView.displayInventoryFull();
                        }
                    } else {
                        explorationView.displayResourceIgnored();
                    }
                case "NEMICO":
                    Enemy enemy= (Enemy) event;
                    Fight fight=new Fight(game,enemy);
                    fightController.setFight(fight);
                    fight.setObserverUI(fightController);
                    fightController.startFight();
            }

        } else {
            explorationView.displayNothingFound();
        }
        // Non è necessario aggiornare this.game qui se game.pickUp modifica direttamente l'oggetto player
        // e dbController salva le modifiche persistenti.
    }

    private void handleShowInventory() {
        Player player = game.getPlayer();
        Inventory inventory = dbController.showInventory(player); // Ottiene l'inventario aggiornato
        inventoryView.displayInventory(inventory);
    }

    private void handleMove() throws IOException {
        int direction = movementView.getDirectionChoice();
        Player player = game.getPlayer();
        long previousAreaId = player.getIdArea();

        // La logica di movimento dovrebbe aggiornare l'ID area del player DENTRO il metodo move
        // e restituire successo/fallimento e forse la nuova area.
        // Assumiamo che dbController.move aggiorni il player passato per riferimento (o che game lo faccia)
        boolean moved = dbController.move(direction, game); // 'move' dovrebbe aggiornare game.getPlayer().setIdArea()

        Area newArea = null;
        if (moved) {
            // Se dbController.move non aggiorna l'oggetto 'game', aggiornalo qui:
            // this.game = dbController.getGame(); // Ricarica lo stato se necessario
            newArea = dbController.getAreasById(player.getIdArea()); // Ottieni la nuova area DOPO lo spostamento
            dbController.updatePlayer(player); // Salva la nuova posizione del player
        }

        movementView.displayMovementResult(moved, newArea);

        // Se non ci si è mossi, non fare nulla (il messaggio è già mostrato)
    }


    private boolean handleCrafting() throws IOException {
        Player player = game.getPlayer();
        Inventory inventory = dbController.showInventory(player); // Ottieni inventario attuale

        if (inventory == null || inventory.getResources() == null || inventory.getResources().isEmpty()) {
            craftingView.displayInventoryEmpty();
            return false; // Non si può craftare senza risorse
        }

        ArrayList<CraftedResource> recipes = dbController.getCraftedResources();
        craftingView.displayCraftingRecipes(recipes);

        // Mostra inventario per la selezione e ottieni la mappa di corrispondenza indice->risorsa
        HashMap<Integer, SimpleResource> correspondenceMap = craftingView.displayInventoryForCrafting(inventory);

        if (correspondenceMap.isEmpty()) {
            // Già gestito da displayInventoryForCrafting
            return false;
        }

        String[] selections = craftingView.getCraftingInput(true); // Chiede input

        if (selections == null) { // Utente ha scelto 'annulla' o input vuoto
            CommonViewUtils.displayMessage("Crafting annullato.");
            return false;
        }

        // Validazione input selezione
        ArrayList<Integer> selectedIndices = new ArrayList<>();
        try {
            for (String s : selections) {
                int index = Integer.parseInt(s.trim());
                if (index < 1 || index > correspondenceMap.size() || !correspondenceMap.containsKey(index)) {
                    craftingView.displayInvalidInput();
                    return false;
                }
                selectedIndices.add(index);
            }
        } catch (NumberFormatException e) {
            craftingView.displayInvalidInput();
            return false;
        }


        // Controlla compatibilità e combina usando ResourceController
        CraftedResource resultingItem = resourceController.compatible(selections, correspondenceMap, recipes);

        if (resultingItem != null) {
            // Esegui il crafting (aggiorna l'inventario del player)
            Inventory updatedInventory = resourceController.combine(selections, correspondenceMap, inventory, resultingItem);
            player.setInventory(updatedInventory); // Aggiorna l'inventario nel player

            // Salva le modifiche nel DB
            dbController.updatePlayer(player);
            // Potrebbe essere necessario aggiornare anche l'oggetto Game se contiene copie
            // dbController.updateGame(game); // Dipende dalla struttura

            craftingView.displayCraftingResult(true, resultingItem);
            handleShowInventory(); // Mostra inventario aggiornato
            return true; // Crafting riuscito (e potenzialmente fine demo)
        } else {
            craftingView.displayCraftingResult(false, null);
            return false; // Combinazione non valida
        }
    }

}