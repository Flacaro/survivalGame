package org.example;

import Controller.StartController;
import org.example.model.*;
import org.example.persistence.EntityManagerSingleton;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        var em = EntityManagerSingleton.getEntityManager();



        var tx = em.getTransaction();

        tx.begin();
//        Attack attack = new Attack("boom!", 10.0, "boom");
//
//        em.persist(attack);
//        System.out.println("Persisting attack: " + attack);

        ArrayList<Attack> attaks = new ArrayList<>();
        Attack attack = new Attack("boom!", 10.0, "boom");
        Attack attack1 = new Attack("boom!", 15.0, "boom");

        attaks.add(attack);
        attaks.add(attack1);
        Resource r = new Resource("cibo", null, 1, "acqua", "cibo");
        Resource r2 = new Resource("arma", attaks, 1, "legno", "arma");
        CraftedResource r3 = new CraftedResource("arma", attaks, 3, "lancia", "fa male");

        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(r);
        resources.add(r2);

        CraftingCatalog cc = new CraftingCatalog();
        cc.setResourcesToCraft(resources);
        cc.setFinalResource(r3);

        //em.persist(attack);
//           em.persist(r);
//           em.detach(r);
//        em.persist(r2);
//        em.persist(r3);
//        em.persist(cc);
//        em.flush();
        //em.getTransaction().commit();

        System.out.println("crafting catalog" + cc);

//        CraftingCatalogueController craftingCatalogueController = new CraftingCatalogueController();
//        ArrayList<Long> list = new ArrayList<>(Arrays.asList(1L,2L));
//        Long idList = craftingCatalogueController.craftingResources(list);
//        System.out.println("idResources " + idList);
//        System.out.println("crafting creato");

        StartController sc = new StartController();
        Mode m = new Mode(1L);
        sc.start(m);
//
//
        tx.commit();
        em.close();
    }


}