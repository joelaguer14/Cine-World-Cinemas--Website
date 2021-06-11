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
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 *
 * @author joela
 */
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "price")
    private float price;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "movie")
    private List<Screening> screeningsList;

    public Movie() {
        this.screeningsList = new ArrayList<>();
    }

    public Movie(String title, String description, int duration, float price) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.screeningsList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Screening> getScreeningsList() {
        return screeningsList;
    }

    public void setScreeningsList(List<Screening> screeningsList) {
        this.screeningsList = screeningsList;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", description=" + description + ", duration=" + duration + ", screeningsList=" + screeningsList + '}';
    }
}
