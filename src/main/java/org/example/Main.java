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

        //gestione apertura del gioco e caricamento mappa con risorse
        StartController startController= new StartController();
        Game game=startController.start(new Mode(1L));

    }


}