/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Movie;
import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.Ticket;
import CineWorldCinemas.logic.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joela
 */
@Path("/tickets")
public class Tickets {

    @GET
    @PermitAll
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> search() {
        return Service.instance().findAllTickets();
    }

    @GET
    @PermitAll
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket get(@PathParam("id") int id) {
        try {
            return Service.instance().findTicketById(id);
        } catch (Exception ex) {
            throw new NotFoundException(ex);
        }
    }
    
    @GET
    @PermitAll
    @Path("last")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket searchLast() {
        List<Ticket> ticketsList = Service.instance().findAllTickets().stream().
                sorted((Ticket a, Ticket b) -> a.getId() - b.getId()).collect(Collectors.toList());

        return ticketsList.get(ticketsList.size() - 1);
    }

    @PUT
    @Path("creditCard")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Ticket ticket) {  
        try {
            Ticket saveTicket = Service.instance().findTicketById(ticket.getId());
            saveTicket.setCreditCard(ticket.getCreditCard());
            Service.instance().updateTicket(saveTicket);
            
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }

}
