/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.User;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class UserDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public User findById(String id) {
        session.clear();
        User user = (User) session.find(User.class, id);
        session.refresh(user);
        return user;
    }

    public User save(User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.refresh(user);
        return user;
    }

    public User update(User user) {
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.refresh(user);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        session.clear();
        return session.createQuery("from User").getResultList();
    }

    public void deleteById(String id) {
        final User user = findById(id);
        delete(user);
    }

    public void delete(User user) {
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.refresh(user);
    }
}
