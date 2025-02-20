package io.jmix.uisamples.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "MEETING_POINT", indexes = {
        @Index(name = "IDX_MEETING_POINT_TERMINAL", columnList = "TERMINAL_ID")
})
@Entity
public class MeetingPoint {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;
    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "TERMINAL_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Terminal terminal;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}