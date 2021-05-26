/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.Movie;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class MovieDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Movie findById(int id) {
        session.clear();
        Movie movie = (Movie) session.find(Movie.class, id);
        session.refresh(movie);
        return movie;
    }

    public Movie save(Movie movie) {
        session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();
        session.refresh(movie);
        return movie;
    }

    public Movie update(Movie movie) {
        session.beginTransaction();
        session.update(movie);
        session.getTransaction().commit();
        session.refresh(movie);
        return movie;
    }

    @SuppressWarnings("unchecked")
    public List<Movie> findAll() {
        session.clear();
        return session.createQuery("from Movie").getResultList();
    }

    public void deleteById(int id) {
        final Movie movie = findById(id);
        delete(movie);
    }

    public void delete(Movie movie) {
        session.beginTransaction();
        session.delete(movie);
        session.getTransaction().commit();
        session.refresh(movie);
    }
}
