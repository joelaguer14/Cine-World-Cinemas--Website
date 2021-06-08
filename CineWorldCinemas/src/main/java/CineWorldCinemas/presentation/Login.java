/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author felig
 */
@Path("/login")
public class Login {

    @Context
    HttpServletRequest request;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public User login(User user) {
        try {
            User userDB = Service.instance().findUserById(user.getId());
            if (!userDB.getPassword().equals(user.getPassword())) {
                throw new Exception("Incorrect Password");
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userDB);
            return userDB;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public User getUser() {
        HttpSession session = request.getSession(true);
        return (User) session.getAttribute("user");
    }

    @DELETE
    public void logout() {
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");
        session.invalidate();
    }
}
