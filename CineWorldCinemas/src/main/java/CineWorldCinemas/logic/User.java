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
@Table(name = "persons")
public class User implements Serializable {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "varchar(64)")
    private String id;
    
    @Column(name = "password")
    protected String password;
    
    @Column(name = "type")
    private int type;

    public User() {
    }

    public User(String id, String password, int type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", type=" + type + '}';
    }
}
