package com.artists.artlasapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "artistas", schema = "domain")
public class Artist {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_artista", nullable = false)
    private int idArtists;

    @Basic
    @Column(name = "tipo_ide", nullable = false, length = 5)
    private  String typeIDE;

    @Basic
    @Column(name = "numero_ide", nullable = false, length = 20)
    private  String numberIDE;

    @Basic
    @Column(name = "nombres", nullable = false, length = 30)
    private  String name;

    @Basic
    @Column(name = "apellidos", nullable = false, length = 30)
    private  String lastName;

    public Artist(ArtistBuilder builder){
        this.idArtists = builder.idArtists;
        this.typeIDE = builder.typeIDE;
        this.numberIDE = builder.numberIDE;
        this.name = builder.name;
        this.lastName = builder.lastName;
    }

    public Artist() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist that)) return false;
        return getIdArtists() == that.getIdArtists() && getTypeIDE().equals(that.getTypeIDE()) && getNumberIDE().equals(that.getNumberIDE()) && getName().equals(that.getName()) && getLastName().equals(that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdArtists(), getTypeIDE(), getNumberIDE(), getName(), getLastName());
    }

    public static class ArtistBuilder {
        private int idArtists;
        private String typeIDE;
        private String numberIDE;
        private String name;
        private String lastName;

        public ArtistBuilder setIdArtists(int idArtists) {
            this.idArtists = idArtists;
            return this;
        }

        public ArtistBuilder setTypeIDE(String typeIDE) {
            this.typeIDE = typeIDE;
            return this;
        }

        public ArtistBuilder setNumberIDE(String numberIDE) {
            this.numberIDE = numberIDE;
            return this;
        }

        public ArtistBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ArtistBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public String toString() {
            return "Artist.ArtistBuilder(idArtists=" + this.idArtists + ", typeIDE=" + this.typeIDE + ", numberIDE=" + this.numberIDE + ", name=" + this.name + ", lastName=" + this.lastName + ")";
        }

        public Artist build() {
            return new Artist(this);
        }
    }
}