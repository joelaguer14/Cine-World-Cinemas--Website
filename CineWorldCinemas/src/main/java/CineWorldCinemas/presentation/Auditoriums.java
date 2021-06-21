/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Auditorium;
import CineWorldCinemas.logic.Service;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joela
 */
@Path("/auditoriums")
public class Auditoriums {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Auditorium> findAll() {
        return Service.instance().findAllAuditoriums();
    }
}
