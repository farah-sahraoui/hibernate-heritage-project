package com.example.model.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("BIKE")
public class Moto extends Vehicule {

    @NotNull(message = "La puissance moteur (cc) est requise")
    @Min(value = 50, message = "Minimum autorisé : 50 cc")
    @Max(value = 2000, message = "Maximum autorisé : 2000 cc")
    private Integer cylindree;

    private String typeTransmission;

    public Moto() {
    }

    public Moto(String marque, String modele, LocalDate anneeFabrication, Double prix,
                Integer cylindree, String typeTransmission) {
        super(marque, modele, anneeFabrication, prix);
        this.cylindree = cylindree;
        this.typeTransmission = typeTransmission;
    }

    public Integer getCylindree() {
        return cylindree;
    }

    public void setCylindree(Integer cylindree) {
        this.cylindree = cylindree;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n--- Détails Moto ---" +
                "\nCylindrée : " + cylindree + " cc" +
                "\nTransmission : " + typeTransmission;
    }
}