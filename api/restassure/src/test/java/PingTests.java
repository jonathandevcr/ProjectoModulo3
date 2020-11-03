import org.testng.annotations.Test;
import static org.hamcrest.core.IsEqual.equalTo;


public class PingTests extends BaseTest {

    private static final String RESOURCE = "/ping";

  @Test
    public void Ping_Test(){

      request
              .get(String.format("%s", RESOURCE))
              .then()
              .statusCode(200)
              .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
              .body("id", equalTo(3),"response",equalTo("pong"));
  }

}
