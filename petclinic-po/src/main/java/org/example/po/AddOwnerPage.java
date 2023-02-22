package org.example.po;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Owner;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
@AllArgsConstructor
public class AddOwnerPage {

    private HeaderPart headerPart;
    private FindOwnersPage findOwnersPage;

    public void navigate() {
        headerPart.findOwners();
        findOwnersPage.addOwner();
    }

    public void addOwner(Owner owner) {
        $("#firstName").sendKeys(owner.getFirstName());
        $("#lastName").sendKeys(owner.getLastName());
        $("#address").sendKeys(owner.getAddress());
        $("#city").sendKeys(owner.getCity());
        $("#telephone").sendKeys(owner.getTelephone());
        $("button[type=submit]").click();
    }
}
