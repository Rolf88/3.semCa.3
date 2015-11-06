/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import rest.exceptions.DataAllreadyExistException;

public class UserFacadeTest {

    UserFacade userFacade;

    @Before
    public void setUp() {
        userFacade = new UserFacade(Persistence.createEntityManagerFactory("ca3.serverPU-test"));
    }

    @Test
    public void testGetUserByUserId() {
        User user = userFacade.getUserByUserId("1");

        assertNotNull(user);
    }

    @Test
    public void testGetUsers() {
        List<User> users = userFacade.getUsers();

        assertFalse(users.isEmpty());
    }

    @Test
    public void testGetUserByUserName() {
        User user = userFacade.getUserByUserName("test");

        assertNotNull(user);
        assertEquals("test", user.getUserName());
    }

    @Test
    public void testAuthenticateUser_valid() throws Exception {
        List<String> roles = userFacade.authenticateUser("test", "test");

        assertFalse(roles.isEmpty());
    }

    @Test
    public void testAuthenticateUser_invalid() throws Exception {
        List<String> roles = userFacade.authenticateUser("test", "testasd");

        assertNull(roles);
    }

    @Test
    public void testCreateUser() throws Exception {
        List<String> roles = new ArrayList<>();
        roles.add("admin");

        int usersBefore = userFacade.getUsers().size();
        userFacade.createUser(new User("password", "createUser", roles));

        assertEquals(usersBefore + 1, userFacade.getUsers().size());
    }

    @Test
    public void testDeleteUser() throws DataAllreadyExistException, NoSuchAlgorithmException, InvalidKeySpecException {
        List<String> roles = new ArrayList<>();
        roles.add("admin");

        userFacade.createUser(new User("password", "deleteUser", roles));
      
        int usersBefore = userFacade.getUsers().size();
        userFacade.deleteUser(userFacade.getUserByUserName("deleteUser"));

        assertEquals(usersBefore - 1, userFacade.getUsers().size());
    }

}
