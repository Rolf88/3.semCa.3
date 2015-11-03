package facades;

import entity.CurrencyRate;
import java.util.List;
import javax.persistence.EntityManager;

public class CurrencyFacade {

    private final EntityManager entityManager;

    public CurrencyFacade(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(List<CurrencyRate> currencies) {
        this.entityManager.getTransaction().commit();

        for (CurrencyRate currency : currencies) {
            this.entityManager.persist(currency);
        }

        this.entityManager.getTransaction().begin();
    }
}
