package com.artists.artlasapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "museo", schema = "domain")
public class Museum {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_museo", nullable = false)
    private int idMuseums;

    @Basic
    @Column(name = "nombre", nullable = false, length = 30)
    private String name;

    @Basic
    @Column(name = "pais", nullable = false, length = 30)
    private String country;

    @Basic
    @Column(name = "ciudad", nullable = false, length = 30)
    private String city;

    @Basic
    @Column(name = "direccion", nullable = false, length = 30)
    private String address;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "museos_x_obras",
            joinColumns = @JoinColumn(name = "id_museo"),
            inverseJoinColumns = @JoinColumn(name = "id_obra")
    )

    @Builder.Default
    private List<ArtWorks> employees = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Museum that)) return false;
        return getIdMuseums() == that.getIdMuseums() && getName().equals(that.getName()) && getCountry().equals(that.getCountry()) && getCity().equals(that.getCity()) && getAddress().equals(that.getAddress()) && getEmployees().equals(that.getEmployees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMuseums(), getName(), getCountry(), getCity(), getAddress(), getEmployees());
    }
}
