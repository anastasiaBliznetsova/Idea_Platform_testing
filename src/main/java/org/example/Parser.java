package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parser {
    public static List<Ticket> parserDataSpecificNode(String pathName, String specificNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        var jsonNode = objectMapper.readValue(new File(pathName), JsonNode.class);
        String ticketsAsString = jsonNode.get(specificNode).toString();
        return objectMapper.readValue(ticketsAsString, new TypeReference<>() { });
    }
}
