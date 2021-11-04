package com.alkemy.icons.icons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
@SQLDelete(sql = "UPDATE country SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String name;

    @Column(name = "inhabitants_num")
    private Long inhabitantsNumber;
    private Long surfaceArea; //m2

    //Search for info inside Continent Entity to set lists
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id", insertable = false, updatable = false)
    private ContinentEntity continent;

    @Column(name = "continent_id", nullable = false)
    private Long continentId;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}) //genera tabla intermedia para relacionar tablas
    @JoinTable(name = "icon_country",
    joinColumns = @JoinColumn(name = "country_id"),
    inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<IconEntity> icons = new HashSet<>();

    //Add icons to countries
    public void addIcon(IconEntity icon) {
        this.icons.add(icon);
    }

    //Delete icons from countries
    public void deleteIcon(IconEntity icon) {
        this.icons.remove(icon);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final CountryEntity other = (CountryEntity) obj;
        return other.id == this.id;
    }
}
