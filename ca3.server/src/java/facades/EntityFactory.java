package facades;

import deploy.DeploymentConfiguration;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactory {

    private static EntityManagerFactory instance;

    public static EntityManagerFactory getInstance() {
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        }

        return instance;
    }
}
