package org.fourgeeks.gha.dbadmin.server;

import org.fourgeeks.gha.dbadmin.client.TableService;
import org.fourgeeks.gha.dbadmin.shared.GHARecordTable;

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

	public static GHARecordTable table = new GHARecordTable();

	@Override
	public GHARecordTable getTable() {
		return table;
	}

}
