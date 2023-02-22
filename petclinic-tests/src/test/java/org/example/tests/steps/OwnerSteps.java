package org.example.tests.steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.OwnerDao;
import org.example.model.Owner;
import org.example.po.AddOwnerPage;
import org.example.po.OwnerInformationPage;
import org.example.tests.TestConfig;
import org.example.tests.TestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ContextConfiguration(classes = {TestConfig.class})
public class OwnerSteps {

    @Autowired
    private TestContext context;
    @Autowired
    private AddOwnerPage addOwnerPage;
    @Autowired
    private OwnerInformationPage ownerInformationPage;
    @Autowired
    private OwnerDao ownerDao;

    @Given("Doctor Vet Qualified")
    public void doctorVetQualified() {
        log.info("NOOP step");
    }

    @When("add pet owners")
    public void addPetOwners(List<Owner> owners) {
        log.info("Owners: \n\t{}", owners.stream().map(Owner::toString).collect(Collectors.joining("\n\t")));
        context.getContextMap().put("owners", owners);

        owners.forEach(expectedOwner -> {
            addOwnerPage.navigate();
            addOwnerPage.addOwner(expectedOwner);
            Owner actualOwner = ownerInformationPage.getOwnerInfo();
            log.info("[Web] Expect Owner is: {}", expectedOwner);
            log.info("[Web] Actual Owner is: {}", actualOwner);
            assertThat(actualOwner).isEqualTo(expectedOwner);
            expectedOwner.setId(ownerInformationPage.getIdOfDisplayedOwner());
        });
    }

    @Then("owner of pet are added to system")
    public void petOwnersAreAddedToSystem() {
        List<Owner> owners = context.getContextEntry("owners");
        owners.forEach(expectedOwner -> {
            Owner actualOwner = ownerDao.findById(expectedOwner.getId());

            log.info("[SQL] Expect Owner is: {}", expectedOwner);
            log.info("[SQL] Actual Owner is: {}", actualOwner);
            assertThat(actualOwner).isEqualTo(expectedOwner);
        });
    }

    @ParameterType("(.*) (.*)")
    public Owner owner(String firstName, String lastName) {
        log.info("First name: {}", firstName);
        log.info("Last name: {}", lastName);
        return ownerDao.findByFirstLastName(firstName, lastName);
    }

    @Given("{owner} pet owner")
    public void petOwner(Owner owner) {
        context.getContextMap().put("owner", owner);
        log.info("Owner from param type: {}", owner);
    }
}
