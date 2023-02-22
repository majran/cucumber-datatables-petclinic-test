package org.example.tests.steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.VisitDao;
import org.example.model.Pet;
import org.example.model.Visit;
import org.example.tests.TestConfig;
import org.example.tests.TestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ContextConfiguration(classes = {TestConfig.class})
public class VisitSteps {

    @Autowired
    private TestContext context;
    @Autowired
    private VisitDao visitDao;

    @ParameterType("(.*) for (.*)")
    public Visit visit(String date, String description) {
        log.info("Date: {}", date);
        log.info("Description: {}", description);
        return Visit.builder().date(Date.valueOf(date)).description(description).build();
    }
    @When("add visit on {visit}")
    public void addVisit(Visit visit) {
        visit.setPet(context.getContextEntry("pet"));
        log.info("Visit to be schedules: {}", visit);
        visitDao.addVisit(visit);
        context.getContextMap().put("visit", visit);
    }

    @Then("visit is scheduled")
    public void visitIsScheduled() {
        Visit expectedVisit = (Visit) context.getContextMap().get("visit");
        Pet pet = context.getContextEntry("pet");
        Visit actualVisit = visitDao.findByPet(pet.getId(), expectedVisit.getDate());

        assertThat(actualVisit)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedVisit);
    }
}
