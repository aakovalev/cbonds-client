package io.github.aakovalev.cbonds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Field {
    @JsonProperty(value = "field")
    private final String name;

    public Field(String name) {
        this.name = name;
    }
}
