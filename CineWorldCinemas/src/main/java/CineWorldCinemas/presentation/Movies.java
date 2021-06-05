/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.presentation;

import CineWorldCinemas.logic.Movie;
import CineWorldCinemas.logic.Service;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/Movies")
public class Movies {
    String location="C:/cinemaImages";
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> search(@DefaultValue("") @QueryParam("nombre") String nombre) { 
        return Service.instance().findMoviesByName(nombre);
    } 
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Movie get(@PathParam("id") int id) {
        try {
            return Service.instance().findMovieById(id);
        } catch (Exception ex) {
            throw new NotFoundException(ex); 
        }
    }
    
    @GET
    @Path("{cedula}/imagen")
    @Produces("image/png")
    public Response getImge(@PathParam("cedula") String cedula) throws IOException {
        File file = new File(location+cedula);
        ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    }    

    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public void add(Movie movie) {  
        try {
            Service.instance().saveMovie(movie);
        } catch (Exception ex) {
            throw new NotAcceptableException(ex); 
        }
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA) 
    @Path("{cedula}/imagen")
    public void addImage(@PathParam("cedula") String cedula, @FormDataParam("imagen") InputStream imagenStream) {  
        try{
                int read = 0;
                byte[] bytes = new byte[1024];

                OutputStream out = new FileOutputStream(new File(location + cedula));
                while ((read = imagenStream.read(bytes)) != -1){out.write(bytes, 0, read);}
                out.flush();
                out.close();
            } catch (Exception ex) {
                throw new NotAcceptableException(ex); 
            }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Movie movie) {  
        try {
            Service.instance().updateMovie(movie);
        } catch (Exception ex) {
            throw new NotFoundException(ex); 
        }
    }
    

    @DELETE
    @Path("{id}")
    public void del(@PathParam("id") int id) {
        try {
            Service.instance().deleteMovieById(id);
        } catch (Exception ex) {
            throw new NotFoundException(ex); 
        }
    }
      
}

