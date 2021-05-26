/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.Auditorium;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class AuditoriumDAO {
    
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Auditorium findById(int id) {
        session.clear();
        Auditorium auditorium = (Auditorium) session.find(Auditorium.class, id);
        session.refresh(auditorium);
        return auditorium;
    }

    public Auditorium save(Auditorium auditorium) {
        session.beginTransaction();
        session.save(auditorium);
        session.getTransaction().commit();
        session.refresh(auditorium);
        return auditorium;
    }

    public Auditorium update(Auditorium auditorium) {
        session.beginTransaction();
        session.update(auditorium);
        session.getTransaction().commit();
        session.refresh(auditorium);
        return auditorium;
    }

    @SuppressWarnings("unchecked")
    public List<Auditorium> findAll() {
        session.clear();
        return session.createQuery("from Auditorium").getResultList();
    }

    public void deleteById(int id) {
        final Auditorium auditorium = findById(id);
        delete(auditorium);
    }

    public void delete(Auditorium auditorium) {
        session.beginTransaction();
        session.delete(auditorium);
        session.getTransaction().commit();
        session.refresh(auditorium);
    }
}
