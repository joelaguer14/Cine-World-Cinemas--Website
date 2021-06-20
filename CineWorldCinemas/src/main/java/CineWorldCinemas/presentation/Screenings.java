/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Movie;
import CineWorldCinemas.logic.Screening;
import CineWorldCinemas.logic.Service;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alonso
 */
@Path("/screenings")
public class Screenings {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Screening> search(@DefaultValue("") @QueryParam("nombre") String nombre) {
        return Service.instance().findAllScreenings();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Screening get(@PathParam("id") int id) {
        try {
            return Service.instance().findScreeningById(id);
        } catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
}
