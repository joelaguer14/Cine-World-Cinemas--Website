/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joela
 */
public class Screening {

    private int id;
    private Movie movie;
    private Auditorium auditorium;
    private Date screeningStart;
    private List<SeatReserved> seatsReservedList;
    private List<Ticket> ticketsList;

    public Screening() {
        this.seatsReservedList = new ArrayList<>();
        this.ticketsList = new ArrayList<>();
    }

    public Screening(int id, Movie movie, Date screeningStart) {
        this.id = id;
        this.movie = movie;
        this.screeningStart = screeningStart;
        this.seatsReservedList = new ArrayList<>();
        this.ticketsList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Date getScreeningStart() {
        return screeningStart;
    }

    public void setScreeningStart(Date screeningStart) {
        this.screeningStart = screeningStart;
    }

    public List<SeatReserved> getSeatsReservedList() {
        return seatsReservedList;
    }

    public void setSeatsReservedList(List<SeatReserved> seatsReservedList) {
        this.seatsReservedList = seatsReservedList;
    }

    public List<Ticket> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }

    @Override
    public String toString() {
        return "Screening{" + "id=" + id + ", movie=" + movie + ", auditorium=" + auditorium + ", screeningStart=" + screeningStart + ", seatsReservedList=" + seatsReservedList + ", ticketsList=" + ticketsList + '}';
    }
}
