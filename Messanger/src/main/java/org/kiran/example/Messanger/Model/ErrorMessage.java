package org.kiran.example.Messanger.Model;

public class ErrorMessage {
	private String errorMessage;
	private long errorCode;
	private String document;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public ErrorMessage(String errorMessage, long errorCode, String document) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.document = document;
	}
	public ErrorMessage()
	{
		
	}
}
