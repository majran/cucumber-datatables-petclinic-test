package org.example.po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Owner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
@Component
public class OwnerInformationPage {

    public long getIdOfDisplayedOwner() {
        return Long.parseLong(Selenide.webdriver().object().getCurrentUrl()
                .replaceAll(".*\\/", ""));
    }

    public Owner getOwnerInfo() {
        Map<String, String> ownerMap = $x("//h2[text()='Owner Information']/parent::*")
                .shouldBe(Condition.visible)
                .findAll("table tr")
                .asFixedIterable()
                .stream()
                .collect(Collectors.toMap(
                        tr -> tr.find("th").getText(),
                        tr -> tr.find("td").getText()));

        return Owner.builder()
                .firstName(ownerMap.get("First name"))
                .lastName(ownerMap.get("Last name"))
                .address(ownerMap.get("Address"))
                .city(ownerMap.get("City"))
                .telephone(ownerMap.get("Telephone"))
                .build();
    }
}
