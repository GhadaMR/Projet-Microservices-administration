package org.acme.model;

//import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "ventes")
public class Vente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;


    @Column(nullable = false)
    @JsonProperty("produit")
    private String produit;

    @Column(nullable = false)
    @JsonProperty("quantite")
    private int quantite;

    @Column(nullable = false)
    @JsonProperty("prix")
    private double prix;

    public Vente() {

    }

    public Vente(double prix, int quantite, String produit) {
        this.prix = prix;
        this.quantite = quantite;
        this.produit = produit;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
