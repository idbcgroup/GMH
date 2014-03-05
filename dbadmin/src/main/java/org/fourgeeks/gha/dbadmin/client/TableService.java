package org.fourgeeks.gha.dbadmin.client;

import java.util.ArrayList;

import org.fourgeeks.gha.dbadmin.shared.Record;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tableService")
public interface TableService extends RemoteService {
	ArrayList<Record> getTable();
	ArrayList<String> getColumns();
}