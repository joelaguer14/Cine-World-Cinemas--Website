/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.Ticket;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class TicketDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Ticket findById(int id) {
        session.clear();
        Ticket ticket = (Ticket) session.find(Ticket.class, id);
        session.refresh(ticket);
        return ticket;
    }

    public Ticket save(Ticket ticket) {
        session.beginTransaction();
        session.save(ticket);
        session.getTransaction().commit();
        session.refresh(ticket);
        return ticket;
    }

    public Ticket update(Ticket ticket) {
        session.beginTransaction();
        session.update(ticket);
        session.getTransaction().commit();
        session.refresh(ticket);
        return ticket;
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> findAll() {
        session.clear();
        return session.createQuery("from Ticket").getResultList();
    }

    public void deleteById(int id) {
        final Ticket ticket = findById(id);
        delete(ticket);
    }

    public void delete(Ticket ticket) {
        session.beginTransaction();
        session.delete(ticket);
        session.getTransaction().commit();
        session.refresh(ticket);
    }
}
