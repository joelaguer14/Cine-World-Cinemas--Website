/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.filter.RestfulFilter;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author joela
 */
@ApplicationPath("api")
public class CinemaApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(MultiPartFeature.class);
        classes.add(Movies.class);
        classes.add(Register.class);
        classes.add(Login.class);
        classes.add(Auditoriums.class);
        classes.add(Tickets.class);
        classes.add(Users.class);
        classes.add(Screenings.class);
        classes.add(RestfulFilter.class);

        return classes;
    }
}
