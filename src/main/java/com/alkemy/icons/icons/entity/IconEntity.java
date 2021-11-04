package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "icon")
@SQLDelete(sql = "UPDATE icon SET deleted = true WHERE id=?") //This query provide the soft delete, as an update over icon
@Where(clause = "deleted=false")
@Getter
@Setter
public class IconEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String name;

    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate created;

    private Long height;

    private String history;

    @ManyToMany(mappedBy = "icons", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CountryEntity> countries = new ArrayList<>();

    //Soft delete
    private Boolean deleted = Boolean.FALSE;

    //Add and remove countries
    public void addCountry(CountryEntity country) {
        this.countries.add(country);
    }

    public void removeCountry(CountryEntity country) {
        this.countries.add(country);
    }

}
