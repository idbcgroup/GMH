package org.fourgeeks.gha.dbadmin.client;

import org.fourgeeks.gha.dbadmin.shared.GHARecord;
import org.fourgeeks.gha.dbadmin.shared.GHARecordTable;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dbService")
public interface DBService extends RemoteService {

	void updateDB(GHARecordTable record);
	
}
