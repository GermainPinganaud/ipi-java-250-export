package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Alexandre on 09/04/2018.
 */
@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //???
    private Set<LigneFacture> lignes;

    public double getTotal() {
        //TODO???
        return 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<LigneFacture> getLignes() {
        return lignes;
    }

    public void setLignes(Set<LigneFacture> lignes) {
        this.lignes = lignes;
    }


}