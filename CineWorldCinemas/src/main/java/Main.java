
import CineWorldCinemas.logic.Movie;
import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alonso
 */
public class Main {
      public static void main(String[] args) {
         Service service = Service.instance();
         service.saveMovie(new Movie("Anabelle", "Terror", 120,5.0f));
         service.saveMovie(new Movie("Anabelle2", "Terror", 120,6.0f));
         service.saveMovie(new Movie("Conjuring", "Terror", 120,6.5f));
         
         System.out.println(service.findMoviesByName("Ana"));
         
         
         
         
    }
    
}
