package org.codetechn.auth.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name",nullable = false,length = 50,unique = true)
    private String name;

    public  Role(){

    }
    public Role( int id){
        this.id=id;
    }
    @Override
    public String toString() {
        return this.name;
    }

    public Role(String name) {
        this.name = name;
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

    public void setName(String email) {
        this.name = email;
    }

}
