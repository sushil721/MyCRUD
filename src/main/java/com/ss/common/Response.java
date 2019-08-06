package com.ss.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response<T> {
	
	private List<T> wrappedList = new ArrayList<T>();
	private Map<String,String> errorsMap = new HashMap<String,String>();
	private String responseCode;
	private String responseDesc;
	
	public Response(){
		
	}
	
	public Response(List<T> wrappedList) {
		super();
		this.wrappedList = wrappedList;
	}
	public Response(List<T> wrappedList, Map<String, String> errorsMap) {
		super();
		this.wrappedList = wrappedList;
		this.errorsMap = errorsMap;
	}

	public Response(List<T> wrappedList, Map<String, String> errorsMap, String responseCode, String responseDesc) {
		super();
		this.wrappedList = wrappedList;
		this.errorsMap = errorsMap;
		this.responseCode = responseCode;
		this.responseDesc = responseDesc;
	}

	public List<T> getWrappedList() {
		return wrappedList;
	}

	public void setWrappedList(List<T> wrappedList) {
		this.wrappedList = wrappedList;
	}

	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}

	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}	
}
