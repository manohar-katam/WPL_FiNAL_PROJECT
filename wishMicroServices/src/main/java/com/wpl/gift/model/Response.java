
package com.wpl.gift.model;
/**
 * Author Sneha
 */
public class Response {
	
	public Response(String responseMessage, String responseCode) {
		super();
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}
	public Response() {
		// TODO Auto-generated constructor stub
	}
	public String responseMessage;
	public String responseCode;
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	

}
