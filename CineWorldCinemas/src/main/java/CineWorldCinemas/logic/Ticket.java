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
@Table(name = "tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne()
    @JoinColumn(name = "id_screening", nullable = false)
    private Screening screening;
    
    @ManyToOne()
    @JoinColumn(name = "id_client", nullable = true)
    private Client client;
    
    @Column(name = "total_price")
    private float totalPrice;
    
    @OneToMany(mappedBy = "ticket")
    private List<SeatReserved> seatsReservedList;

    public Ticket() {
        this.seatsReservedList = new ArrayList<>();
    }

    public Ticket(int id, Screening screening, Client client, float totalPrice) {
        this.id = id;
        this.screening = screening;
        this.client = client;
        this.totalPrice = totalPrice;
        this.seatsReservedList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SeatReserved> getSeatsReservedList() {
        return seatsReservedList;
    }

    public void setSeatsReservedList(List<SeatReserved> seatsReservedList) {
        this.seatsReservedList = seatsReservedList;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", screening=" + screening + ", client=" + client + ", totalPrice=" + totalPrice + ", seatsReservedList=" + seatsReservedList + '}';
    }
}
