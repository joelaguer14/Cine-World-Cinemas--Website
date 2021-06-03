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
@Table(name = "persons")
public class User implements Serializable {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "varchar(64)")
    private String id;
    
    @Column(name = "password")
    protected String password;
    
    @Column(name = "administrator")
    private boolean admin;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Ticket> ticketsList;
    
    public User() {
        this.ticketsList = new ArrayList<>();
    }

    public User(String id, String password, boolean admin, String name) {
        this.id = id;
        this.password = password;
        this.admin = admin;
        this.name = name;
        this.ticketsList = new ArrayList<>();
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", admin=" + admin + ", name=" + name + ", ticketsList=" + ticketsList + '}';
    }

    
}
