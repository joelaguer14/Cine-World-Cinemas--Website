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
@Table(name = "auditoriums")
public class Auditorium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "seat_number")
    private int seatsNumber;

    @OneToMany(mappedBy = "auditorium")
    private List<Screening> screeningsList;
    
    @OneToMany(mappedBy = "auditorium")
    private List<Seat> seatsList;

    public Auditorium() {
        this.screeningsList = new ArrayList<>();
        this.seatsList = new ArrayList<>();
    }

    public Auditorium(int id, String name, int seatsNumber) {
        this.id = id;
        this.name = name;
        this.seatsNumber = seatsNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public List<Screening> getScreeningsList() {
        return screeningsList;
    }

    public void setScreeningsList(List<Screening> screeningsList) {
        this.screeningsList = screeningsList;
    }

    public List<Seat> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<Seat> seatsList) {
        this.seatsList = seatsList;
    }

    @Override
    public String toString() {
        return "Auditorium{" + "id=" + id + ", name=" + name + ", seatsNumber=" + seatsNumber + ", screeningsList=" + screeningsList + ", seatsList=" + seatsList + '}';
    }
}