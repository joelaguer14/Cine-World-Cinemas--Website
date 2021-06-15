/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 *
 * @author joela
 */
@Entity
@Table(name = "seats_reserved")
public class SeatReserved implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "id_seat", nullable = false)
    private Seat seat;

    @ManyToOne()
    @JoinColumn(name = "id_ticket", nullable = true)
    private Ticket ticket;
    
    @ManyToOne()
    @JoinColumn(name = "id_screening", nullable = true)
    private Screening screening;
    
    @Column(name = "screening_int")
    private int screeningId;

    public SeatReserved() {
        seat = new Seat();
        ticket = new Ticket();
        screening = new Screening();
    }

    public SeatReserved(Seat seat, Ticket ticket, Screening screening) {
        this.seat = seat;
        this.ticket = ticket;
        this.screening = screening;
        this.screeningId = this.screening.getId();
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonbTransient
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @JsonbTransient
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @JsonbTransient
    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    @Override
    public String toString() {
        return "SeatReserved{" + "id=" + id + ", seat=" + seat + ", ticket=" + ticket + ", screening=" + screening + '}';
    }
}
