package io.jmix.uisamples.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;

import java.util.UUID;

@JmixEntity
public class Document {

    @JmixGeneratedValue
    @JmixId
    private UUID id;

    @JmixProperty
    private FileRef content;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FileRef getContent() {
        return content;
    }

    public void setContent(FileRef content) {
        this.content = content;
    }
}
