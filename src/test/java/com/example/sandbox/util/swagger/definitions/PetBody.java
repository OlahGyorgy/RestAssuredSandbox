package com.example.sandbox.util.swagger.definitions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetBody {

    @JsonProperty
    private int id;

    @JsonProperty
    private Item category;

    @JsonProperty
    private String name;

    @Singular()
    @JsonProperty
    private List<String> photoUrls;

    @Singular()
    @JsonProperty
    private List<Item> tags;

    @JsonProperty
    private String status;
}
