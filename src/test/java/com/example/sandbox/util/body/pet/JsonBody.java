package com.example.sandbox.util.body.pet;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class JsonBody {

    public static String createJsonBody(PostCreatePet body){
        try{
            return new ObjectMapper().writeValueAsString(body.getPetBody());

        } catch (Throwable e){
            throw new RuntimeException("Nody Generation Failure");
        }
    }

}
