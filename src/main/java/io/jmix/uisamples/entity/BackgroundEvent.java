package io.jmix.uisamples.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.fullcalendarflowui.component.model.Display;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "BACKGROUND_EVENT", indexes = {
        @Index(name = "IDX_BACKGROUND_EVENT_WORKING_SHIFT", columnList = "WORKING_SHIFT_ID")
})
@Entity
public class BackgroundEvent {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "TITLE")
    private String title;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DISPLAY")
    private String display;

    @Column(name = "ALL_DAY")
    private Boolean allDay;

    @JoinColumn(name = "WORKING_SHIFT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkingShift workingShift;

    @Column(name = "CLASS_NAMES")
    private String classNames;

    public String getClassNames() {
        return classNames;
    }

    public void setClassNames(String classNames) {
        this.classNames = classNames;
    }

    public WorkingShift getWorkingShift() {
        return workingShift;
    }

    public void setWorkingShift(WorkingShift workingShift) {
        this.workingShift = workingShift;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    @Nullable
    public Display getDisplay() {
        return display == null ? null : Display.fromId(display);
    }

    public void setDisplay(@Nullable Display display) {
        this.display = display == null ? null : display.getId();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
