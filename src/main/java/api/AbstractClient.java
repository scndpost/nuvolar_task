package api;

import configuration.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;

public class AbstractClient {

    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.config.basePetstoreUri())
                .addFilter(new AllureRestAssured())
                .build();
    }

    protected static Response get(RequestSpecification specification) {
        Response response = RestAssured.given().spec(specification).get();
        return checkResponseCode(response, HttpStatus.SC_OK);
    }

    private static Response checkResponseCode(Response response, int statusCode) {
        try {
            Assertions.assertThat(response.statusCode())
                    .as("Status code")
                    .isEqualTo(statusCode);
        } catch (AssertionError e) {
            String oldMessage = e.getMessage();
            String newMessage = String.format("%s \n Response: %s \n", oldMessage, response.body().asString());
            throw new AssertionError(newMessage);
        }
        return response;
    }
}
