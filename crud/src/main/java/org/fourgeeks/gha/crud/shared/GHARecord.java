package org.fourgeeks.gha.crud.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class GHARecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> list;
	private String query;
	
	public GHARecord() {
		list = new ArrayList<String>();
	}
	
	public void add(String s) {
		list.add(s);
	}
	
	public String getQuery() {
		String result = "('" + list.get(0);
		for (int i = 1; i < list.size(); i++) {
			result += "', '" + list.get(i);
		}
		return result + "')";
	}
	
	public ArrayList<String> getList() {
		return list;
	}
}
