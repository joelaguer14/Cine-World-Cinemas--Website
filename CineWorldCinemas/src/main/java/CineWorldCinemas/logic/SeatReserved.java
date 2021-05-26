/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

/**
 *
 * @author joela
 */
public class SeatReserved {

    private int id;
    private Seat seat;
    private Ticket ticket;
    private Screening screening;

    public SeatReserved() {
    }

    public SeatReserved(int id, Seat seat, Ticket ticket, Screening screening) {
        this.id = id;
        this.seat = seat;
        this.ticket = ticket;
        this.screening = screening;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

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
