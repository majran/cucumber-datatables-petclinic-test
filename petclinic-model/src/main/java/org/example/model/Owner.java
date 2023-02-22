package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
}
