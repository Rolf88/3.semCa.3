package facades;

import entity.CurrencyRate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class CurrencyFacade {

    private final EntityManager entityManager;

    public CurrencyFacade(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(List<CurrencyRate> currencies) {
        this.entityManager.getTransaction().begin();

        for (CurrencyRate currency : currencies) {
            this.entityManager.persist(currency);
        }

        this.entityManager.getTransaction().commit();
    }
}
