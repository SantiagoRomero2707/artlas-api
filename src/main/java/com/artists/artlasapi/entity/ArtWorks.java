package com.artists.artlasapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "obras", schema = "domain")
public class ArtWorks {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_obra", nullable = false)
    private int idWorksArts;

    @Basic
    @Column(name = "nombre", nullable = false, length = 20)
    private String name;

    @Basic
    @Column(name = "pais", nullable = false, length = 30)
    private String country;

    @Basic
    @Column(name = "fk_id_artista", nullable = false)
    private int fkIdArtist;

    @ManyToOne
    @JoinColumn(name = "fk_id_artista", referencedColumnName = "id_artista", nullable = false, insertable = false, updatable = false)
    private Artist artistByFkIdArtist;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtWorks that)) return false;
        return getIdWorksArts() == that.getIdWorksArts() && getFkIdArtist() == that.getFkIdArtist() && getName().equals(that.getName()) && getCountry().equals(that.getCountry()) && getArtistByFkIdArtist().equals(that.getArtistByFkIdArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdWorksArts(), getName(), getCountry(), getFkIdArtist(), getArtistByFkIdArtist());
    }
}
