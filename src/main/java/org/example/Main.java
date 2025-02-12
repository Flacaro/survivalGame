package org.example;


import org.example.model.Attack;
import org.example.persistence.EntityManagerSingleton;

public class Main {


    public static void main(String[] args) {
        var em = EntityManagerSingleton.getEntityManager();



        var tx = em.getTransaction();

        tx.begin();
        Attack attack = new Attack("boom!", 10.0, "boom");

        em.persist(attack);
        System.out.println("Persisting attack: " + attack);

        tx.commit();
        em.close();
    }


}