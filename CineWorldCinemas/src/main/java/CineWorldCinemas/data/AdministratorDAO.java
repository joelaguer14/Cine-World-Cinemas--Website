/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.Administrator;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class AdministratorDAO {

    private final Session session = HibernateUtil.getSessionFactory().openSession();

    public Administrator findById(String id) {
        session.clear();
        Administrator admin = (Administrator) session.find(Administrator.class, id);
        session.refresh(admin);
        return admin;
    }

    public Administrator save(Administrator administrator) {
        session.beginTransaction();
        session.save(administrator);
        session.getTransaction().commit();
        session.refresh(administrator);
        return administrator;
    }

    public Administrator update(Administrator administrator) {
        session.beginTransaction();
        session.update(administrator);
        session.getTransaction().commit();
        session.refresh(administrator);
        return administrator;
    }

    @SuppressWarnings("unchecked")
    public List<Administrator> findAll() {
        session.clear();
        return session.createQuery("from Administrator").getResultList();
    }

    public void deleteById(String id) {
        final Administrator administrator = findById(id);
        delete(administrator);
    }

    public void delete(Administrator administrator) {
        session.beginTransaction();
        session.delete(administrator);
        session.getTransaction().commit();
        session.refresh(administrator);
    }
}
