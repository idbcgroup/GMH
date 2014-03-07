package org.fourgeeks.gha.crud.client;

import java.util.ArrayList;

import org.fourgeeks.gha.crud.shared.GHARecordTable;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dbService")
public interface DBService extends RemoteService {
	public GHARecordTable getTable(String table, String schema);
}
