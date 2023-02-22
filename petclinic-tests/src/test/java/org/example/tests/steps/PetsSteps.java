package org.example.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.OwnerDao;
import org.example.dao.PetDao;
import org.example.model.Pet;
import org.example.model.PetType;
import org.example.tests.TestConfig;
import org.example.tests.TestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ContextConfiguration(classes = {TestConfig.class})
public class PetsSteps {

    @Autowired
    private PetDao petDao;
    @Autowired
    private OwnerDao ownerDao;
    @Autowired
    private TestContext context;

    @ParameterType("(.*) (.*)")
    public Pet pet(String type, String name) {
        log.info("Pet name: {}", name);
        log.info("Pet type: {}", type);
        return petDao.findByOwnerNameType(name, PetType.valueOf(type.toUpperCase()));
    }

    @And("pet {pet}")
    public void givenPet(Pet pet) {
        log.info("Pet from param: {}", pet);
        context.getContextMap().put("pet", pet);
    }

    @DataTableType
    public Pet newPets(Map<String, String> row) {
        String[] ownerNames = row.get("Owner").split(" ");
        return Pet.builder()
                .name(row.get("Name"))
                .petType(PetType.valueOf(row.get("Type").toUpperCase()))
                .birthDate(Date.valueOf(row.get("DoB")))
                .owner(ownerDao.findByFirstLastName(ownerNames[0], ownerNames[1]))
                .build();
    }

    @Given("pets")
    public void pets(List<Pet> pets) {
        context.getContextMap().put("pets", pets);
        log.info("\n\t{}", pets.stream().map(Pet::toString).collect(Collectors.joining("\n\t")));
    }

    @And("single pet")
    public void singlePet(DataTable dataTable) {
        Pet singlePet = dataTable.convert(Pet.class, true);
        context.getContextMap().put("pet", singlePet);
        log.info("Single pet using @DataTableType with transpose {}", singlePet);
    }
}
