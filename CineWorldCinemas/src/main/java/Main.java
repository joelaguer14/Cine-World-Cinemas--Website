
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
         
         User user1 = new User("111","111",1);
         
         service.saveUser(user1);
         
    }
    
}
