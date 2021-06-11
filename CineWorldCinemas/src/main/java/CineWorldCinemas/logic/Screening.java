/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 *
 * @author joela
 */
@Entity
@Table(name = "screenings")
public class Screening implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "id_movie", nullable = false)
    private Movie movie;
    
    @ManyToOne()
    @JoinColumn(name = "id_auditorium", nullable = false)
    private Auditorium auditorium;

    @Column(name = "screeningStart")
    @Temporal(TemporalType.DATE)
    private Date screeningStart;

    @OneToMany(mappedBy = "screening")
    private List<SeatReserved> seatsReservedList;

    @OneToMany(mappedBy = "screening")
    private List<Ticket> ticketsList;

    public Screening() {
        this.auditorium = new Auditorium();
        this.movie = new Movie();
        this.seatsReservedList = new ArrayList<>();
        this.ticketsList = new ArrayList<>();
    }

    public Screening(Movie movie, Date screeningStart) {
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

    @JsonbTransient
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @JsonbTransient
    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    @JsonbTransient
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
