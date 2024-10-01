package io.jmix.uisamples.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "TIP_INFO")
@Entity
@JmixEntity
public class TipInfo {

    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    @Id
    private UUID id;

    @Column(name = "TOTAL_BILL", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalBill;

    @Column(name = "TIP", nullable = false, precision = 19, scale = 2)
    private BigDecimal tip;

    @Column(name = "SMOKER", nullable = false)
    private Boolean smoker = false;

    @Column(name = "SIZE_", nullable = false)
    private Integer size;

    @Column(name = "SEX", nullable = false)
    private String sex;

    @Column(name = "DAY_", nullable = false)
    private String day;

    @Column(name = "TIME_", nullable = false)
    private String time;

    public Time getTime() {
        return time == null ? null : Time.fromId(time);
    }

    public void setTime(Time time) {
        this.time = time == null ? null : time.getId();
    }

    public Day getDay() {
        return day == null ? null : Day.fromId(day);
    }

    public void setDay(Day day) {
        this.day = day == null ? null : day.getId();
    }

    public Sex getSex() {
        return sex == null ? null : Sex.fromId(sex);
    }

    public void setSex(Sex sex) {
        this.sex = sex == null ? null : sex.getId();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
