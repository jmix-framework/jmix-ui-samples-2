package io.jmix.flowuisampler.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Embeddable
public class Address {
    @Column(name = "ZIP")
    private String zip;

    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}