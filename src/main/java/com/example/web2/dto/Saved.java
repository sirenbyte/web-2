package com.example.web2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "saved")
public class Saved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "seq")
    @JsonIgnore
    private Long id;

    @Column(length = 5000)
    private String line;

    private String date;

    public Saved(String line, String date) {
        this.line = line;
        this.date = date;
    }

    public Saved() {

    }
}
