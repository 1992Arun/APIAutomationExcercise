package org.utility;

public enum APIEndPoints {

	GETRequest("api/productsList"),
	GETUserAccount("api/getUserDetailByEmail?email="),
	POSTSearchProduct("api/searchProduct"),
	POSTCreateAccount("api/createAccount"),
	VerifyUserExist("api/verifyLogin"),
	PUTRequest("api/updateAccount"),
	DELETERequest("api/deleteAccount");
	
	String details;
	
	APIEndPoints(String deta){
		
		this.details=deta;
		
	}
	
	public String getEndPoints() {
		
		return details;
	}
	
}
