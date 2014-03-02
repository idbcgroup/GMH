package org.fourgeeks.dbadmin.server;

import java.util.ArrayList;

import org.fourgeeks.dbadmin.client.TableService;
import org.fourgeeks.dbadmin.shared.Record;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author
 * 
 */
public class TableServiceImpl extends RemoteServiceServlet implements
		TableService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ArrayList<Record> list;
	public static ArrayList<String> columns;

	public ArrayList<String> getColumns() {
		return columns;
	}

	public ArrayList<Record> getTable() {
		return list;
	}

}
