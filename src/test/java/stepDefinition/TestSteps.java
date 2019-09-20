package stepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import common.Constants;
import common.ResponseUtils;
import cucumber.api.java.en.*;
import entities.Order;

public class TestSteps {
	private CloseableHttpClient client; 
	private HttpGet get;
	private String url;
	private CloseableHttpResponse response;
	private Header[] headers;
	SoftAssert softAssert = new SoftAssert();
	
	@Given("^I have the API url as \"([^\"]*)\"$")
	public void getUrl(String apiUrl){
		url = Constants.BASE_ENDPOINT_SWAGGER + apiUrl;
		client = HttpClientBuilder.create().build();
		get = new HttpGet(url);
		System.out.println("URL: " + url );
	}
	
	
	@Given("I have the API url {string}")
	public void setUrl(String apiUrl){
		url = Constants.BASE_ENDPOINT_SWAGGER + apiUrl;
		System.out.println("API url: " + url);
	}
	
	@When("^I send the request$")
	public void sentGetRequest() throws ClientProtocolException, IOException{
		response =  client.execute(get);
		
	}
	
	@Then("^I get the responseCode as (\\d+)$")
	public void getResponseCode(int statucCode){
		int actualStatus = response.getStatusLine().getStatusCode();
		System.out.println("Status is: " + actualStatus);
		assertEquals(actualStatus, statucCode, "Status code is not as expected");
		
	}	
	
	@Then("^I get the contentType as \"([^\"]*)\"$")
	public void getContentType(String contentType){
		ContentType ct = ContentType.getOrDefault(response.getEntity());
		assertEquals(ct.getMimeType(), contentType, "Content type is not as expected");		
	}
	
	@Then("^I get all the headers$")
	public void getAllHeaders(){
		ResponseUtils ru = new ResponseUtils();
		headers = ru.getHeaders(response);
	}
	
	@Then("^I check allowHeaders as \"([^\"]*)\" and allowMethods as \"([^\"]*)\"$")
	public void getHeaderValue(String allowHeaders, String allowMethods){
		ResponseUtils ru = new ResponseUtils();
		softAssert.assertEquals(ru.getHeaderByName(headers, Constants.ALLOW_HEADERS), allowHeaders, "Allowed headers are not as expected");
		softAssert.assertEquals(ru.getHeaderByName(headers, Constants.ALLOW_METHODS), allowMethods, "Allowed methods are not as expected");
	}
	
	@When("I send a post request with body {string}")
	public void sendPostRequest( String bodyPath ) throws JSONException, IOException{	
		client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);		
		System.out.println(System.getProperty("user.dir") + "\\testData\\" + bodyPath);
		JSONObject jsonObject = common.JsonUtils.parseJSONFile(System.getProperty("user.dir") + "\\testData\\" + bodyPath);		
		request.setEntity(new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));		
		response = client.execute(request);		
	}
	
	@Then("The order with ID {int} is returned")
	public void getOrderIdFromResponse(int orderID) throws ParseException, IOException{
		ResponseUtils ru = new ResponseUtils();
		JSONObject body = ru.getBody(response);
		Integer orderNumber = ru.getAttribValueFromBody(body, Order.ORDER_ID);
		assertEquals(orderNumber, Integer.valueOf(orderID), "The returned order is not as expected. Expected: " + orderID + " Actual: " + orderNumber);		
	}
	
	@When("^Delete the order \"([^\"]*)\"$")
	public void deleteOrder(String orderId) throws ClientProtocolException, IOException{
		String url = Constants.BASE_ENDPOINT_SWAGGER + Constants.BASE_ENDPOINT_ORDER + "/" + orderId;		
		System.out.println("Delete url: " + url);
		client = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(url);
		response = client.execute(request);			
	}
	
	@Then("Check pets more than {int}")
	public void getPetByStatus (int expectedPets) throws ParseException, IOException{
	//	ResponseUtils ru = new ResponseUtils();
		String body = EntityUtils.toString(response.getEntity());
		
		JSONArray response_json = new JSONArray(body);	
		
		
		
//		JSONObject body = ru.getBody(response);
		int pets = response_json.length();
		System.out.println("Available pets: " + body.length());
		assertTrue(expectedPets <= pets);
		
	}
	
}
