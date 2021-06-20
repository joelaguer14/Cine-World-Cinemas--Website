
import CineWorldCinemas.logic.Movie;
import CineWorldCinemas.logic.Service;
import CineWorldCinemas.logic.User;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            service.saveUser(new User("111", "Niki", "niki@outlook.com", "111", true));
            service.saveUser(new User("222", "Alonso", "alonso@outlook.com", "222", true));
            service.saveUser(new User("333", "Joel", "joel@outlook.com", "333", true));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(service.findAllUsers());

    }

}
