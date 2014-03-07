package org.fourgeeks.gha.dbadmin.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class GHARecordTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> columns;
	private ArrayList<GHARecord> records;
	
	private String tableName;
	
	public ArrayList<GHARecord> getRecords() {
		return records;
	}
	
	public ArrayList<String> getColumns() {
		return columns;
	}
	
	public void setRecords(ArrayList<GHARecord> list) {
		records = list;
	}
	
	public void setColumns(ArrayList<String> list) {
		columns = list;
	}
	
	public void setTableName(String name) {
		tableName = name;
	}
	
	public String getColumnQuery() {
		String result = "(" + columns.get(0);
		for (int i = 1; i < columns.size(); i++) {
			result += ", " + columns.get(i);
		}
		return result + ")";
	}
	
	public String getQuery(GHARecord r) {
		return "INSERT INTO " + tableName + " " + getColumnQuery() + " VALUES " + r.getQuery();
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public GHARecordTable() {
		columns = new ArrayList<String>();
		records = new ArrayList<GHARecord>();
	}
	
}
