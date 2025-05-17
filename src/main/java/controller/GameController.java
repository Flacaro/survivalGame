package controller;

import model.entity.*;
import model.entity.fight.Fight;
import view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
        this.startController = new StartController();
        this.dbController = new DBController();
        this.resourceController = new ResourceController();
        this.fightController = new FightController();

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
            this.game = startController.start(mode, nickname);
            if (game == null || game.getPlayer() == null) {
                CommonViewUtils.displayMessage("Errore durante l'inizializzazione del gioco.");
                return;
            }

            setupView.displayWelcomeMessage();

            // 2. Game Loop Principale
            boolean continueToPlay = true;
            while (continueToPlay) {

                handleEnemy();
                handleCheckpoint();
                int choice = mainMenuView.showMainMenu();

                switch (choice) {
                    case 1:
                        handleExplore();
                        continueToPlay = handleEndFight();
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
                            continueToPlay = false; // Esce dopo crafting riuscito
                        }
                        break;
                    case 5:
                        continueToPlay = false;
                        dbController.updateGame(game);
                        break;
                    default:
                        CommonViewUtils.displayMessage("Scelta non valida.");
                        break;
                }
            }

            CommonViewUtils.displayMessage("Grazie per aver giocato!");

        } catch (IOException e) {
            CommonViewUtils.displayMessage("Si è verificato un errore di input/output: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbController.close();
            CommonViewUtils.displayMessage("Gioco terminato.");
        }
    }

    private void handleEnemy() throws IOException {
        Player player = game.getPlayer();
        Area currentAreaId = player.getId_Area();
        if (currentAreaId != null) {
            Event event = currentAreaId.getIdEvent();
            if (event != null) {
                if (Objects.equals(currentAreaId.getCategory(), "NEMICO")) {
                    Enemy enemy = (Enemy) event;
                    Fight fight = new Fight(game, enemy);
                    fightController.setFight(fight);
                    fight.setObserverUI(fightController);
                    fightController.startFight();
                    if (handleEndFight()) {
                        currentAreaId.setIdEvent(null);
                        currentAreaId.setCategory(null);
                    }
                }
            }
        }
    }

    private void handleExplore() throws IOException {
        Player player = game.getPlayer();
        Area currentAreaId = player.getId_Area();
        Event event = currentAreaId.getIdEvent();
        if (event != null) {
            switch (currentAreaId.getCategory()) {
                case "RISORSA":
                    SimpleResource resource = (SimpleResource) event;
                    explorationView.displayFoundResource(resource.getName());
                    int pickupChoice = explorationView.getPickupChoice();
                    if (pickupChoice == 1) {
                        if (game.pickUp(resource, player)) {

                            currentAreaId.setIdEvent(null);
                            currentAreaId.setCategory(null);
                            explorationView.displayResourcePickedUp();
                        } else {
                            explorationView.displayInventoryFull();
                        }
                    } else {
                        explorationView.displayResourceIgnored();
                    }
                case "NEMICO":
                    handleEnemy();
            }

        } else {
            explorationView.displayNothingFound();
        }
    }

    private boolean handleEndFight() {
        return game.getPlayer().getHealth() != 0.0;
    }

    private void handleShowInventory() {
        Player player = game.getPlayer();
        Inventory inventory = player.getInventory();
        inventoryView.displayInventory(inventory);
    }

    private void handleMove() throws IOException {
        int direction = movementView.getDirectionChoice();
        Player player = game.getPlayer();

        boolean moved = game.move(direction);

        movementView.displayMovementResult(moved, player.getId_Area());
    }


    private boolean handleCrafting() throws IOException {
        Player player = game.getPlayer();
        Inventory inventory = player.getInventory();

        if (inventory == null || inventory.getResources() == null || inventory.getResources().isEmpty()) {
            craftingView.displayInventoryEmpty();
            return false;
        }

        ArrayList<CraftedResource> recipes = dbController.getCraftedResources();
        craftingView.displayCraftingRecipes(recipes);

        // Mostra inventario per la selezione e ottieni la mappa di corrispondenza indice->risorsa
        HashMap<Integer, SimpleResource> correspondenceMap = craftingView.displayInventoryForCrafting(inventory);

        if (correspondenceMap.isEmpty()) {
            return false;
        }

        String[] selections = craftingView.getCraftingInput(true);

        if (selections == null) {
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
            Inventory updatedInventory = resourceController.combine(selections, correspondenceMap, inventory, resultingItem);
            player.setInventory(updatedInventory);

            craftingView.displayCraftingResult(true, resultingItem);
            handleShowInventory();
            return true;
        } else {
            craftingView.displayCraftingResult(false, null);
            return false;
        }
    }

    public void handleCheckpoint() {
        Player player = game.getPlayer();
        if (player.getId_Area().getCheckpoint() != null) {
            player.setExp(player.getExp() + (10 * player.getId_Area().getCheckpoint().getExp()));
            List<Skill> skills = new ArrayList<>();
            skills.add(player.getId_Area().getCheckpoint().getSkill());
            player.setSkills(skills);
            game.setPlayer(player);
            dbController.updateGame(game);
            CommonViewUtils.displayMessage("\nHai raggiunto il CHECKPOINT! Hai guadagnato " + player.getId_Area().getCheckpoint().getExp() + " punti esperienza!\n"
            + "Hai sbloccato una nuova skill! " + player.getSkills().get(0).getName() + "\nOra puoi costruire!");

        }
    }

}