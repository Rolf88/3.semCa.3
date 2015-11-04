package facades;

import entity.CurrencyRate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

public class CurrencyFacade {
    
    private static final HashMap<String, List<CurrencyRate>> rateCache = new HashMap<>();
    
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
    
    public List<CurrencyRate> find(Date date) {
        date.setDate(date.getDate() - 1);
        
        String cacheKey = date.getDate() + "-" + date.getMonth() + "-" + date.getYear();        
        
        if (!rateCache.containsKey(cacheKey)) {
            Query query = entityManager.createQuery("SELECT c FROM CurrencyRate c WHERE c.timeCreated = :timeCreated");
            query.setParameter("timeCreated", date, TemporalType.DATE);
            
            List<CurrencyRate> result = query.getResultList();
            rateCache.put(cacheKey, result);
            
            return result;
        }
        
        return rateCache.get(cacheKey);
    }
}
