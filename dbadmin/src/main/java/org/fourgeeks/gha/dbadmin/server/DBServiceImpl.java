package org.fourgeeks.gha.dbadmin.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.fourgeeks.gha.dbadmin.client.DBService;
import org.fourgeeks.gha.dbadmin.shared.GHARecord;
import org.fourgeeks.gha.dbadmin.shared.GHARecordTable;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DBServiceImpl extends RemoteServiceServlet implements DBService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void updateDB(GHARecordTable record) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con =
					DriverManager.getConnection("jdbc:postgresql://localhost:5432/gha", "postgres", "postgres");
			Statement stm = con.createStatement();
			for (GHARecord r : record.getRecords())
				stm.executeQuery(record.getQuery(r));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
