package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    private long id;
    private String name;
    private Date birthDate;
    private PetType petType;
    private Owner owner;
}
