/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joela
 */
public class Ticket {
    
    private int id;
    private Screening screening;
    private Client client;
    private float totalPrice;
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
