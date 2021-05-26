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
@Table(name = "seats")
public class Seat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "row")
    private int row;

    @Column(name = "number")
    private int number;

    @ManyToOne()
    @JoinColumn(name = "id_auditorium", nullable = false)
    private Auditorium auditorium;

    @OneToMany(mappedBy = "seat")
    private List<SeatReserved> seatsReserved;

    public Seat() {
        this.seatsReserved = new ArrayList<>();
    }

    public Seat(int id, int row, int number, Auditorium auditorium) {
        this.id = id;
        this.row = row;
        this.number = number;
        this.auditorium = auditorium;
        this.seatsReserved = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public List<SeatReserved> getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(List<SeatReserved> seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    @Override
    public String toString() {
        return "Seat{" + "id=" + id + ", row=" + row + ", number=" + number + ", auditorium=" + auditorium + ", seatsReserved=" + seatsReserved + '}';
    }
}
