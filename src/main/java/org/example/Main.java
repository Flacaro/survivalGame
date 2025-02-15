package org.example;


import Controller.StartController;
import org.example.model.*;
import org.example.persistence.EntityManagerSingleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

//   public class SQLLoader {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mioPU");
//        EntityManager em = emf.createEntityManager();
//
//        try {
//            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/import.sql"));
//            em.getTransaction().begin();
//            for (String sql : lines) {
//                em.createNativeQuery(sql).executeUpdate();
//            }
//            em.getTransaction().commit();
//            System.out.println("Dati caricati!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            em.close();
//            emf.close();
//        }
//    }
//}
//
//
//    public static void main(String[] args){
//        //StartController.start();
//        Mode mode= new Mode();
//        mode.setResourcesAndArea(1);
//        Game game= new Game();
//
//        game.start(mode);
//        System.out.println("numero di caselle: "+ game.getMap().getTotalMapArea().size());
//    }

    public static void main(String[] args) {
        //caricamento delle risorse generiche all'interno del db attraverso il file import.sql
        var em = EntityManagerSingleton.getEntityManager();

        try{
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/import.sql"));
            em.getTransaction().begin();
            for (String sql : lines) {
                em.createNativeQuery(sql).executeUpdate();}
            em.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }
//        var tx = em.getTransaction();
//
//        tx.begin();
//        Attack attack = new Attack("boom!", 10.0, "boom");
//
//        em.persist(attack);
//        System.out.println("Persisting attack: " + attack);
//
//        ArrayList<Attack> attaks = new ArrayList<>();
//        Attack attack = new Attack("boom!", 10.0, "boom");
//        Attack attack1 = new Attack("boom!", 15.0, "boom");
//
//        attaks.add(attack);
//        attaks.add(attack1);
//        Resource r = new Resource("cibo", null, 1, "acqua", "cibo");
//        Resource r2 = new Resource("arma", attaks, 1, "legno", "arma");
//        CraftedResource r3 = new CraftedResource("arma", attaks, 3, "lancia", "fa male");
//
//        ArrayList<Resource> resources = new ArrayList<>();
//        resources.add(r);
//        resources.add(r2);
//
//        CraftingCatalog cc = new CraftingCatalog();
//        cc.setResourcesToCraft(resources);
//        cc.setFinalResource(r3);
//
//        //em.persist(attack);
////           em.persist(r);
////           em.detach(r);
////        em.persist(r2);
//        em.persist(r3);
//        em.persist(cc);
////        em.flush();
//        //em.getTransaction().commit();
//
//        System.out.println("crafting catalog" + cc);
//
////        CraftingCatalogueController craftingCatalogueController = new CraftingCatalogueController();
////        ArrayList<Long> list = new ArrayList<>(Arrays.asList(1L,2L));
////        Long idList = craftingCatalogueController.craftingResources(list);
////        System.out.println("idResources " + idList);
////        System.out.println("crafting creato");
////
////
//        tx.commit();
//        em.close();


        //gestione apertura del gioco e caricamento mappa con risorse
        StartController startController= new StartController();
        Game game=startController.start(new Mode(1L));

    }


}