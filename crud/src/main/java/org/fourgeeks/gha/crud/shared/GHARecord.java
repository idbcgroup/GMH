package org.fourgeeks.gha.crud.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class GHARecord<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<T> contents;
	
	public GHARecord() {
		contents = new ArrayList<T>();
	}
	
	public T get(int index) {
		return contents.get(index);
	}
	
	public void set(int index, T obj) {
		contents.set(index, obj);
	}

	public void add(T obj) {
		contents.add(obj);
	}
	
	public Integer size() {
		return contents.size();
	}

}
