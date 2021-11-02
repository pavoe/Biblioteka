package pl.clockworkjava.hotelreservation.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Year;

public class BookRepository {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em  = factory.createEntityManager();

    public void createNewBook(String tytuł, String autor, Year rokWydania, int dostępne) {
        System.out.println(" ------------ CREATE ------------ ");
        Book newOne = new Book(tytuł, autor, rokWydania, dostępne);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        System.out.println(" ------ Persisting in new transaction ------ ");
        em.persist(newOne);
        System.out.println(" New Book ID " + newOne.getId());

        System.out.println(" ------ Closing transaction ------ ");
        transaction.commit();
    }
}