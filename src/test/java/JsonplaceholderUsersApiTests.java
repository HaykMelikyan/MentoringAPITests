import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JsonplaceholderUsersApiTests {
    private Response response;

    @BeforeMethod
    public void performRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        final String ENDPOINT = "/users";

        response = RestAssured
                .get(ENDPOINT)
                .andReturn();
    }

    @Test
    public void statusCodeTest() {
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not matching");
    }

    @Test
    public void responseHeaderTest() {
        Assert.assertNotNull(response.getContentType(), "Response doesn't have content type header");
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8", "Content type is not matching");
    }

    @Test
    public void responseBodyTest() {
        Object[] usersList = response.getBody().as(Object[].class);
        Assert.assertEquals(usersList.length, 10, "Response body is not an array of 10 users");
    }
}
