package cz.rsvoboda.getting.started;

import io.quarkus.test.junit.QuarkusTest;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMUnitConfig;
import org.jboss.byteman.contrib.bmunit.WithByteman;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@WithByteman
@BMUnitConfig(verbose = true, bmunitVerbose = true)
public class GreetingResourceTest {

    @Test
    @BMRule(name = "GreetingResource",
            targetClass = "cz.rsvoboda.getting.started.GreetingResource",
            targetMethod = "hello",
            action = "throw new RuntimeException( \"Byteman ...\" )"
//            action = "System.out.println(\"GreetingResource - invoke\")"
    )
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}