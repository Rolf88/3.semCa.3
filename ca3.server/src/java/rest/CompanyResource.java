/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author AlexanderSteen
 */
@Path("company")
@RolesAllowed("User")
public class CompanyResource {

    @Context
    private UriInfo context;
    private Gson gson;
    private JsonParser jsonParser;

    public CompanyResource() {
        gson = new Gson();
        jsonParser = new JsonParser();
    }

    @GET
    @Produces("application/json")
    @Path("{search}")
    public Response search(@PathParam("search") String search) throws IOException {
        String json = "";

        try {
            URL url = new URL("http://cvrapi.dk/api?search=" + search + "&country=dk");
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                json += inputLine;
            }
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.ok(json).build();
    }
}
