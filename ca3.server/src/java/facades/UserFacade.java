package facades;

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

        try {
            return (User) createQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> authenticateUser(String userName, String password) {
        User user = getUserByUserName(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

    public User createUser(User user) {
        if (user == null) {
            throw new NullPointerException("User cannot be null");
        }

        user.addRole("User");

        User oldUser = getUserByUserName(user.getUserName());
        if (oldUser != null) {
            throw new NullPointerException("Username already in use");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();

        return user;
    }

    public void close() {
        this.entityManager.close();
    }
}
