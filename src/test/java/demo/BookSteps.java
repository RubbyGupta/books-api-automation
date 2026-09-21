package demo;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class BookSteps {
    private String baseUrl;
    private Response response;

    @Given("the books API is configured")
    public void configureApi() {
        baseUrl = "https://simple-books-api.click";
    }

    @When("I request the books")
    public void requestBooks() {
        response = given().baseUri(baseUrl).when().get("/books");
        System.out.println("Status:" + response.statusCode());
        System.out.println("Time:" + response.time() + " ms");
        System.out.println("Body size:" + response.asByteArray().length + " bytes");
    }

    @Then("the status code should be {int}")
    public void checkStatus(int expected) {
        response.then().statusCode(expected);
    }

    @Then("the response time should be below {int} milliseconds")
    public void checkTime(int limit) {
        response.then().time(lessThan((long) limit));
    }

    @Then("the response body size should be greater than {int} bytes")
    public void checkSize(int minimum) {
        int bytes = response.asByteArray().length;
        assertTrue("Actual body size:" + bytes + " bytes", bytes > minimum);
    }

    @Then("the response should contain {int} books")
    public void checkCount(int count) {
        response.then().body("size()", equalTo(count));
    }

    @Then("the first book should be {string}")
    public void checkFirstBook(String name) {
        // Array indexes start at zero.
        response.then().body("[0].name", equalTo(name));
    }

    @Then("the book with ID {int} should be {string}")
    public void checkBookById(int id, String name) {
        // find locates a record by ID, regardless of its position.
        response.then().body("find { it.id == " + id + " }.name", equalTo(name));
    }

    @Then("there should be {int} fiction books")
    public void checkFictionBooks(int count) {
        // findAll selects every matching record.
        response.then().body("findAll { it.type == 'fiction' }.size()", equalTo(count));
    }

    @Then("there should be {int} available books")
    public void checkAvailableBooks(int count) {
        response.then().body("findAll { it.available == true }.size()", equalTo(count));
    }

    @Then("the book with ID {int} should be unavailable")
    public void checkUnavailableBook(int id) {
        response.then().body("find { it.id == " + id + " }.available", equalTo(false));
    }
}
