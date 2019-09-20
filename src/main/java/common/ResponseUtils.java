package common;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class ResponseUtils {
	public String getHeader(CloseableHttpResponse response, String headerName) {
		//het all headers
		Header[] headers = response.getAllHeaders();
		String returnHeader = "";		
		for(Header header : headers){
			if(headerName.equals(header.getName())){
				returnHeader = header.getValue();
			}
		}	
		if(returnHeader.isEmpty()){
			throw new RuntimeException("No header: " + headerName);
		}
		
		return returnHeader;
	}
	
	public Header[] getHeaders(CloseableHttpResponse response){
		return response.getAllHeaders();
	}
	
	public String getHeaderByName(Header[] headers, String headerName){
		String returnHeaderValue = "";		
		for(Header header : headers){
			if(headerName.equals(header.getName())){
				returnHeaderValue = header.getValue();
			}
		}	
		return returnHeaderValue;
	}
	
	public String getHeaderJava8Way(CloseableHttpResponse response, String headerName) {
		//het all headers
		List<Header> headers = Arrays.asList(response.getAllHeaders());
		
		Header matchedHaader =  headers.stream()
				.filter(header -> headerName.equalsIgnoreCase(header.getName()))
				.findFirst().orElseThrow(() -> new RuntimeException("No header"));
			
		return matchedHaader.getValue();
	}
	
	public boolean headerIsPresent (CloseableHttpResponse response, String headerName) {
		//get all headers
		List<Header> headers = Arrays.asList(response.getAllHeaders());
		boolean isHeader = headers.stream().anyMatch(header -> headerName.equalsIgnoreCase(header.getName()));
		
		return isHeader;		
	}
	
	public JSONObject getBody(CloseableHttpResponse response) throws ParseException, IOException{
		String body = EntityUtils.toString(response.getEntity());
		JSONObject jsonBody = new JSONObject(body);
		return jsonBody;
	}
	
//	public <T> T getAttribValueFromBody (JSONObject body, String attrib){
//		@SuppressWarnings("unchecked")
//		T idValue = (T)getValueForKey(body, attrib);
//		return idValue;
//	}
	
	public Integer getAttribValueFromBody (JSONObject body, String attrib){
		Integer idValue = (Integer) getValueForKey(body, attrib);
		return idValue;
	}
	
	
	private Object getValueForKey(JSONObject jsonBody, String key) {
		return jsonBody.get(key);		
	}

}
