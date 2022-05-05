package com.epinerez.testnexos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,length = 100)
    private String name;
    private Integer edad;
    @Temporal(TemporalType.DATE)
    private Date dateIngress;



    @ManyToOne
    private Charge charge;

    public User(Integer id) {
        this.id = id;
    }




}
