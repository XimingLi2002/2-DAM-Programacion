
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class JPA_Manager implements ConsoleColors {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public JPA_Manager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void insert(Object obj) {
        entityTransaction.begin();
        try {
            if (!(entityManager.createQuery("from " + obj.getClass().getName()).getResultList().contains(obj))) {
                entityManager.persist(obj);
                System.out.println(GREEN_BOLD_BRIGHT + "(" + obj.getClass().getName() + ")" + RESET);
            } else {
                System.out.println(RED_BOLD_BRIGHT + "(" + obj.getClass().getName() + ") INSERT failed because DUPLICATED PRIMARY KEY" + RESET);
            }
        } catch (RuntimeException e) {
            System.out.println(RED_BOLD_BRIGHT + "(" + obj.getClass().getName() + ") (exception) INSERT failed because DUPLICATED PRIMARY KEY" + RESET);
        }
        entityTransaction.commit();

    }

    public <T> List<T> getList(Class<T> entityClass) {
        return entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    public <T, E> Object getEntityById(Class<T> entityClass, E entityId) {
        return entityManager.find(entityClass, entityId);
    }

    public void updateEntity(Object obj) {
        entityTransaction.begin();
        //Solo existe el merge para sobreescribir las entidades sin embargo en caso de que no exista lo va a crear.
        entityManager.merge(obj);
        System.out.println(GREEN_BOLD_BRIGHT + "(" + obj.getClass().getName() + ") UPDATED / CREATED" + RESET);
        entityTransaction.commit();
    }

    public <T, E> void removeEntityById(Class<T> entityClass, E entityId) {
        try {
            entityTransaction.begin();
            entityManager.remove(entityManager.find(entityClass, entityId));
            System.out.println(GREEN_BOLD_BRIGHT + "(" + entityClass.getName() + ")" + " " + entityId + " REMOVED" + RESET);
        } catch (RuntimeException e) {
            System.out.println(RED_BOLD_BRIGHT + "REMOVE failed" + RESET);
        }
        entityTransaction.commit();
    }
    public void removeEntity(Object entity){
        entityTransaction.begin();
        entityManager.remove(entity);
        entityTransaction.commit();
    }

    public <T> Query getQuery(Class<T> entityClass, String query) {
        return entityManager.createQuery(query, entityClass);
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
