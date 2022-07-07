package api;

import static io.restassured.RestAssured.given;

public class APIRequest extends APIUtils implements APIVerbos {
  @Override
  public void GET() {
    response = given()
        .relaxedHTTPSValidation()
        .params(params)
        .headers(headers)
        .get(uri);

    log("GET");
  }

  @Override
  public void POST() {
    response = given()
        .relaxedHTTPSValidation()
        .params(params)
        .headers(headers)
        .body(body.toString())
        .post(uri);

    log("POST");
  }

  @Override
  public void PUT() {
    response = given()
        .relaxedHTTPSValidation()
        .params(params)
        .headers(headers)
        .body(body.toString())
        .put(uri);

    log("PUT");
  }

  @Override
  public void PATCH() {
    response = given()
        .relaxedHTTPSValidation()
        .params(params)
        .headers(headers)
        .body(body.toString())
        .patch(uri);

    log("PATCH");
  }

  @Override
  public void DELETE() {
    response = given()
        .relaxedHTTPSValidation()
        .params(params)
        .headers(headers)
        .delete(uri);

    log("DELETE");
  }
}
