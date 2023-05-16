package com.example.sandbox.util.body.pet;

import com.example.sandbox.util.swagger.definitions.PetBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostCreatePet extends  JsonBody{

        @JsonProperty
        private PetBody PetBody;


}
