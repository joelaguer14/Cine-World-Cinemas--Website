/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author joela
 */
@Entity
@Table(name = "administrators")
public class Administrator implements Serializable {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "varchar(64)")
    private String id;
    
    @Column(name = "name")
    private String name;

    public Administrator() {
    }

    public Administrator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Administrator{" + "id=" + id + ", name=" + name + '}';
    }
}
