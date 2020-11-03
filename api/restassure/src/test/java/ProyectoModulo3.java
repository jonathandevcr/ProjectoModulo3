import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;



public class ProyectoModulo3 extends BaseTest {

  @Test
    public void StatusTest(){

      request
              .get(String.format("/status"))
              .then()
              .statusCode(200)
              .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
              .body("status", equalTo("Listos para el examen"));
  }

    @Test
    public void PutNameTest(){

        request
                .put(String.format("/updateName"))
                .then()
                .statusCode(406)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("message", equalTo("Name was not provided"));
    }

    @Test
    public void GetRandomNamewnAuthTest(){

        request
                .get(String.format("/randomName"))
                .then()
                .statusCode(401)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("message", equalTo("Please login first"));
    }

    @Test
    public void GetRandomNameAuthTest(){

        request
                .given().auth()
                .basic("testuser", "testpass")
                .get(String.format("/randomName"))
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("name", is(not(equalTo(null))));;

    }

    @Test
    public void SameNameTest(){

        JSONObject SameName = new JSONObject();
        SameName.put("name", "La Roca");

        request
                .body(SameName.toJSONString())
                .post("/sameName")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo("http://localhost"))
                .body("name", equalTo("La Roca"));;

    }


}
