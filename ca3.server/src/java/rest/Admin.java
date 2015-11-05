package rest;

import entity.User;
import facades.EntityFactory;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import rest.exceptions.InvalidDataException;
import rest.jsonconverter.JSONConverter;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
                + "\"serverTime\": \"" + now + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("users")
    public Response getAllUsers() throws InvalidDataException {
        UserFacade facade = new UserFacade(EntityFactory.getInstance());
        List<User> u = facade.getUsers();

        if (u.isEmpty()) {
            throw new InvalidDataException("No users found");
        }

        return Response.ok(JSONConverter.userToJson(u)).build();
    }

    @DELETE
    @Path("user/{id}")
    public void removeUserById(@PathParam("id") Integer id) {
        UserFacade facade = new UserFacade(EntityFactory.getInstance());
        User u = facade.getUserByUserId(id.toString());
        facade.deleteUser(u);
    }
}
