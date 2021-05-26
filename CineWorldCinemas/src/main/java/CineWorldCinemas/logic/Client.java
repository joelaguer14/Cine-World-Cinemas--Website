/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author joela
 */
@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "varchar(64)")
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Ticket> ticketsList;

    public Client() {
        this.ticketsList = new ArrayList<>();
    }

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
        this.ticketsList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }

}
