package org.example.task16;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfPropReader {
    static Properties properties= new Properties();

    static {
        try {
            properties.load(new FileInputStream("/Users/adamogorodnik/IdeaProjects/4AT_31/4AT/src/main/resources/confProp"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfProp(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
       getConfProp("trello_KEY");
    }
}
