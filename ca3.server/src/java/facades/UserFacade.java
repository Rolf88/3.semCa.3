package facades;

import entity.User;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class UserFacade {

    private final EntityManager entityManager;

    public UserFacade(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    public User getUserByUserId(String id) {
        return this.entityManager.find(User.class, Long.parseLong(id));
    }

    public List<User> getUsers() {
        Query createQuery = this.entityManager.createQuery("SELECT u FROM User u");

        return createQuery.getResultList();
    }

    public User getUserByUserName(String userName) {
        Query createQuery = this.entityManager.createQuery("SELECT u FROM User u WHERE u.userName = :userName");
        createQuery.setParameter("userName", userName);

        return (User) createQuery.getSingleResult();
    }

    public List<String> authenticateUser(String userName, String password) {
        User user = getUserByUserName(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

}
