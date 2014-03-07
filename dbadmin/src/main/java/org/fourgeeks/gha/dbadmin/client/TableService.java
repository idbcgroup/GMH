package org.fourgeeks.gha.dbadmin.client;

import org.fourgeeks.gha.dbadmin.shared.GHARecordTable;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tableService")
public interface TableService extends RemoteService {
	GHARecordTable getTable();
}