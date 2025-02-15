package Controller;

import org.example.model.Inventory;
import org.example.model.Resource;
import org.example.persistence.InventoryDaoImpl;

import java.util.ArrayList;

public class InventoryController {

    private Inventory inventory;
    private InventoryDaoImpl inventoryDao= new InventoryDaoImpl();

    public void addSelections(long idInventory){
        //query che prende l'inventario
        inventory= inventoryDao.getInventory(idInventory);
        for(Resource r : inventory.getResources()){
            //gliele faccio vedere tutte e lui me le deve selezionare
            System.out.println("risorsa: ");
        }

    }
    //deve aggiornare l'inventario dopo che il giocatore ha craftato una risorsa

    //deve aggiornare l'inventario se il giocatore ha preso una risorsa
    //deve avere una funzione che mi seleziona le risorse da craftare
    public void combine (){

    }
    //deve tenere traccia delle quantita' delle risorse (esempio se le rimuove o le usa)
}
