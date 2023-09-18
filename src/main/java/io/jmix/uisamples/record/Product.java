package io.jmix.uisamples.record;

public record Product(String name, int count) {

    public String getDisplayName() {
        return this.name + " (" + this.count + ")";
    }
}
