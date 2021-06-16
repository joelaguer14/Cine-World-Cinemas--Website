/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.Ticket;
import java.util.Comparator;
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
@Path("/tickets")
public class Tickets {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> search() {
        return Service.instance().findAllTickets();
    }

    @GET
    @Path("last")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket searchLast() {
        List<Ticket> ticketsList = Service.instance().findAllTickets().stream().
                sorted((Ticket a, Ticket b) -> a.getId() - b.getId()).collect(Collectors.toList());

        return ticketsList.get(ticketsList.size()-1);
    }
    
}
