package org.fourgeeks.gha.crud.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class GHARecordTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<GHARecord<String>> records;
	private ArrayList<String> columns;
	
	public GHARecordTable() {
		columns = new ArrayList<String>();
		records = new ArrayList<GHARecord<String>>();
	}
	
	public ArrayList<GHARecord<String>> getRecords() {
		return records;
	}
	
	public ArrayList<String> getColumns() {
		return columns;
	}
}
