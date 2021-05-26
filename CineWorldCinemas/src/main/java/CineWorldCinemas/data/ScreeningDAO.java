/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.Screening;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class ScreeningDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Screening findById(int id) {
        session.clear();
        Screening screening = (Screening) session.find(Screening.class, id);
        session.refresh(screening);
        return screening;
    }

    public Screening save(Screening screening) {
        session.beginTransaction();
        session.save(screening);
        session.getTransaction().commit();
        session.refresh(screening);
        return screening;
    }

    public Screening update(Screening screening) {
        session.beginTransaction();
        session.update(screening);
        session.getTransaction().commit();
        session.refresh(screening);
        return screening;
    }

    @SuppressWarnings("unchecked")
    public List<Screening> findAll() {
        session.clear();
        return session.createQuery("from Screening").getResultList();
    }

    public void deleteById(int id) {
        final Screening screening = findById(id);
        delete(screening);
    }

    public void delete(Screening screening) {
        session.beginTransaction();
        session.delete(screening);
        session.getTransaction().commit();
        session.refresh(screening);
    }
}
