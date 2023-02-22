package org.example.tests.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class CucumberTypes {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        log.info("Converting from {} of type {}", fromValue, fromValue.getClass());
        Object o = objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
        log.info("Converting to   {} of type -> {}", o, toValueType);
        return o;
    }
}
