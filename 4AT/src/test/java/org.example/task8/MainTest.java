package org.example.task8;

//1. Create 3 simple unit tests for Task_2 (modify your code to have 3 different methods in Task_2 solving if needed).
//2. Create testng.xml which should execute your test class. Execute this file
//3. Add a Data provider for each test.
//4. Create a test with parameters loaded from testng.xml.

import org.example.task6.Meeting;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

import static org.example.task6.Main.*;

public class MainTest {
    @DataProvider
    public static Object[][] updateDBData() {
        return new Object[][]{
                {"AQA" + UUID.randomUUID().toString().substring(0, 10), 1},
                {"TEST" + UUID.randomUUID().toString().substring(0, 10), 2}
        };
    }

    @Test
    public void creteDataBaseTest() {
        String titleTest = "titleTest";
        String dateTest = "dateTest";
        String discTest = "discTest";
        Integer idTest = new Random().nextInt();

        //Create
        Meeting meeting = createMeeting(titleTest, dateTest, discTest, idTest);

        //Check if created
        Meeting actualMeeting = readMeeting(meeting.getId());

        Assert.assertEquals(actualMeeting.getTitle(), titleTest, "Invalid title!(");
        Assert.assertEquals(actualMeeting.getDate(), dateTest, "Invalid date!(");
    }

    @Test(dataProvider = "updateDBData")
    public void updateDataBaseTest(String updateTitle, Integer idTest) {
         updateTitle = "AQA" + UUID.randomUUID().toString().substring(0, 10);
         idTest = 1;

        //Read
        Meeting meeting = readMeeting(idTest);
        meeting.setTitle(updateTitle);

        //Update
        updateMeeting(meeting);

        Meeting actualMeeting = readMeeting(meeting.getId());

        Assert.assertEquals(actualMeeting.getTitle(), updateTitle, "Invalid update title!(");


    }
}
