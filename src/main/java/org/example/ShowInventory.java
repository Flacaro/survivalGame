package org.example;

import controller.DBController;
import controller.ResourceController;
import controller.StartController;
import model.domain.*;
import persistence.ResourceDaoImpl;
import services.InventoryService;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowInventory {
    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        DBController dbController=new DBController();
        GameDomain g = sc.start();

        PlayerDomain pd=g.getPlayer();
        ResourceController resourceController= new ResourceController();
        InventoryDomain inventory=dbController.showInventory(pd);
        InventoryService inventoryService=new InventoryService();
        //add resource necessarie per il craftig nell'inventario

        //add resource necessarie per il craftig nell'inventario
        List<ResourceDomain> res= new ArrayList<>();
        ResourceDaoImpl resourceDao= new ResourceDaoImpl();
        res=resourceDao.getResourceByName();
        inventory.setResources(res);
        for(ResourceDomain r:res){
            inventoryService.updateInventory(r,inventory);
        }

        List<CraftedResourceDomain> craft=dbController.getCraftedResources();
        System.out.println("Combinazioni possibili");
        for(CraftedResourceDomain r: craft){
            System.out.println(r.getName()+ " combina "+r.getDescription());
        }

        //selezione delle risorse
        System.out.println("Inserisci il numero delle risorse da combinare (es. 0,1)");
        int counter=1;
        HashMap<Integer,ResourceDomain> corrisp= new HashMap<>();
        for(ResourceDomain r: inventory.getResources()){
            System.out.println(counter + r.getName());
            corrisp.put(counter,r);
            counter=counter+1;
        }
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
       try {
            String input = bf.readLine();
            String[] selections=input.split(",");
            //se è vero checkSelections allora genera la risorsa ed aggiorna l'inventario
           CraftedResourceDomain cf=resourceController.checkSelections(selections,corrisp);
           if(cf!=null){
            System.out.println("Hai creato: "+cf.getName());
            resourceController.combine(selections,corrisp,pd,cf);}
           else{

               System.out.println("Combinazione non valida.");
           }
            //se è falso input non valido.
        } catch (NumberFormatException e) {
            System.out.println("Input non valido.");
        }
    }
}
