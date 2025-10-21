package task16;

//General task
//Make restAssured Test Cases using scenario from Task_15
//The same using any another API client.
//Add Request and Response clases for each unique endpoints.
//Validate Response Object using your own class comparator.


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.task16.ConfPropReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MainTest {

    @BeforeTest
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void restAssuredTest(){
        //Step1: Create Checklist
        Response responseCreateChecklist = given()
                .pathParam("trello_KEY", ConfPropReader.getConfProp("trello_KEY"))
                .pathParam("trello_TOKEN", ConfPropReader.getConfProp("trello_TOKEN"))
                .pathParam("card_ID", ConfPropReader.getConfProp("card_ID"))
                .when()
                .post("https://api.trello.com/1/checklists?key={trello_KEY}&token={trello_TOKEN}&idCard={card_ID}&name=task15")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        String checklist_ID = responseCreateChecklist.path("id");

        //Step2: Add Item
        String AddItemName = "task15AddItemTest";

        Response responseAddItem = given()
                .pathParam("trello_KEY", ConfPropReader.getConfProp("trello_KEY"))
                .pathParam("trello_TOKEN", ConfPropReader.getConfProp("trello_TOKEN"))
                .pathParam("checklist_ID", checklist_ID)
                .pathParam("AddItemName", AddItemName)
                .when()
                .post("https://api.trello.com/1/checklists/{checklist_ID}/checkItems?name={AddItemName}&key={trello_KEY}&token={trello_TOKEN}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(responseAddItem.path("name"), AddItemName, "Unexpected item name.");

        //Step3: Update the name of the checklist
        String newChecklistName = "UpdatedTask15";

        Response responseUpdateChecklistName = given()
                .pathParam("trello_KEY", ConfPropReader.getConfProp("trello_KEY"))
                .pathParam("trello_TOKEN", ConfPropReader.getConfProp("trello_TOKEN"))
                .pathParam("checklist_ID", checklist_ID)
                .pathParam("newChecklistName", newChecklistName)
                .when()
                .put("https://api.trello.com/1/checklists/{checklist_ID}?key={trello_KEY}&token={trello_TOKEN}&name={newChecklistName}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(responseUpdateChecklistName.path("name"), newChecklistName, "Unexpected item new name.");

        //Step4: Delete the checklist

        Response responseDeleteChecklist = given()
                .pathParam("trello_KEY", ConfPropReader.getConfProp("trello_KEY"))
                .pathParam("trello_TOKEN", ConfPropReader.getConfProp("trello_TOKEN"))
                .pathParam("checklist_ID", checklist_ID)
                .when()
                .delete("https://api.trello.com/1/checklists/{checklist_ID}?key={trello_KEY}&token={trello_TOKEN}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

}
