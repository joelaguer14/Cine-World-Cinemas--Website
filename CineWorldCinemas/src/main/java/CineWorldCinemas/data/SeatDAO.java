/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.Seat;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class SeatDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Seat findById(int id) {
        session.clear();
        Seat seat = (Seat) session.find(Seat.class, id);
        session.refresh(seat);
        return seat;
    }

    public Seat save(Seat seat) {
        session.beginTransaction();
        session.save(seat);
        session.getTransaction().commit();
        session.refresh(seat);
        return seat;
    }

    public Seat update(Seat seat) {
        session.beginTransaction();
        session.update(seat);
        session.getTransaction().commit();
        session.refresh(seat);
        return seat;
    }

    @SuppressWarnings("unchecked")
    public List<Seat> findAll() {
        session.clear();
        return session.createQuery("from Seat").getResultList();
    }

    public void deleteById(int id) {
        final Seat seat = findById(id);
        delete(seat);
    }

    public void delete(Seat seat) {
        session.beginTransaction();
        session.delete(seat);
        session.getTransaction().commit();
        session.refresh(seat);
    }
}
