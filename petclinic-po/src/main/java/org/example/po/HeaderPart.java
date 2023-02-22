package org.example.po;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class HeaderPart {
    public void homePage(){
        $("a[title='home page']").click();
    }

    public void findOwners(){
        $("a[title='find owners']").click();
    }

    public void veterinarians(){
        $("a[title='veterinarians']").click();
    }
}
