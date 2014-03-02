package org.fourgeeks.gha.dbadmin.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Record implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> list;
	private String query;
	
	public Record() {
		list = new ArrayList<String>();
	}
	
	public void add(String s) {
		list.add(s);
	}
	
	public void setQuery(String s) {
		query = s;
	}
	
	public String getQuery() {
		return query;
	}
	
	public ArrayList<String> getList() {
		return list;
	}
}
