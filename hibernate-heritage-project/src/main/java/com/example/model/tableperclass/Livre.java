package com.example.model.tableperclass;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "catalogue_livres")
public class Livre extends Produit {

    @NotBlank(message = "Le nom de l’auteur est requis")
    private String auteur;

    @NotBlank(message = "Le code ISBN doit être précisé")
    private String isbn;

    @Positive(message = "Le total de pages doit être positif")
    private Integer nombrePages;

    private String editeur;

    public Livre() {
    }

    public Livre(String nom, Double prix, String description, String auteur,
                 String isbn, Integer nombrePages, String editeur) {
        super(nom, prix, description);
        this.auteur = auteur;
        this.isbn = isbn;
        this.nombrePages = nombrePages;
        this.editeur = editeur;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNombrePages() {
        return nombrePages;
    }

    public void setNombrePages(Integer nombrePages) {
        this.nombrePages = nombrePages;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n--- Détails Livre ---" +
                "\nAuteur : " + auteur +
                "\nISBN : " + isbn +
                "\nPages : " + nombrePages +
                "\nMaison d'édition : " + editeur;
    }
}