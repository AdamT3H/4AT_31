package org.example.task5;
//The general task for all:
//Serialization-Deserialization:
//a) Make some complex models using your variant.
//b) Make it serializable.
//c) Read JSON from “input.json”
//d) and deserialize it to POJO.
//e) Then change a few fields and save it to “output.json”.
//f) Do the same for XML.
//V6
//{
//    "title": "Meeting with team",
//        "date": "2023-03-01",
//        "description": "Discuss project status with team members"
//}
//<meeting>
//<title>Meeting with team</title>
//  <date>2023-03-01</date>
//<description>Discuss project status with team members</description>
//</meeting>

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SerializationDeserialization {
    public static void main(String[] args)  {

        String inputJSON = "";

        try (Scanner scanner = new Scanner(new File("/Users/adamogorodnik/IdeaProjects/4AT_31/4AT/src/main/resources/input.json"))) {
            while (scanner.hasNextLine()) {
                inputJSON += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Meeting meet = null;

        try {
            meet = objectMapper.readValue(inputJSON, Meeting.class);
            System.out.println(meet);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        meet.setTitle("Test");
        meet.setDescription("Test");
        meet.setDate("Test");

        saveToJson(meet);

//        meet = (Meeting) JAXBContext.newInstance(Meeting.class)
//                .createUnmarshaller()
//                .unmarshal(new File("/src/main/resources/input.xml"));



    }

    private static void saveToJson(Meeting meet) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter("/Users/adamogorodnik/IdeaProjects/4AT_31/4AT/src/main/resources/output.json")) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, meet);
            System.out.println("Об'єкт збережено у output.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
