package com.example;

import com.example.model.joined.Developpeur;
import com.example.model.joined.Employe;
import com.example.model.joined.Manager;
import com.example.model.singletable.Moto;
import com.example.model.singletable.Vehicule;
import com.example.model.singletable.Voiture;
import com.example.model.tableperclass.Electronique;
import com.example.model.tableperclass.Livre;
import com.example.model.tableperclass.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {

        System.out.println("=== Initialisation du projet Héritage Hibernate ===");

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate-inheritance");

        try {

            System.out.println("\n>>> Démonstration stratégie SINGLE_TABLE");
            testSingleTable(emf);

            System.out.println("\n>>> Démonstration stratégie JOINED");
            testJoined(emf);

            System.out.println("\n>>> Démonstration stratégie TABLE_PER_CLASS");
            testTablePerClass(emf);

        } finally {
            emf.close();
            System.out.println("\n=== Fin des tests ===");
        }
    }

    // ================= SINGLE_TABLE =================
    private static void testSingleTable(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            System.out.println("Insertion de véhicules dans la base...");

            Voiture v1 = new Voiture(
                    "Toyota", "Corolla", LocalDate.of(2022, 4, 10), 21000.0,
                    4, true, "Hybride"
            );

            Voiture v2 = new Voiture(
                    "BMW", "Serie 3", LocalDate.of(2021, 2, 18), 35000.0,
                    4, true, "Essence"
            );

            Moto m1 = new Moto(
                    "Yamaha", "MT-07", LocalDate.of(2023, 6, 5), 9000.0,
                    700, "Manuelle"
            );

            em.persist(v1);
            em.persist(v2);
            em.persist(m1);

            em.getTransaction().commit();
            System.out.println("✔ Véhicules enregistrés.");

            em.clear();

            System.out.println("\nListe complète des véhicules :");
            List<Vehicule> vehicules =
                    em.createQuery("SELECT v FROM Vehicule v", Vehicule.class)
                            .getResultList();
            vehicules.forEach(System.out::println);

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ================= JOINED =================
    private static void testJoined(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            System.out.println("Insertion du personnel...");

            Developpeur d1 = new Developpeur(
                    "Benali", "Amine", "amine.dev@mail.com",
                    LocalDate.of(2020, 1, 12),
                    "Java", "Microservices", 4
            );

            Developpeur d2 = new Developpeur(
                    "Rahimi", "Sara", "sara.front@mail.com",
                    LocalDate.of(2021, 3, 20),
                    "Angular", "Frontend", 3
            );

            Manager m1 = new Manager(
                    "Haddad", "Karim", "karim.manager@mail.com",
                    LocalDate.of(2018, 9, 5),
                    "Technologie", 8, 7500.0
            );

            em.persist(d1);
            em.persist(d2);
            em.persist(m1);

            em.getTransaction().commit();
            System.out.println("✔ Personnel enregistré.");

            em.clear();

            System.out.println("\nListe globale du personnel :");
            List<Employe> employes =
                    em.createQuery("SELECT e FROM Employe e", Employe.class)
                            .getResultList();
            employes.forEach(System.out::println);

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ================= TABLE_PER_CLASS =================
    private static void testTablePerClass(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            System.out.println("Insertion des articles catalogue...");

            Livre l1 = new Livre(
                    "Clean Code", 32.50,
                    "Guide des bonnes pratiques de développement",
                    "Robert C. Martin",
                    "9780132350884",
                    464,
                    "Prentice Hall"
            );

            Livre l2 = new Livre(
                    "Design Patterns", 40.00,
                    "Référence en architecture logicielle",
                    "GoF",
                    "9780201633610",
                    395,
                    "Addison-Wesley"
            );

            Electronique e1 = new Electronique(
                    "Laptop Pro 15", 1299.99,
                    "Ordinateur portable haute performance",
                    "Dell",
                    "XPS 15",
                    24,
                    "16GB RAM, 512GB SSD"
            );

            em.persist(l1);
            em.persist(l2);
            em.persist(e1);

            em.getTransaction().commit();
            System.out.println("✔ Articles enregistrés.");

            em.clear();

            System.out.println("\nInventaire complet :");
            List<Produit> produits =
                    em.createQuery("SELECT p FROM Produit p", Produit.class)
                            .getResultList();
            produits.forEach(System.out::println);

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}