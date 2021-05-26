/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.data;

import CineWorldCinemas.logic.Client;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author felig
 */
public class ClientDAO {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public Client findById(String id) {
        session.clear();
        Client client = (Client) session.find(Client.class, id);
        session.refresh(client);
        return client;
    }

    public Client save(Client client) {
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.refresh(client);
        return client;
    }

    public Client update(Client client) {
        session.beginTransaction();
        session.update(client);
        session.getTransaction().commit();
        session.refresh(client);
        return client;
    }

    @SuppressWarnings("unchecked")
    public List<Client> findAll() {
        session.clear();
        return session.createQuery("from Client").getResultList();
    }

    public void deleteById(String id) {
        final Client client = findById(id);
        delete(client);
    }

    public void delete(Client client) {
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        session.refresh(client);
    }
}
