/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotAcceptableException;
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
    @Consumes(MediaType.APPLICATION_JSON) 
    public void register(User user) throws Exception {
        Service.instance().saveUser(user);
     
    }
}