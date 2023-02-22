package org.example.po;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class FindOwnersPage {

    public void navigate() {
        $("a[title='find owner']").click();
    }

    public void typeLastName(String lastName) {
        $("#lastName").sendKeys(lastName);
    }

    public void findOwner() {
        $("button[type=submit]").click();
    }

    public void addOwner() {
        $(By.linkText("Add Owner")).click();
    }
}
