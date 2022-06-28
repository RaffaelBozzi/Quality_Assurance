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

        super.log("GET");
    }

    @Override
    public void POST() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .get(uri);

        super.log("POST");
    }

    @Override
    public void PUT() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .get(uri);

        super.log("PUT");
    }

    @Override
    public void PATCH() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .body(body.toString())
                .get(uri);

        super.log("PATCH");
    }

    @Override
    public void DELETE() {
        response = given()
                .relaxedHTTPSValidation()
                .params(params)
                .headers(headers)
                .get(uri);

        super.log("DELETE");
    }
}
