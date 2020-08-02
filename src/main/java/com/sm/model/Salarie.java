package com.sm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Salarie {
    private static final long serialVersionUID = -637582897899375251L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "salno")
    private String salno;

    @Column(name = "salname")
    private String salname;

    @Column(name = "position")
    private String position;

    public Salarie() {
    }
}
