package io.jmix.uisamples.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "COUNTRY_GROWTH")
@Entity
@JmixEntity
public class CountryGrowth {

    @Id
    @Column(name = "ID")
    @JmixGeneratedValue
    protected UUID id;

    @InstanceName
    @Column(name = "COUNTRY", nullable = false)
    protected String country;

    @Column(name = "FLAG")
    private String flag;

    @Column(name = "PREV_YEAR", nullable = false)
    protected Double prevYear;

    @Column(name = "CURR_YEAR", nullable = false)
    protected Double currYear;

    public CountryGrowth() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getPrevYear() {
        return prevYear;
    }

    public void setPrevYear(Double prevYear) {
        this.prevYear = prevYear;
    }

    public Double getCurrYear() {
        return currYear;
    }

    public void setCurrYear(Double currYear) {
        this.currYear = currYear;
    }
}
