package org.fourgeeks.gha.crud.server;

import java.util.ArrayList;
import java.sql.*;

import org.fourgeeks.gha.crud.client.DBService;
import org.fourgeeks.gha.crud.shared.GHARecord;
import org.fourgeeks.gha.crud.shared.GHARecordTable;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DBServiceImpl extends RemoteServiceServlet implements DBService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/gha", "postgres", "postgres");
	}

	@Override
	public GHARecordTable getTable(String table, String schema) {
		try {
			Statement stm = connect().createStatement();
			ResultSet rs = stm.executeQuery("SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '" + table + "' ORDER BY ordinal_position");
			GHARecordTable rt = new GHARecordTable();
			rt.setTableName(table);
			while(rs.next()) {
				String tmp = rs.getString("column_name");
				rt.getColumns().add(tmp);
			}
			rs = stm.executeQuery("SELECT * FROM " + table);
			while(rs.next()) {
				GHARecord record = new GHARecord();
				for(int i = 0; i < rt.getColumns().size(); i++) {
					String tmp = rs.getString(rt.getColumns().get(i));
					record.add(tmp);
				}
				rt.getRecords().add(record);
			}
			return rt;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Something went horribly wrong");
		return null;
	}
	
	@Override
	public void addRecord(GHARecord record, GHARecordTable table) {
		try {
			Statement stm = connect().createStatement();
			stm.execute(table.getQuery(record));
		} catch (Exception e) {
			
		}
	}
	

}
