package org.step;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.asserts.SoftAssert;
import org.utility.APIEndPoints;
import org.utility.UtilityClass;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinition extends UtilityClass {

	public static RequestSpecification spec;

	public static Response response;

	public static SoftAssert s = new SoftAssert();

	@Given("I send the Base URI with request spec builder")
	public void i_send_the_Base_URI_with_request_spec_builder() {

		spec = RestAssured.given().spec(RequestSpecBuilder());

	}

	@When("I send the {string} request with {string} endpoint")
	public void i_send_the_request_with_endpoint(String string, String string2) throws JsonProcessingException {

		APIEndPoints valueOf = APIEndPoints.valueOf(string2);

		String endPoints = valueOf.getEndPoints();

		if (string.equals("GET")) {

			response = spec.when().get(endPoints);

		} else if (string.equals("GETUserAccount")) {

			String[][] readXL = readXL("New");

			for (int i = 0; i < readXL("New").length; i++) {

				response = spec.when().get(endPoints + readXL[i][1]);

				i_verify_the_response_and_response_code(string, response.statusCode());

				String asString = response.body().asString();

				JsonPath pa = new JsonPath(asString);

				System.out.println(pa.get("user.email"));	

				SoftAssert s = new SoftAssert();

				s.assertTrue(readXL[i][1].equalsIgnoreCase((String) pa.get("user.email")));

				s.assertAll();


			}

		}

		else if (string.equals("POSTCeateAccount")) {

			String[][] readXL = readXL("New");

			for (int i = 0; i < readXL.length; i++) {

				Map<String, String> mp = new LinkedHashMap();

				mp.put("name", readXL[i][0]);

				mp.put("email", readXL[i][1]);

				mp.put("password", readXL[i][2]);

				mp.put("title", readXL[i][3]);

				mp.put("birth_date", readXL[i][4]);

				mp.put("birth_month", readXL[i][5]);

				mp.put("birth_year", readXL[i][6]);

				mp.put("firstname", readXL[i][7]);

				mp.put("lastname", readXL[i][8]);

				mp.put("company", readXL[i][9]);

				mp.put("address1", readXL[i][10]);

				mp.put("address2", readXL[i][11]);

				mp.put("country", readXL[i][12]);

				mp.put("zipcode", readXL[i][13]);

				mp.put("state", readXL[i][14]);

				mp.put("city", readXL[i][15]);

				mp.put("mobile_number", readXL[i][16]);

				spec = RestAssured.given().spec(RequestSpecBuilder()).formParams(mp);

				response = spec.when().post(endPoints);

				i_verify_the_response_and_response_code(string, response.statusCode());

			}

		} else if (string.equals("POSTSearchProduct")) {

			spec = spec.formParams(searchProduct("Polo"));

			response = spec.when().post(endPoints);

		} else if (string.equals("VerifyUserExist")) {

			spec = spec.formParams(verifyUserExist("arun55@mgai.com", "1243434"));

			response = spec.when().post(endPoints);

		}

		else if (string.equals("PUT")) {

			String[][] readXL = readXL("Update");

			for (int i = 0; i < readXL.length; i++) {

				Map<String, String> mp = new LinkedHashMap();

				mp.put("name", readXL[i][0]);

				mp.put("email", readXL[i][1]);

				mp.put("password", readXL[i][2]);

				mp.put("title", readXL[i][3]);

				mp.put("birth_date", readXL[i][4]);

				mp.put("birth_month", readXL[i][5]);

				mp.put("birth_year", readXL[i][6]);

				mp.put("firstname", readXL[i][7]);

				mp.put("lastname", readXL[i][8]);

				mp.put("company", readXL[i][9]);

				mp.put("address1", readXL[i][10]);

				mp.put("address2", readXL[i][11]);

				mp.put("country", readXL[i][12]);

				mp.put("zipcode", readXL[i][13]);

				mp.put("state", readXL[i][14]);

				mp.put("city", readXL[i][15]);

				mp.put("mobile_number", readXL[i][16]);

				spec = RestAssured.given().spec(RequestSpecBuilder()).formParams(mp);

				response = spec.when().put(endPoints);

				i_verify_the_response_and_response_code(string, response.statusCode());

			}

		} else if (string.equals("DELETE")) {

			String[][] readXL = readXL("Update");

			for (int i = 0; i < readXL.length; i++) {

				Map<String, String> mp = new LinkedHashMap();

				mp.put("email", readXL[i][1]);

				mp.put("password", readXL[i][2]);

				spec = RestAssured.given().spec(RequestSpecBuilder()).formParams(mp);

				response = spec.when().delete(endPoints);

				i_verify_the_response_and_response_code(string, response.statusCode());

			}

		}

	}

	@Then("I verify the {string} response and response code {int}")
	public void i_verify_the_response_and_response_code(String string, int int1) {

		if (string.equalsIgnoreCase("GET")) {

			response = response.then().spec(ResponseSpecBuilder(int1)).assertThat().extract().response();

			//Parents as = response.as(Parents.class);

			//System.out.println(as.getResponseCode());

			System.out.println(response.getStatusCode());

			// System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("{")));

			String substring = response.getBody().asString().substring(response.getBody().asString().indexOf("{"));

			Object products = response.jsonPath().get("products");

			System.out.println(response.body().asString());

			//	s.assertTrue( response.jsonPath().get("products[0].category") instanceof java.util.Map, " category is an object"); //verify is an Object

			//	s.assertTrue(response.jsonPath().get("products[0].brand") instanceof java.util.Map, "brand is not an object"); //verify is an Object

			s.assertTrue(response.jsonPath().get("products[0].category.usertype") instanceof java.util.Map, "usertype is not an object"); //verify is an Object

			s.assertTrue(response.jsonPath().get("products[0].brand") instanceof java.lang.String, "brand is not an Array"); //verify is an Object


			String string2 = response.jsonPath().getString("products[0].name"); // verify is String

			s.assertTrue(response.jsonPath().getString("products[0].name") instanceof java.lang.String,"name is String ");

			System.out.println("name is String: "+ string2);

			int int2 = response.jsonPath().getInt("responseCode");

			System.out.println("responseCode is number: "+ int2);  // verify if integer

			s.assertTrue(response.jsonPath().get("products") instanceof java.util.List); // verify is an array

			s.assertTrue(products instanceof java.util.List);

			s.assertAll();


		} else if (string.equalsIgnoreCase("GETUserAccount")) {

			response = response.then().spec(ResponseSpecBuilder(int1)).assertThat().extract().response();

			String asString = response.body().asString();

			JsonPath pa = new JsonPath(asString);

			//    System.out.println(pa.get("user.email"));

			//	System.out.println( user.getAddress1());

			//	System.out.println(response.getStatusCode());


			//
			//			System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("{")));
			//			
			//			String substring = response.getBody().asString().substring(response.getBody().asString().indexOf("{"));
			//			
			//	JsonPath jp = new JsonPath(substring);

			//		   System.out.println(jp.get("user.id"));
			//		   
			//		   System.out.println(jp.get("user.name"));



		}

		else if (string.equalsIgnoreCase("POSTCeateAccount")) {

			response = response.then().spec(ResponseSpecBuilder(int1)).assertThat().extract().response();

			System.out.println(response.getStatusCode());

			System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("{")));

			String asString = response.getBody().asString().substring(response.getBody().asString().indexOf("{"));

			JsonPath jp = new JsonPath(asString);

			System.out.println(jp.getString("message"));

			System.out.println(jp.getString("responseCode"));

			// Assert.assertArrayEquals(200, jp.getString("responseCode"));

		} else if (string.equalsIgnoreCase("DELETE")) {

			response = response.then().spec(ResponseSpecBuilder(int1)).assertThat().extract().response();

			System.out.println(response.getStatusCode());

			System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("{")));

			String asString = response.getBody().asString().substring(response.getBody().asString().indexOf("{"));

			JsonPath jp = new JsonPath(asString);

			System.out.println(jp.getString("message"));

			System.out.println(jp.getString("responseCode"));

		} else if (string.equalsIgnoreCase("POSTSearchProduct")) {

			response = response.then().spec(ResponseSpecBuilder(int1)).assertThat().extract().response();

			System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("{")));

			String asString = response.getBody().asString().substring(response.getBody().asString().indexOf("{"));

			JsonPath jp = new JsonPath(asString);

			System.out.println(jp.getString("products[0].price"));

			System.out.println(jp.getString("responseCode"));

		} else if (string.equalsIgnoreCase("VerifyUserExist")) {

			response = response.then().spec(ResponseSpecBuilder(int1)).assertThat().extract().response();

			System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("{")));

			String asString = response.getBody().asString().substring(response.getBody().asString().indexOf("{"));

			JsonPath jp = new JsonPath(asString);

			System.out.println(jp.getString("message"));

			System.out.println(jp.getString("responseCode"));

		} else if (string.equalsIgnoreCase("PUT")) {

			response = response.then().spec(ResponseSpecBuilder(int1)).assertThat().extract().response();

			System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("{")));

			String asString = response.getBody().asString().substring(response.getBody().asString().indexOf("{"));

			JsonPath jp = new JsonPath(asString);

			System.out.println(jp.getString("message"));

			System.out.println(jp.getString("responseCode"));

		}

	}

}
