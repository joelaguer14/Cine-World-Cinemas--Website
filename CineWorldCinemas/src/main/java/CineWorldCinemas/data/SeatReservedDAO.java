/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.SeatReserved;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class SeatReservedDAO {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public SeatReserved findById(int id) {
        session.clear();
        SeatReserved seatReserved = (SeatReserved) session.find(SeatReserved.class, id);
        session.refresh(seatReserved);
        return seatReserved;
    }

    public SeatReserved save(SeatReserved seatReserved) {
        session.beginTransaction();
        session.save(seatReserved);
        session.getTransaction().commit();
        session.refresh(seatReserved);
        return seatReserved;
    }

    public SeatReserved update(SeatReserved seatReserved) {
        session.beginTransaction();
        session.update(seatReserved);
        session.getTransaction().commit();
        session.refresh(seatReserved);
        return seatReserved;
    }

    @SuppressWarnings("unchecked")
    public List<SeatReserved> findAll() {
        session.clear();
        return session.createQuery("from SeatReserved").getResultList();
    }

    public void deleteById(int id) {
        final SeatReserved seatReserved = findById(id);
        delete(seatReserved);
    }

    public void delete(SeatReserved seatReserved) {
        session.beginTransaction();
        session.delete(seatReserved);
        session.getTransaction().commit();
        session.refresh(seatReserved);
    }
}
