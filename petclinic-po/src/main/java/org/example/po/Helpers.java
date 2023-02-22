package org.example.po;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class Helpers {
    public static void openPage(String url) {
        Selenide.open(url);
    }

    public static void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }
}
