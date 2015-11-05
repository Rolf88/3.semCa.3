package facades;

import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import rest.exceptions.DataAllreadyExistException;
import security.PasswordHash;

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

    public List<String> authenticateUser(String userName, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = getUserByUserName(userName);
        return user != null && PasswordHash.validatePassword(password, user.getPassword()) ? user.getRoles() : null;
    }

    public User createUser(User user) throws DataAllreadyExistException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (user == null) {
            throw new NullPointerException("User cannot be null");
        }

        user.addRole("User");
        user.setPassword(PasswordHash.createHash(user.getPassword()));
        User oldUser = getUserByUserName(user.getUserName());
        if (oldUser != null) {
            throw new DataAllreadyExistException("Username already in use");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();

        return user;
    }

    public void deleteUser(User user) {
        if (user == null) {
            throw new NullPointerException("user cannot be null");
        }

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(user);
        this.entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
    }
}
