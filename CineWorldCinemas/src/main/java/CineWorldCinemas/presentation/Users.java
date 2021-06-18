/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.Ticket;
import CineWorldCinemas.logic.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joela
 */
@Path("/users")
public class Users {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> search() {
        return Service.instance().findAllUsers();
    }

    @GET
    @Path("last")
    @Produces({MediaType.APPLICATION_JSON})
    public User searchLast() {
        List<User> usersList = Service.instance().findAllUsers();

        return usersList.get(usersList.size()-1);
    }
}
