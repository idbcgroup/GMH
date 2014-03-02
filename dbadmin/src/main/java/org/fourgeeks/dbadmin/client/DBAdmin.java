package org.fourgeeks.dbadmin.client;

import java.util.ArrayList;

import org.fourgeeks.dbadmin.shared.Record;

import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.SingleUploader;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DBAdmin implements EntryPoint {

	VerticalPanel mainPanel = new VerticalPanel();
	HorizontalPanel header = new HorizontalPanel();
	HorizontalPanel uploadPanel = new HorizontalPanel();

	Label tableName = new Label();
	FlexTable mainTable = new FlexTable();
	VerticalPanel recordTable = new VerticalPanel();

	public TableServiceAsync service;

	public void build(ArrayList<Record> list) {

	}

	public void onModuleLoad() {
		final SingleUploader upload = new SingleUploader();
		
		service = GWT.create(TableService.class);

		upload.setTitle("uploadFormElement");

		upload.addOnFinishUploadHandler(new OnFinishUploaderHandler() {

			public void onFinish(IUploader uploader) {
				recordTable.clear();
				service.getColumns(new AsyncCallback<ArrayList<String>>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Error: no se pudo cargar los nombres de las columnas");
					}

					public void onSuccess(ArrayList<String> result) {
						// TODO Auto-generated method stub
						HorizontalPanel p = new HorizontalPanel();
						for (String c : result) {
							TextBox tb = new TextBox();
							tb.setText(c);
							p.add(tb);
						}
						recordTable.add(p);
					}});
				
				service.getTable(new AsyncCallback<ArrayList<Record>>() {

					public void onFailure(Throwable caught) {
						// TODO error
					}

					public void onSuccess(ArrayList<Record> result) {
						//build(result);
						for (final Record r : result) {
							HorizontalPanel p = new HorizontalPanel();
							for (String s : r.getList()) {
								TextBox tb = new TextBox();
								tb.setText(s);
								p.add(tb);
							}
							Button b = new Button();
							b.setText("SQL");
							b.addClickHandler(new ClickHandler() {

								public void onClick(ClickEvent event) {
									Window.alert(r.getQuery());
								}});
							p.add(b);
							recordTable.add(p);
						}
					}
				});
			}
		});

		uploadPanel.add(upload);

		header.add(new Label("Tabla:"));
		header.add(tableName);

		mainPanel.add(uploadPanel);
		mainPanel.add(header);
		mainPanel.add(recordTable);

		RootPanel.get("root").add(mainPanel);
	}
}
