package io.jmix.flowuisampler.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

@JmixEntity
@Table(name = "SAMPLER_CITY", indexes = {
        @Index(name = "IDX_SAMPLER_CITY_COUNTRY", columnList = "COUNTRY_ID")
})
@Entity
public class City {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @JoinColumn(name = "COUNTRY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}