/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Auditorium;
import CineWorldCinemas.logic.Movie;
import CineWorldCinemas.logic.Screening;
import CineWorldCinemas.logic.Seat;
import CineWorldCinemas.logic.SeatReserved;
import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.Ticket;
import CineWorldCinemas.logic.User;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author felig
 */
@Path("/register")
public class Register {

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerUser(User user) throws Exception {
        Service.instance().saveUser(user);

    }

    @POST
    @RolesAllowed({"true"})
    @Path("auditorium")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerAuditorium(Auditorium auditorium) throws Exception {
        Auditorium auditoriumDB = Service.instance().saveAuditorium(auditorium);
        int rows = auditorium.getSeatsNumber() / 10;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 10; j++) {
                Seat seat = new Seat(i, j, auditoriumDB);
                Service.instance().saveSeat(seat);
            }
        }
    }

    @POST
    @RolesAllowed({"true"})
    @Path("movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerMovie(Movie movie) throws Exception {
        Service.instance().saveMovie(movie);
    }

    @POST
    @RolesAllowed({"true"})
    @Path("screening")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerScreening(Screening screening) throws Exception {
        Service.instance().saveScreening(screening);
    }
    
    @POST
    @RolesAllowed({"false"})
    @Path("ticket")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerTicket(Ticket ticket) throws Exception {
        Service.instance().saveTicket(ticket);
    }
    
    @POST
    @RolesAllowed({"false"})
    @Path("seatReserved")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerSeatReserved(SeatReserved seatR) throws Exception {
        Service.instance().saveSeatReserved(seatR);
    }
}
