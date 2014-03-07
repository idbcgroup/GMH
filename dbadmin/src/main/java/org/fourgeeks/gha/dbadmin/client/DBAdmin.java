package org.fourgeeks.gha.dbadmin.client;

import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.SingleUploader;

import java.util.ArrayList;

import org.fourgeeks.gha.dbadmin.shared.GHARecord;
import org.fourgeeks.gha.dbadmin.shared.GHARecordTable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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

	public TableServiceAsync tableService;
	public DBServiceAsync dbService;

	public void build(ArrayList<GHARecord> list) {

	}

	@Override
	public void onModuleLoad() {
		final SingleUploader upload = new SingleUploader();

		tableService = GWT.create(TableService.class);
		dbService = GWT.create(DBService.class);

		upload.setTitle("uploadFormElement");

		upload.addOnFinishUploadHandler(new OnFinishUploaderHandler() {

			@Override
			public void onFinish(IUploader uploader) {
				recordTable.clear();
				tableService.getTable(new AsyncCallback<GHARecordTable>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Error: no se pudo cargar los nombres de las columnas");
					}

					@Override
					public void onSuccess(final GHARecordTable result) {
						// TODO Auto-generated method stub
						HorizontalPanel columnPanel = new HorizontalPanel();
						for (int i = 0; i < result.getColumns().size(); i++) {
							String c = result.getColumns().get(i);
							final int index = i;
							final TextBox tb = new TextBox();
							tb.setText(c);
							tb.addChangeHandler(new ChangeHandler() {

								@Override
								public void onChange(ChangeEvent event) {
									result.getColumns().set(index, tb.getText());
								}});
							columnPanel.add(tb);
						}
						recordTable.add(columnPanel);
						
						for (final GHARecord r : result.getRecords()) {
							HorizontalPanel p = new HorizontalPanel();
							for (int i = 0; i < r.getList().size(); i++) {
								final int index = i;
								String s = r.getList().get(i);
								final TextBox tb = new TextBox();
								tb.setText(s);
								tb.addChangeHandler(new ChangeHandler() {

									@Override
									public void onChange(ChangeEvent event) {
										r.getList().set(index, tb.getText());
									}});
								p.add(tb);
							}
							final Button b = new Button();
							b.setText("SQL");
							b.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									Window.alert(result.getQuery(r));
								}
							});
							p.add(b);
							recordTable.add(p);
						}
						
						tableName.setText(result.getTableName());
						
						Button inject = new Button("Send");
						inject.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								dbService.updateDB(result, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										Window.alert("Error: Failed to connect");
									}

									@Override
									public void onSuccess(Void result) {
										// TODO Auto-generated method stub
										Window.alert("Done!");
									}});
							}});
						
						recordTable.add(inject);
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
