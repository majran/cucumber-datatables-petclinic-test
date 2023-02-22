package org.example.tests;

import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ScenarioScope
@Component
@Getter
public class TestContext {
    @Getter
    private final Map<String, Object> contextMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getContextEntry(String entryKey){
        return (T) contextMap.get(entryKey);
    }

    @SuppressWarnings("unchecked")
    public <T> T getContextEntry(String entryKey, T defaultValue){
        return (T) (Objects.isNull(contextMap.get(entryKey)) ? defaultValue : contextMap.get(entryKey));
    }

    public String getEntryAsString(String entryKey){
        return contextMap.get(entryKey).toString();
    }
}
